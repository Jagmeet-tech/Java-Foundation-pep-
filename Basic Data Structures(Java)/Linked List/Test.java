import java.util.Scanner;
import java.io.IOException;
public class Test{
    public static Scanner scn=new Scanner(System.in);
    public static int menu(){
        System.out.println("1.Add node at first:");
        System.out.println("2.Add node at last:");
        System.out.println("3.Add node between:");
        System.out.println("4.Remove first node:");
        System.out.println("5.Remove last node:");
        System.out.println("6.Remove between node:");
        System.out.println("7.Get first node:");
        System.out.println("8.Get last node:");
        System.out.println("9.Get between node:");
        System.out.println("10.Display linked list:");
        System.out.println("11.Exit:");
        System.out.println("Enter your choice:");
        int n=scn.nextInt();
        return n;
    }
    public static void main(String []args)throws Exception {
        linkedList ll=new linkedList();
        int data,i;
        while(true){
            int n=menu();
            switch(n){
                case 1:
                    System.out.println("Enter the first data:");
                    data=scn.nextInt();
                    ll.addFirst(data);
                    break;
                case 2:
                    System.out.println("Enter the last data:");
                    data=scn.nextInt();
                    ll.addLast(data);
                    break; 
                case 3:
                    System.out.println("Enter the index and data:");
                    i=scn.nextInt();
                    data=scn.nextInt();
                    ll.addAt(i,data);
                    break;
                case 4:
                    System.out.println(ll.removeFirst());
                    break;
                case 5:
                    System.out.println(ll.removeLast());
                    break;
                case 6:
                    System.out.println("Enter the index:");
                    i=scn.nextInt();
                    System.out.println(ll.removeAt(i));
                    break;
                case 7:
                    System.out.println(ll.getFirst());
                    break;
                case 8:
                    System.out.println(ll.getLast());
                    break;
                case 9:
                    System.out.println("Enter the index:");
                    i=scn.nextInt();
                    System.out.println(ll.getAt(i));
                    break; 
                case 10:
                    ll.display();
                    break;                    
                case 11:
                    System.exit(0);
            }
            /*to clear screen
            try
            {  
                final String os = System.getProperty("os.name");  
                if (os.contains("Windows"))  
                    {  
                        Runtime.getRuntime().exec("cls");  
                    }  
            }  
            catch (final Exception e)  
            {  
                e.printStackTrace();  
            } */ 
        }
    }
}