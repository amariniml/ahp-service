package ahp.service

import Jama.Matrix
import ahp.utils.HierarchyTree
import ahp.utils.PairwaiseMatrix
import ahp.utils.ValueComparator


class AHPToolService {

    def mathService

    def getSortedKeysOfInputs(Set keys){
        List sortedKeys = new ArrayList(keys)
        sortedKeys.remove('controller')
        sortedKeys.remove('format')
        sortedKeys.remove('action')
        Collections.sort(sortedKeys)
        return sortedKeys
    }

    def getTheResults(ArrayList<PairwaiseMatrix> criteriaMatrices,ArrayList<PairwaiseMatrix> alternativeMatrices){
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

    def removeUselessKeys(Set keys){
        keys.remove('controller')
        keys.remove('format')
        keys.remove('action')
        return keys
    }


    def fillMatrixOfPriorityAlternativeVectors(ArrayList<PairwaiseMatrix> alternativeMatrices){
        double[][] newMatrix = new double[alternativeMatrices.size()][alternativeMatrices.get(0).getItems().size()]
        for(int i = 0; i<alternativeMatrices.size(); i++){
            newMatrix[i] = (Double[]) mathService.getPriorityMap(alternativeMatrices.get(i)).values().toArray()
        }
        return newMatrix
    }

    def orderAlternativeMatrices(List<PairwaiseMatrix> alternativeMatrices){
        return HierarchyTree.seekAndOrderTheList(alternativeMatrices)
    }
}
