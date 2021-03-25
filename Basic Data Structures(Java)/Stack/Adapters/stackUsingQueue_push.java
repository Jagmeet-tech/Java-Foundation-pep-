import java.util.Queue;
import java.util.LinkedList;

public class stackUsingQueue_push {
    /*Queue is interface in java which has abstract methods which is implemented by LinkedList class (ll implementation).
      for array implementation of Queue ,use ArrayDequeue class(array implement).
      And we know we can create reference variable of interface and make object of that class which implements that interface.
    */
    
    Queue<Integer> que=new LinkedList<>();  
    Queue<Integer> temp=new LinkedList<>();

    int peekEle=0;
    public int size(){
        return this.que.size();
    }
    
    public boolean isEmpty(){
        return this.que.size()==0;
    }

    //O(1)
    public void push(int data){
        this.peekEle=data;
        this.que.add(data);
    }
    
    //O(1)
    public int top(){
        return this.peekEle;
    }

    //O(n)
    public int pop(){
        if(isEmpty())
            return -1;
        while(this.size()!=1){
            this.temp.add(this.que.remove());
        }
        int rdata=this.que.remove();
        while(this.temp.size()!=0){
            this.push(this.temp.remove());
        }    
        return rdata;
    }

}