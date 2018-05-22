package ahp.service

import ahp.utils.*
import org.springframework.http.HttpStatus

class CreateController {

    def createHierarchy() {

        if(params.containsKey('goal')){
            VIPD root = new VIPD(0,params.get('goal'))
            HierarchyTree<VIPD> hierarchy = new HierarchyTree<>(root)
            List sortedKeys = getSortedKeysOfInputs(params.keySet())
            Node rootNode = hierarchy.getRoot()
            sortedKeys.each { key ->
                if(key == 'criteria'){
                    params.get(key).each { value ->
                        def id =  obtainIdFromInput(value.toString())
                        if(id == ''){
                            VIPD toInsert = new VIPD(rootNode.getChildren().size()+1,value)
                            Node nodeToInsert = new Node(toInsert)
                            rootNode.addChild(nodeToInsert)
                        }
                        else{
                            Node parentNode = Node.searchNodeById(rootNode,id)
                            VIPD toInsert = new VIPD(parentNode.getChildren().size()+1,value)
                            Node nodeToInsert = new Node(toInsert)
                            parentNode.addChild(nodeToInsert)
                        }
                    }
                }
            }
            Node.printTree(hierarchy.getRoot(),"   ")
        }
        else{
            return HttpStatus.BAD_REQUEST
        }
//        HierarchyTree<String> robocop = new HierarchyTree("hola")
//        robocop.getRoot().addChild(new Node<String>("chau1"))
//        robocop.getRoot().addChild(new Node<String>("chau2"))
//        Node.printTree(robocop.getRoot(),"  ")



    }

//    mentira este método aún no se como implemetnarlo, seria para obtener las matrices
//    def generateMatrix() {
//        JSONObject json = request.getJSON();
//        ArrayList level = (json.NIVEL)? json.NIVEL : null
//
//        while(level.iterator().hasNext()){e
//            log.info(level.iterator().next())
//        }
//    }

    def comparision(){

    }

    def getSortedKeysOfInputs(Set keys){
        List sortedKeys = new ArrayList(keys)
        sortedKeys.remove('controller')
        sortedKeys.remove('format')
        sortedKeys.remove('action')
        Collections.sort(sortedKeys)
        return sortedKeys
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
