public class dynamicStack extends stack{

    public dynamicStack(){
        super();
    }
    public dynamicStack(int size){
        super(size);
    }
    public dynamicStack(int []arr){
        super.intializeVariables(2*arr.length);
        for(int ele:arr)
            super.push_(ele);
    }

    @Override
    public void push(int data)throws Exception{
        if(super.capacity == super.elementCount){
            int temp[]=super.arr; //copy old data
            super.intializeVariables(2*super.capacity); //allocate new array 
            for(int ele:temp)  //copying value to new array
                super.push_(ele);
        } 
        super.push(data); //adding data by checking exceptions also for overflow
    }

}
