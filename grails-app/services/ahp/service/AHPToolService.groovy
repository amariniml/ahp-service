package ahp.service

import Jama.Matrix
import ahp.utils.HierarchyTree
import ahp.utils.Node
import ahp.utils.PairwaiseMatrix
import ahp.utils.VIPD
import ahp.utils.ValueComparator
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.http.HttpStatus


class AHPToolService {

    JSONObject generateMatrices(){
        HierarchyTree<VIPD> hierarchy = HierarchyTree.getHierarchyTree()
        Map<String,ArrayList<PairwaiseMatrix>> mapPairwaise = buildPairwaiseComparison(hierarchy,hierarchy.getAlternatives())
        JSONObject returnJSON = new JSONObject()
        returnJSON.put("CRITERIA",mapPairwaise.CRITERIA)
        returnJSON.put("ALTERNATIVE",mapPairwaise.ALTERNATIVE)
        return returnJSON
    }

    def createHierarchy(params) {
        if(params.containsKey('goal')){
            VIPD root = new VIPD(0,(String) params.get('goal'))
            HierarchyTree<VIPD> hierarchy = HierarchyTree.getHierarchyTree(root,(ArrayList<String>) params.get("alternative"),true)
            List sortedKeys = getSortedKeysOfInputs(params.keySet())
            Node rootNode = hierarchy.getRoot()
            sortedKeys.each { key ->
                if(key == 'criteria'){
                    params.get(key).each { value ->
                        def id =  obtainIdFromInput(value.toString())
                        def name = value.toString().split(' ')[1].replace(" ","")
                        if(id == ''){
                            VIPD toInsert = new VIPD(rootNode.getChildren().size()+1,name)
                            Node nodeToInsert = new Node(toInsert)
                            rootNode.addChild(nodeToInsert)
                        }
                        else{
                            Node parentNode = Node.searchNodeById(rootNode,id)
                            VIPD toInsert = new VIPD(parentNode.getChildren().size()+1,name)
                            Node nodeToInsert = new Node(toInsert)
                            parentNode.addChild(nodeToInsert)
                        }
                    }
                }
            }
            return HttpStatus.OK
        }
        else{
            return HttpStatus.BAD_REQUEST
        }
    }

    def doTheMath(GrailsParameterMap params){
        ArrayList<PairwaiseMatrix> criteriaMatrices = []
        ArrayList<PairwaiseMatrix> alternativeMatrices = []
        params.keySet().each { key ->
            def keyOnArray = key.toString().split("-")
            ArrayList<Double> values = []
            params.get(key).each { value ->
                values.push(Double.parseDouble(value.toString()))
            }
            PairwaiseMatrix tempMatrix = new PairwaiseMatrix(keyOnArray[1],(ArrayList<String>) keyOnArray[2].substring(1,keyOnArray[2].length()-1).split(","),values)
            if(keyOnArray[0].contains("ava")){
                alternativeMatrices.push(tempMatrix)
            }
            else if(keyOnArray[0].toString().contains("cvc")){
                criteriaMatrices.push(tempMatrix)
            }
        }
        return getTheResults(criteriaMatrices,alternativeMatrices)
    }

    def getTheResults(ArrayList<PairwaiseMatrix> criteriaMatrices,ArrayList<PairwaiseMatrix> alternativeMatrices){
        HierarchyTree hierarchyTree = HierarchyTree.getHierarchyTree()
        criteriaMatrices.each { criteriaMatrix ->
            Map<String,Double> priorityVector =  getPriorityMap(criteriaMatrix)
            priorityVector.each { key, value ->
                key = key.replace(" ","")
                def n = HierarchyTree.search(hierarchyTree.getRoot(),key)
                if(n != null)
                    n.getData().setLocalPriority(value)

            }
        }
        HierarchyTree.setGlobalPriorities()
        Map<String,Double> globalPriorityMap = HierarchyTree.getGlobalPriorityMap()
        Matrix globalPriorityVector = transformToMatrix(globalPriorityMap)
        alternativeMatrices = orderAlternativeMatrices(alternativeMatrices)
        Matrix matrixOfAlternativePriorities = new Matrix(fillMatrixOfPriorityAlternativeVectors(alternativeMatrices))
        Matrix resultsVector = matrixOfAlternativePriorities.transpose().times(globalPriorityVector)

        Map<String,Double> resultsMap = [:]
        Iterator listIterator = hierarchyTree.getAlternatives().iterator()
        int cont = 0
        while(listIterator.hasNext()){
            resultsMap.put(listIterator.next(),resultsVector.get(cont,0))
            cont++
        }
        ValueComparator bvc = new ValueComparator(resultsMap)
        TreeMap<String,Double> sortedMap = new TreeMap<>(bvc)
        sortedMap.putAll(resultsMap)
        return sortedMap
    }

    def normalizeMatrix(PairwaiseMatrix matrix) {
        Matrix tempMatrix = matrix.getMatrix()
        return tempMatrix.normalize1Matrix()
    }

    def getPriorityMap(PairwaiseMatrix matrix){
        Map<String,Double> mapToReturn = [:]
        Matrix normalizeMatrix = new Matrix(this.normalizeMatrix(matrix))
        double[] averageVector = normalizeMatrix.getAverageVector()
        int counter = 0
        matrix.getItems().each { item ->
            mapToReturn.put(item,averageVector[counter])
            counter++
        }
        return mapToReturn
    }

    def transformToMatrix(Map<String,Double> mapToTransform){
        double[] vectorFromMap = (Double[]) mapToTransform.values().toArray()
        double[][] matrixToReturn = new double[vectorFromMap.size()][1]

        for(int i = 0; i< vectorFromMap.size();i++){
            double[] temp = [ vectorFromMap[i] ]
            matrixToReturn[i] = temp
        }
        return new Matrix(matrixToReturn)
    }


    def fillMatrixOfPriorityAlternativeVectors(ArrayList<PairwaiseMatrix> alternativeMatrices){
        double[][] newMatrix = new double[alternativeMatrices.size()][alternativeMatrices.get(0).getItems().size()]
        for(int i = 0; i<alternativeMatrices.size(); i++){
            newMatrix[i] = (Double[]) getPriorityMap(alternativeMatrices.get(i)).values().toArray()
        }
        return newMatrix
    }

    def orderAlternativeMatrices(List<PairwaiseMatrix> alternativeMatrices){
        return HierarchyTree.seekAndOrderTheList(alternativeMatrices)
    }

    private static buildPairwaiseComparison(HierarchyTree hierarchy,ArrayList<String> alternatives){
        return HierarchyTree.buildPairwaiseComparison(alternatives)
    }

    private static List getSortedKeysOfInputs(Set keys){
        List sortedKeys = new ArrayList(keys)
        Collections.sort(sortedKeys)
        return sortedKeys
    }

    private static String obtainIdFromInput(String value){
        String id = ""
        for(int i = 0; i < value.length(); i ++){
            id += value[i]
            if(value[i] == '-'){
                break
            }
        }
        if(!id.contains('.'))
            return ""
        int index = 0
        for(int i = id.length()-1; i >= 0; i--){
            index++
            if(id[i] == '.'){
                break
            }
        }

        return id.substring(0,id.length()-index)
    }
}
