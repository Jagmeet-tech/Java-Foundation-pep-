import java.util.Queue;
import java.util.LinkedList;

public class stackUsingQueue_pop {
    /*Queue is interface in java which has abstract methods which is implemented by LinkedList class (ll implementation).
      for array implementation of Queue ,use ArrayDequeue class(array implement).
      And we know we can create reference variable of interface and make object of that class which implements that interface.
    */
    
    Queue<Integer> que=new LinkedList<>();  
    Queue<Integer> temp=new LinkedList<>();

    int peekEle=0;//it is not required as as we pop the first element then peekELe cant change to next value ,so we use peek() fn.
    public int size(){
        return this.que.size();
    }
    
    public boolean isEmpty(){
        return this.que.size()==0;
    }

    //O(n)
    public void push(int data){
        while(this.size()!=0){
            this.temp.add(this.que.remove());
        }
        this.que.add(data);
        while(this.temp.size()!=0){
            this.que.add(this.temp.remove());
        }
    }
    //O(1)
    public int top(){
        return this.que.peek();
    }

    //O(1)
    public int pop(){
     return this.que.remove();
    }

}