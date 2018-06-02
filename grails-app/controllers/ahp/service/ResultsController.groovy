package ahp.service



import ahp.utils.PairwaiseMatrix



class ResultsController {
    AHPToolService AHPToolService

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
        TreeMap<String,Double> resultsMap = AHPToolService.getTheResults(criteriaMatrices,alternativeMatrices)
        render(view:"seeTheResults",model: [resultsVector:resultsMap])

    }
}
