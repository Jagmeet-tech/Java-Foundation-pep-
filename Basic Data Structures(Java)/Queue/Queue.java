public class Queue{
    protected int arr[]=null;
    protected int front=0;
    protected int back=0;
    protected int elementCount=0; //No of elements present in array.
    protected int capacity=0;  // Maximum size of array.

    //constructors==============================================================

    public void intializeVariables(int capacity){
        this.capacity=capacity;
        this.arr=new int[this.capacity];
        this.elementCount=0;
        this.front=0;
        this.back=0;
    }
    public Queue(){
        intializeVariables(10);  //default capacity 10.
    }
    public Queue(int size){
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
            int idx=(i+ this.front) % this.capacity;
            sb.append(this.arr[idx]);
            if(i!=this.elementCount-1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString(); //converted StringBuilder(which is array of characters) to String. 
    }
    
    //Exceptions==================================================================
    
    private void overflowException() throws Exception {
        if(this.capacity==this.elementCount)
            throw new Exception("Queue Is Full..."); 
    }
    
    private void underflowException() throws Exception {
        if(this.elementCount==0)
            throw new Exception("Queue Is Empty..."); 
    }

    //Queue functions==========================================================

    protected void push_(int data){
        this.arr[this.back]=data; //ismein back pehle hi incremented hai uski value hi 0 se strt hai bina value insert kiye toh increment alag se ni krna.
        this.elementCount++;
        this.back=(this.back + 1) % this.capacity; //to cover the overflow case.
    }
    public void push(int data) throws Exception{ 
        overflowException();
        push_(data);
    }

    public int front()throws Exception{
        underflowException();
        return this.arr[this.front];
    }

    protected int pop_(){
       int del=this.arr[this.front];
       this.arr[this.front]=0;
       this.elementCount--;
       this.front=(this.front + 1) % this.capacity; //to cover the overflow case.
       return del;
    }
    public int pop() throws Exception{
        underflowException();
        return pop_();
    }
}