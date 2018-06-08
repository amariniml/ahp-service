package ahp.service

import ahp.utils.*
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.http.HttpStatus

class AHPController {
    AHPToolService AHPToolService

    def getPairwaseMatrices() {
        String statusCode = AHPToolService.createHierarchy(removeUselessKeys(params))
        if (statusCode == "200") {
            JSONObject mapPairwaise = AHPToolService.generateMatrices()
            render(view: "matrixGeneration", model: [pairwaiseMatrixList: mapPairwaise])
        } else {
            return HttpStatus.BAD_REQUEST
        }
    }

    def getTheResultMap() {
        TreeMap<String,Double> resultsMap = AHPToolService.doTheMath(removeUselessKeys(params))
        render(view:"seeTheResults",model: [resultsVector:resultsMap])

    }

    private static GrailsParameterMap removeUselessKeys(GrailsParameterMap map){
        map.remove('controller')
        map.remove('format')
        map.remove('action')
        return map
    }


}