public class client{
     /*public static void main(String[] args)throws Exception{
         Queue st=new Queue(5);
         for(int i=1;i<6;i++)
             st.push(i*10);
         System.out.println(st);    
         while(st.size()!=0)
             System.out.println(st.pop());        
     }*/
 
     /* public static void main(String[] args) throws Exception {
        dynamicQueue que = new dynamicQueue(6);
        for (int i = 0; i < 15; i++)
            que.push(i * 100);

        System.out.println(que.pop());
        System.out.println(que.pop());
        System.out.println(que.pop());
        System.out.println(que.pop());
        System.out.println(que.pop());
        System.out.println(que);
    }*/

    /*public static void main(String[]args)throws Exception{
        queueLL1 st=new queueLL1();
        for(int i=1;i<6;i++)
            st.push(i*10);
        System.out.println(st.front());    
        for(int i=1;i<6;i++)
            System.out.println(st.pop());   
    }*/
     
    public static void main(String[]args)throws Exception{
        queueLL2 st=new queueLL2();
        for(int i=1;i<6;i++)
            st.push(i*10);
        System.out.println(st.front());    
        for(int i=1;i<6;i++)
            System.out.println(st.pop());   
    }
    
 }