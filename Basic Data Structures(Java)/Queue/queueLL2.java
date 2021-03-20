public class queueLL2{
    private class Node{
        int data=0;
        Node next=null;
        
        Node(int data){
            this.data=data;
        }
    }
    private Node head=null;
    private Node tail=null;
    private int elementCount=0;

    private void addLast(int data){
        Node node=new Node(data);
        if(isEmpty()){
            this.head=this.tail=node;
        }else{
            this.tail.next=node;
            this.tail=node;
        }
        this.elementCount++;
    }

    private int removeFirst(){
        Node node=this.head;
        if(this.size()==1){
            this.head=this.tail=null;
        }else{
            this.head=node.next;
        }
        node.next=null;
        this.elementCount--;
        return node.data;
}

    public int size() {
        return elementCount;
    }

    public boolean isEmpty() {
        return elementCount == 0;
    }

    public void push(int data) {
        addLast(data);
    }

    public int front() {
        return this.head.data;
    }

    public int pop() {
        return removeFirst();
    }
}