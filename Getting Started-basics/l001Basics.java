import java.util.Scanner;
public class l001Basics
{
    public static Scanner scn = new Scanner(System.in);

    public static boolean isPrime(int n) {
        for (int i = 2; i*i<= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
    public static void primeNumbers() {
        int t = scn.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = scn.nextInt();
            if (isPrime(n))
                System.out.println("prime");
            else
                System.out.println("not prime");
        }
    }
    public static void primeNumberTillN(int low, int high) {
        for (int i = low; i <= high; i++)
            if (isPrime(i))
                System.out.println(i);
    }
    public static void  benjaminBulbs(int n)
    {
        for(int i=1;i*i<n;i++)
        System.out.println(i*i);
    }

    public static int rotate(int n, int k) {
        int len = 0;
        int temp = n;
        while (temp != 0) {
            len++;
            temp /= 10;
        }

        k = k % len;
        if (k < 0)
            k = k + len;
        int mul = 1;
        int div = 1;
        for (int i = 1; i <= len; i++) {
            if (i <= k) div = div * 10;
            else mul = mul * 10;
        }
        int r = n % div;
        int q = n / div;
        return r * mul + q;

    }

    public static void main(String[] args) {
        // primeNumbers();
        // int low= scn.nextInt();
        // int high= scn.nextInt();
        // primeNumberTillN(low,high);
        int n=scn.nextInt();
        int k=scn.nextInt();
        System.out.println(rotate(n,k));
} 
}  