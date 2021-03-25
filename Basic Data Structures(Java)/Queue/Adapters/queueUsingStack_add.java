import java.util.Stack;
public class queueUsingStack_add{
    Stack<Integer> st=new Stack<>();
    Stack<Integer> temp=new Stack<>();

    int peekVal=0;
    public int size(){
        return this.st.size();
    }

    public boolean isEmpty(){
        return this.st.size()==0;
    }

    //O(1)
    public void add(int data){
        if(this.size()==0) //because stack last value is the peek value in Queue. 
            this.peekVal=data;
        this.st.push(data);
    }

    //O(1)
    public int peek(){
        if(st.size()==0){
            System.out.println("Queue underflow");
            return -1;
        }
        return this.peekVal;
    }

    public void transferToAnotherStack(){
        while(st.size()!=0){
            temp.push(st.pop());
        }
    }

    //O(n)
    public int remove(){
        if(st.size()==0){
            System.out.println("Queue underflow");
            return -1;
        }
        transferToAnotherStack();
        int rData=this.temp.pop();
        while(temp.size()!=0){
            st.add(temp.pop());
        }
        return rData;
    }
}