public class Recursion
{
    public static void displayArray(int[] arr, int idx) {
        if (idx == arr.length)
            return;
        System.out.println(arr[idx]);
        displayArray(arr, idx + 1);
    }
    public static void revdisplayArray(int[] arr, int idx) {
        if (idx == arr.length)
            return;
        revdisplayArray(arr, idx + 1);
        System.out.println(arr[idx]);
    }
    public static int tribonacciSer(int n){   
        if(n<=2)
            return n==2?1:n;
        return(tribonacciSer(n-1)+tribonacciSer(n-2)+tribonacciSer(n-3));    
    }
    public static int maxOfarray(int arr[], int idx) {
        if (idx == arr.length)
            return (int) - 1e9;
        int max = maxOfarray(arr, idx + 1);
        return (Math.max(max, arr[idx]));
    }
    public static int firstIndex(int arr[], int idx, int data) {
        if (idx == arr.length)
            return -1;
        if (arr[idx] == data)
            return idx;
        return (firstIndex(arr, idx + 1, data));
    }
    public static int lastIndex(int arr[], int idx, int data) {
        if (idx == arr.length)
            return -1;
        int i = lastIndex(arr, idx + 1, data);
        if (arr[idx] == data && i == -1)  //check i condition so that if next occurence will occur then idx should not update.
            return idx;
        else
            return i;
    }
    public static Arraylist<int> indices(int arr[],int idx,int data)
    {
        
        if(arr[idx]==data)
        {
            Arraylist<int> a=new Arraylist<>();
            return a.add(idx);
        }
            
        return a    
    }
    public static int[] inverseArray(int arr[],int inv[],int idx)
    {
         if(idx==arr.length)
             return inv;
         inv[arr[idx]]=idx;
         return(inverse(arr,inv,idx+1));
    }

    public static int sumOfDigits(StringBuilder s,int idx,int endx)
    {
        if(idx==endx) return 0;
        int no=s.charAt(idx)-'0';// because if we dont - a character with it then it stores Ascii value .
         // System.out.println(no);
        return(no+sumOfDigits(s,idx+1,endx));
    }
    public static boolean stringReverse(char[] s1,int idx,int endx,String s2)
    {
        if(idx>endx) 
        {
            //System.out.println(s1.toString());
            if(String.valueOf(s1).equals(s2))
            //if((s1.toString()).equals(s2))    
             return true;
            else
                return false;
        }
        char temp=s1[idx];
        s1[idx]=s1[endx];
        s1[endx]=temp;
        return(stringReverse(s1,idx+1,endx-1,s2));
    }
    public static void stringToInt(StringBuilder s,int idx,int endx)
    {
        int pow=10;
        if(idx==endx){
            pow=1;
            return 1;
        }
        int val=s.charAt(idx)-'0';
        val*pow+stringToInt(s,idx+1,endx);
        pow*=10;
    }
    public static void main(String[] args) throws Exception {
        Scanner scn=new Scanner(System.in);
        int arr[]=new int[scn.nextInt()];
        for(int i=0;i<arr.length;i++)
            arr[i]=scn.nextInt();
        int data=scn.nextInt();    
        System.out.println(firstIndex(arr,0,data));
    }
}