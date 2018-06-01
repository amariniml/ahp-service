package ahp.service

import Jama.Matrix
import ahp.utils.PairwaiseMatrix

class MathService {

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


}
