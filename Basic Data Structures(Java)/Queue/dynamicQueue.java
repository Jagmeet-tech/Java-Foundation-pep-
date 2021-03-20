public class dynamicQueue extends Queue{

    public dynamicQueue(){
        super();
    }
    public dynamicQueue(int size){
        super(size);
    }
    public dynamicQueue(int []arr){
        super.intializeVariables(2 * arr.length);
        for(int ele:arr)
            super.push_(ele);      
    }

    @Override
    public void push(int data)throws Exception{
        if(super.capacity == super.elementCount){
            int f=this.front;
            int n=this.capacity;
            int temp[]=super.arr;
            super.intializeVariables(2 * n);
            for(int i=0;i<n;i++){
                int idx=(i+f) % n; //to cover the overflow case.
                super.push_(temp[idx]);
            }
        }
        super.push(data); //this push in Queue class checks the exception also but in above push_ in line 24 we increase the size so no need to check exception.
    }
}