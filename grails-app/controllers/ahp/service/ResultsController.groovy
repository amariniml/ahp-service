package ahp.service

import Jama.Matrix
import ahp.utils.HierarchyTree
import ahp.utils.PairwaiseMatrix
import ahp.utils.VIPD
import ahp.utils.ValueComparator


class ResultsController {
    AHPToolService AHPToolService
    MathService mathService

    def index() { }

    def doTheMath() {
        Set keys = AHPToolService.removeUselessKeys(params.keySet())
        ArrayList<PairwaiseMatrix> criteriaMatrices = []
        ArrayList<PairwaiseMatrix> alternativeMatrices = []
        keys.each { key ->
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

//        TODO: esto seria del service AHP me parece. osea a partir de aca para abajo
        HierarchyTree hierarchyTree = HierarchyTree.getHierarchyTree()
        criteriaMatrices.each { criteriaMatrix ->
            Map<String,Double> priorityVector =  mathService.getPriorityMap(criteriaMatrix)
            priorityVector.each { key, value ->
                key = key.replace(" ","")
                def n = HierarchyTree.search(hierarchyTree.getRoot(),key)
                if(n != null)
                    n.getData().setLocalPriority(value)

            }
        }
        HierarchyTree.setGlobalPriorities()
        Map<String,Double> globalPriorityMap = HierarchyTree.getGlobalPriorityMap()
        Matrix globalPriorityVector = mathService.transformToMatrix(globalPriorityMap)
        alternativeMatrices = AHPToolService.orderAlternativeMatrices(alternativeMatrices)
        Matrix matrixOfAlternativePriorities = new Matrix(AHPToolService.fillMatrixOfPriorityAlternativeVectors(alternativeMatrices))
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
        render(view:"seeTheResults",model: [resultsVector:sortedMap])

    }
}
