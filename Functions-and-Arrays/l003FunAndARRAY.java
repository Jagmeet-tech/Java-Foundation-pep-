import java.util.Scanner;
public class l003FunAndARRAY
{
    public static Scanner scn =new Scanner(System.in);
    public static int digitsFrequency(int no, int d) {
        int count = 0;
        while (no != 0) {
            int temp = no % 10;
            no /= 10;
            if (temp == d)
                count++;
        }
        return count;
    }
    public static long decimalToAnyBase(long no, long b) {
        long rem = 0, res = 0;
        int pow = 1;
        while (no != 0) {
            rem = no % b;
            no /= b;
            res += rem * pow;
            pow *= 10;
        }
        return res;
    }
    public static long anyBaseTodecimal(long no, long b) {
        long rem = 0, res = 0;
        int pow = 1;
        while (no != 0) {
            rem = no % 10;
            no /= 10;
            res += rem * pow;
            pow *= b;
        }
        return res;
    }
    public static long anyBaseToanyBase(long no,long b1,long b2)
    {
        long num=anyBaseTodecimal(no,b1);
        long res=decimalToAnyBase(num,b2);
        return res;
        //here we can use method of pipelining also(|) ,abtd:anybase to decimal
        //command1:javac abtd.java && javac dtab.java  
        //command2:java abtd <input.txt | java dtab >output.txt 
    }

    public static int AnybaseAdition(int n1, int n2, int b) {
        int carry = 0, pow = 1, res = 0;
        while (n1 != 0 || n2 != 0 || carry != 0) {
            int sum = n1 % 10 + n2 % 10 + carry;
            n1 /= 10;
            n2 /= 10;
            int ld = sum % b;
            carry = sum / b;
            res += ld * pow;
            pow *= 10;
        }
        return res;
    }
    public static long anybaseSubtraction(long num1, long num2, long b) {
        //num2 > num1
        long res = 0, pow = 1, borrow = 0;
        while (num1 != 0 || num2 != 0) {
            long sum = (num2 % 10 + borrow) - num1 % 10;
            if (sum < 0) {
                borrow = -1;
                sum += b;
            } else {
                borrow = 0;
            }
            num1 /= 10;
            num2 /= 10;
            res += sum * pow;
            pow *= 10;
        }
        return res;
    }
    public static int anyBaseproduct(int b, int n1, int n2) {
        int rev = 0, pow = 1;
        while (n2 != 0) {
            int ld = n2 % 10;
            n2 /= 10;
            int sprd = anyBaseProductwithSingleDigit(b, n1, ld);
            rev = AnybaseAdition(sprd * pow, rev, b);
            pow *= 10;
        }
        return rev;

    }
    public static int anyBaseProductwithSingleDigit(int b, int n1, int d1) {
        int carry = 0, pow = 1, res = 0;
        while (n1 != 0 || carry > 0) {
            int sum = (n1 % 10 * d1) + carry;
            n1 /= 10;
            carry = sum / b;
            int ld = sum % b;
            res += ld * pow;
            pow *= 10;
        }
        return res;

    }
    public static void swap(int[] arr, int a, int b) {
        int temp;
        temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void reverse(int[] arr,int i,int j) {
        int si = i, ei = j;
        while (si < ei) {
            swap(arr, si++, ei--);
        }
    }

    public static void rotate(int[] a, int k) {
        k = k % a.length;
        if (k < 0)
            k = k + a.length;
        //part 1
        reverse(a,0, a.length - k - 1);
        //part 2
        reverse(a,a.length - k, a.length - 1);
        //part 3
        reverse(a,0, a.length - 1);
    }
    public static int[] inverse(int[] a) {
        int []b=new int[a.length];
        for(int i=0;i<a.length;i++)
            b[a[i]]=i;
        return b;
    }
    public static void firstIndex(int arr[],int data){ //binary search se krna hai linear se nhi krna.
        int si=0;
        int fi=-1;
        int ei=arr.length-1;
        while(si<=ei){
            int mid=(si+ei)/2;
            if(arr[mid] > data)
                ei=mid-1;
            else if(arr[mid]< data) 
                si=mid+1;
            else{  //equal ho gya data but uske left mein bhi data ho skta hai first occurence vala.
                fi=mid;
                ei=mid-1;
            }      
        }
        System.out.println(arr[fi]);
    }
    public static void lastIndex(int arr[],int data){ //binary search se krna hai
        int si=0;
        int li=-1;
        int ei=arr.length-1;
        while(si<=ei){
            int mid=(si+ei)/2;
            if(arr[mid] > data)
                ei=mid-1;
            else if(arr[mid]< data) 
                si=mid+1;
            else{
                li=mid;
                si=mid+1;
            }      
        }
        System.out.println(arr[li]);
    }    

    public static void main(String []args)
    {
        // long n=scn.nextLong();
        // System.out.println(decimalToBinary(n));
        int n1=scn.nextInt();
        int n2=scn.nextInt();
        int b=scn.nextInt();
        AnybaseAdition(n1,n2,b);
    }
}