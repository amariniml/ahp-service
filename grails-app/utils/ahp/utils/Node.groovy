package ahp.utils

class Node<T> {

    private T data = null
    private List<Node<T>> children = new ArrayList<>()
    private Node<T> parent = null

    Node(T data) {
        this.data = data
    }

    Node<T> addChild(Node<T> child) {
        child.setParent(this)
        this.children.add(child)
        return child
    }

    void addChildren(List<Node<T>> children) {
        children.each {each -> each.setParent(this)}
        this.children.addAll(children)
    }

    List<Node<T>> getChildren() {
        return children
    }

    T getData() {
        return data
    }

    void setData(T data) {
        this.data = data
    }

    private void setParent(Node<T> parent) {
        this.parent = parent
    }

    Node<T> getParent() {
        return parent
    }

    static <T> void printTree(Node<T> node, String appender) {
        System.out.println(appender + ((VIPD) node.getData()).getDescription())
        node.getChildren().each { each ->  printTree(each, appender + appender) }
    }

    static <T> Node searchNodeById(Node<T> node,String idString){
        if(idString == '')
            return node
        String id = idString[0]
        Iterator childIterator = node.getChildren().iterator()
        while(childIterator.hasNext()){
            Node nodeToReturn = childIterator.next()
            if(((VIPD) nodeToReturn.getData()).getId() == id.toInteger()){
                if(idString.length()==1)
                    return searchNodeById(nodeToReturn,"")
                return searchNodeById(nodeToReturn,idString.substring(2))
            }
        }
        return node
    }
}

