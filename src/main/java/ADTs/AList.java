package ADTs;

public class AList<T> {
    Node<T> head;

    public AList() {
        head = null;
    }

    public void add(T data){
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
    }

    public Node<T> getHead(){
        return head;
    }
}
