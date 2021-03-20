public class client{
   /* public static void main(String[] args)throws Exception{
        stack st=new stack(5);
        for(int i=1;i<6;i++)
            st.push(i*10);
        System.out.print(st);    
        while(st.size()!=0)
            System.out.println(st.pop());        
    }*/

   /* public static void main(String[] args) throws Exception {
        // int[] arr = { 10, 20, 30, 40, 50, 60, 70 };
        dynamicStack st = new dynamicStack(5);
        for (int i = 1; i <= 10; i++)
            st.push(i * 10);

        System.out.println(st);
    }*/
    public static void main(String[]args)throws Exception{
        stackLL st=new stackLL();
        for(int i=1;i<6;i++)
            st.push(i*10);
        System.out.println(st.top());    
        for(int i=1;i<6;i++)
            System.out.println(st.pop());   
    } 
}