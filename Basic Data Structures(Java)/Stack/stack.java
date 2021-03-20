public class stack{
    protected int arr[]=null;
    protected int tos=-1;  //top of stack
    protected int elementCount=0; //No of elements present in array.
    protected int capacity=0;  // Maximum size of array.

    //constructors==============================================================

    public void intializeVariables(int capacity){
        this.capacity=capacity;
        this.arr=new int[this.capacity];
        this.elementCount=0;
        this.tos=-1;

    }
    public stack(){
        intializeVariables(10);  //default capacity 10.
    }
    public stack(int size){
        intializeVariables(size);
    }
    // basic functions===========================================================
    public int size(){
        return this.elementCount;
    }
    public boolean isEmpty(){
        return this.elementCount==0;
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder(); //created because i want to display it like arraylist display fn. by using name only so i override toString() method.  
        sb.append("[");
        for(int i=0;i<this.elementCount;i++){
            sb.append(this.arr[i]);
            if(i!=this.elementCount-1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString(); //converted StringBuilder(which is array of characters) to String. 
    }
    
    //Exceptions==================================================================
    
    private void overflowException() throws Exception {
        if(this.capacity==this.elementCount)
            throw new Exception("Stack Is Full..."); 
    }
    
    private void underflowException() throws Exception {
        if(this.elementCount==0)
            throw new Exception("Stack Is Empty..."); 
    }

    //Stack functions==========================================================

    protected void push_(int data){
        this.arr[++this.tos]=data;
        this.elementCount++;
    }
    public void push(int data) throws Exception{ 
        overflowException();
        push_(data);
    }

    public int top()throws Exception{
        underflowException();
        return this.arr[this.tos];
    }

    protected int pop_(){
        int del=this.arr[this.tos];
        this.arr[this.tos--]=0;
        this.elementCount--;
        return del;
    }
    public int pop() throws Exception{
        underflowException();
        return pop_();
    }
}