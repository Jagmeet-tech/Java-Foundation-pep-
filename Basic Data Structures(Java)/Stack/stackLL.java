import java.util.LinkedList;
public class stackLL{
    //predefined linkedlist in collection framework (by default Doubly LL hoti hai) 
    
    private LinkedList<Integer> ll= new LinkedList<>();  //It include all exceptions only we have implemented fns which impacts that ll behave as stack. 

    public int size(){
        return ll.size();
    }

    public boolean isEmpty(){
        return ll.size()==0;
    }

    public void push(int data){
        ll.addFirst(data);
    }

    public int top(){
        return ll.getFirst();
    }
    public int pop(){
        return ll.removeFirst();
    }
}