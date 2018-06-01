package ahp.service

import ahp.utils.*
import org.springframework.http.HttpStatus

class CreateController {
    AHPToolService AHPToolService

    def createHierarchy() {

        if(params.containsKey('goal')){
            VIPD root = new VIPD(0,(String) params.get('goal'))
            HierarchyTree<VIPD> hierarchy = HierarchyTree.getHierarchyTree(root,(ArrayList<String>) params.get("alternative"),true)
            List sortedKeys = AHPToolService.getSortedKeysOfInputs(params.keySet())
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
            createComparisonMatrixList(hierarchy,(ArrayList<String>) params.get("alternative"))
        }
        else{
            return HttpStatus.BAD_REQUEST
        }
    }


    def createComparisonMatrixList(HierarchyTree hierarchy, ArrayList<String> alternatives){
        Map<String,ArrayList<PairwaiseMatrix>> listPairwaise = HierarchyTree.buildPairwaiseComparison(hierarchy,alternatives)
        render(view:"matrixGeneration",model: [pairwaiseMatrixList:listPairwaise])
    }

    def comparision(){

    }



    String obtainIdFromInput(String value){
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