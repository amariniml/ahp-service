package ahp.service

import Jama.Matrix
import ahp.utils.HierarchyTree
import ahp.utils.PairwaiseMatrix



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
