package ahp.service

import ahp.utils.*

class CreateController {

    def createHierarchy() {

//        HierarchyTree<String> robocop = new HierarchyTree("hola")
//        robocop.getRoot().addChild(new Node<String>("chau1"))
//        robocop.getRoot().addChild(new Node<String>("chau2"))
//        Node.printTree(robocop.getRoot(),"  ")

        List sortedKeys = getSortedKeysOfInputs(params.keySet())
        sortedKeys.each { key -> println "${key}-${params.get(key)}" }

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


}
