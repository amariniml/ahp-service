package ahp.utils



class HierarchyTree<T> {
    private Node<T> root
    private ArrayList<String> alternatives
    private static HierarchyTree hierarchyTree = null

    static HierarchyTree getHierarchyTree(T rootData = null,ArrayList<String> alternatives = null,boolean firstTime = false){
        if(hierarchyTree == null || firstTime){
            hierarchyTree = new HierarchyTree(rootData,alternatives)
        }
        return hierarchyTree
    }


    private HierarchyTree(T rootData,ArrayList<String> alternatives){
        root = new Node<T>()
        root.data = rootData
        root.addChildren(new ArrayList<Node<T>>())
        this.alternatives = alternatives
    }

    Node<T> getRoot() {
        return root
    }

    void setRoot(Node<T> root) {
        this.root = root
    }

    static Node<VIPD> search(Node<VIPD> node,String criteria){
        Node n = null
        if(node.getData().getDescription().contains(criteria))
            return node
        if(node.getChildren().size() > 0){
            Iterator childrenIterator = node.getChildren().iterator()
            while(childrenIterator.hasNext()){
                Node child = childrenIterator.next()
                n = search(child,criteria)
                if(n!=null)
                    break
            }
        }
        return n

    }

    static Map<String,ArrayList<PairwaiseMatrix>> buildPairwaiseComparison(ArrayList<String> alternatives){
        Map<String,ArrayList<PairwaiseMatrix>> returnMap = [:]
        ArrayList<PairwaiseMatrix> returnListCriteria = new ArrayList()
        ArrayList<PairwaiseMatrix> returnListAlternative = new ArrayList()


        runOnTree(hierarchyTree.getRoot(),returnListCriteria,returnListAlternative,alternatives)
        returnMap.CRITERIA = returnListCriteria
        returnMap.ALTERNATIVE = returnListAlternative
        return returnMap
    }

    private static void buildAndPushPairwaiseCriteriaMatrix(Node node, ArrayList<PairwaiseMatrix> returnList){
        List<Node> childrenOfNode = node.getChildren()
        ArrayList<String> items = new ArrayList()
        for(child in childrenOfNode){
            items.push(((VIPD) child.getData()).getDescription())
        }
        PairwaiseMatrix matrixToPush = new PairwaiseMatrix(( (VIPD) node.getData()).getDescription(),items)
        returnList.push(matrixToPush)
    }

    private static void buildAndPushPairwaiseAlternativeMatrix(Node node, ArrayList<PairwaiseMatrix> returnList,ArrayList<String> alternatives){
        PairwaiseMatrix matrixToPush = new PairwaiseMatrix(( (VIPD) node.getData()).getDescription(),alternatives)
        returnList.push(matrixToPush)
    }

    private static void runOnTree(Node node, ArrayList<PairwaiseMatrix> returnListCriteria,ArrayList<PairwaiseMatrix> returnListAlternative,ArrayList<String> alternatives){
        List<Node> childrenOfNode = node.getChildren()
        if(childrenOfNode.size()>0){
            buildAndPushPairwaiseCriteriaMatrix(node,returnListCriteria)
            childrenOfNode.each { each -> runOnTree(each,returnListCriteria,returnListAlternative,alternatives)}
        }
        else{
            buildAndPushPairwaiseAlternativeMatrix(node,returnListAlternative,alternatives)
        }
    }

    static void setGlobalPriorities(){
        hierarchyTree.getRoot().getChildren().each { child ->
            ((VIPD) child.getData()).setGlobalPriority(((VIPD) child.getData()).getLocalPriority())
            seekAndSetGlobalPriority(child)
        }

    }
    static private void seekAndSetGlobalPriority(Node<VIPD> node){
        if(node.getChildren().size() > 0){
            node.getChildren().each { child ->
                child.getData().setGlobalPriority(child.getData().getLocalPriority() * node.getData().getGlobalPriority())
                seekAndSetGlobalPriority(child)
            }
        }
    }

    static List<PairwaiseMatrix> seekAndOrderTheList(List<PairwaiseMatrix> listToSearch){
        List<PairwaiseMatrix> listToReturn = []
        seekAndOrderTheListMethod(hierarchyTree.getRoot(),listToSearch,listToReturn)
        return listToReturn
    }

    private static void seekAndOrderTheListMethod(Node<VIPD> node,List<PairwaiseMatrix> listToSearch,List<PairwaiseMatrix> listToReturn){
        if(node.getChildren().size() > 0){
            node.getChildren().each { child ->
                seekAndOrderTheListMethod(child,listToSearch,listToReturn)
            }
        }
        else{
            Iterator listIterator = listToSearch.iterator()
            while(listIterator.hasNext()){
                PairwaiseMatrix matrixToPush = listIterator.next()
                if(matrixToPush.getCompareTo() == node.getData().getDescription()){
                    listToReturn.push(matrixToPush)
                }
            }
        }
    }

    static Map<String,Double> getGlobalPriorityMap(){
        Map<String,Double> mapToReturn = [:]
        seekAndSetGlobalPriority(hierarchyTree.getRoot(),mapToReturn)
        return mapToReturn
    }


    static private void seekAndSetGlobalPriority(Node<VIPD> node, Map<String,Double> mapToReturn){
        if(node.getChildren().size() > 0){
            node.getChildren().each { child ->
                seekAndSetGlobalPriority(child,mapToReturn)
            }
        }
        else{
            mapToReturn.put(node.getData().getDescription(),node.getData().getGlobalPriority())
        }
    }

    ArrayList<String> getAlternatives() {
        return alternatives
    }

    void setAlternatives(ArrayList<String> alternatives) {
        this.alternatives = alternatives
    }
}