package ahp.utils

class HierarchyTree<T> {
    private Node<T> root

    HierarchyTree(T rootData){
        root = new Node<T>()
        root.data = rootData
        root.addChildren(new ArrayList<Node<T>>())
    }

    Node<T> getRoot() {
        return root
    }

    void setRoot(Node<T> root) {
        this.root = root
    }
}