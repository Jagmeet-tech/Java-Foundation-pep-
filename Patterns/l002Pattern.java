import java.util.Scanner;
public class l002Pattern
{
    public static Scanner scn=new Scanner(System.in);
    public static void pattern18(int row) {
        int nst = row;
        int nsp = 0;
        for (int r = 1; r <= row; r++) {
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }
            for (int cst = 1; cst <= nst; cst++) {
                if (r > 1 && r <= row / 2 && cst > 1 && cst < nst)
                    System.out.print("\t");
                else
                    System.out.print("*\t");
            }
            if (r < row / 2 + 1) {
                nst -= 2;
                nsp += 1;
            } else {
                nst += 2;
                nsp -= 1;
            }

            System.out.println();
        }
    }

    public static void pattern17(int row) {
        int nsp = row / 2;
        int nst = 1;
        for (int r = 1; r <= row; r++) {
            for (int csp = 1; csp <= nsp; csp++) {
                if (r == row / 2 + 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("*\t");
            }
            if (r <= row / 2)
                nst++;
            else
                nst--;
            System.out.println();
        }
    }
    public static void pattern16(int row) {
        int nsp = (2 * row) - 3;
        int nst = 1;
        for (int r = 1; r <= row; r++) {
            int val = 1;
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(val + "\t");
                val++;
            }
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }
            if (r == row) {
                nst--;
                val--;
            }

            for (int cst = 1; cst <= nst; cst++) {
                val--;
                System.out.print(val + "\t");

            }
            nsp -= 2;
            nst++;
            System.out.println();
        }
    }

    public static void pattern15(int row) {
        int nsp = row / 2;
        int nst = 1;
        int k = 1;
        for (int r = 1; r <= row; r++) {
            int temp = k;
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(temp + "\t");
                if (cst <= (nst / 2))
                    temp++;
                else
                    temp--;
            }
            if (r <= row / 2) {
                nst += 2;
                nsp--;
                k++; //here we can use k=row+1; also
            } else {
                nst -= 2;
                nsp++;
                k--; //here we can use k=row-r; also 
            }
            System.out.println();
        }
    }
    public static void pattern13(int row) {
        int nst = 0;
         for (int i = 0; i < row; i++) {
             int icj = 1;
             for (int j = 0; j <= nst; j++) {
                 System.out.print(icj + "\t");
                 int icjp1 = icj * (i - j) / (j + 1);
                 icj = icjp1;
             }
             nst++;
             System.out.println();
         }
 
     }

    public static void pattern12(int row) {
        int a=-1,b=1, nst = 1, c;
        for (int r = 1; r <= row; r++) {
            for (int cst = 1; cst <= nst; cst++) {
                c=a+b;
                System.out.print(c + "\t");
                a=b;
                b=c;
            }
            nst++;
            System.out.println();
        }
    }

    public static void pattern11(int row) {
        int k = 1, nst = 1;
        for (int r = 1; r <= row; r++) {
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(k + "\t");
                k++;
            }
            nst++;
            System.out.println();
        }
    }
    public static void pattern10(int row) {
        int nst = 1;
        int osp = row / 2;
        int isp = -1;
        for (int r = 1; r <= row; r++) {
            for (int csp = 1; csp <= osp; csp++) {
                System.out.print("\t");
            }
            System.out.print("*\t");
            for (int csp = 1; csp <= isp; csp++) {
                System.out.print("\t");
            }
            if (r > 1 && r < row) {
                System.out.print("*\t");
            }

            if (r <= row / 2) {
                osp--;
                isp += 2;
            } else {
                osp++;
                isp -= 2;
            }
            System.out.println();
        }
    }

    public static void pattern6(int row)
    {
        int nst=row/2;
        int nsp=1;
        for(int r=1;r<=row;r++)
        {
            for(int cst=1;cst<=nst;cst++)
            {
                System.out.print("*\t");
            }
            for(int csp=1;csp<=nsp;csp++)
            {
                System.out.print("\t");
            }
            nst--;
            nsp+=2;
            System.out.println();
        }

    }
    
    public static void pattern5(int row) {
        int nsp = row / 2;
        int nst = 1;
        for (int r = 1; r <= row; r++) {
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("*\t");
            }
            if (r <= row / 2) {
                nsp--;
                nst += 2;
            } else {
                nsp++;
                nst -= 2;
            }
            System.out.println();
        }
    }
    public static void pattern9(int row) {
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= row; j++) {
                if (i + j == row + 1 || i == j)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println();
        }

    }
    public static void pattern9_01(int row)
    {
        int p=row+1;
        for(int i=1;i<=row;i++)
        {
            for(int j=1;j<=row;j++)
            {
                if(i+j==p || i+j==p-2 || i+j==p+2)
                System.out.print("*\t");
                else
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void pattern9_02(int row)
    {
        int p=0;
        for(int i=1;i<=row;i++)
        {
            for(int j=1;j<=row;j++)
            {
                if(i-j==p || i-j==p-2 || i-j==p+2)
                System.out.print("*\t");
                else
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void invDiagonal(int row) {  //pattern 8
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= row; j++) {
                if (i + j == row + 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println();
        }

    }

    public static void diagonal(int row) {  //pattern 8
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= row; j++) {
                if (j == i)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void invmirrorTriangle(int row) //pattern 4
    {
        int nst=row,nsp=0;
        for(int r=1;r<=row;r++)
        {
            for(int csp=1;csp<=nsp;csp++)
            {
                System.out.print("\t");
            }
            for(int cst=1;cst<=nst;cst++)
            {
                System.out.print("*\t");
            }
            System.out.println();
            nsp++;
            nst--;
        }

    }
    public static void invTriangle(int row) //pattern 2
    {
        int nst=row;
        for(int r=1;r<=row;r++)
        {
            for(int cst=1;cst<=nst;cst++)
            {
                System.out.print("*\t");
            }
            System.out.println();
            nst--;
        }
    } 

    public static void triangle(int row)  //pattern 1
    {
        int nst=1;
        for(int r=1;r<=row;r++)
        {
            for(int cst=1;cst<=nst;cst++)
            {
                System.out.print("*\t");
            }
            System.out.println();
            nst++;

        }
    }
    
    public static void mirrorTriangle(int row)  //pattern 3
    {
        int nsp=row-1,nst=1;
        for(int r=1;r<=row;r++)
        {
            for(int csp=1;csp<=nsp;csp++)
            {
                System.out.print("\t");
            }
            for(int cst=1;cst<=nst;cst++)
            {
                System.out.print("*\t");
            }
            System.out.println();
            nsp--;
            nst++;
        }
    }

    public static void main(String []args)
    {
        int n=scn.nextInt();
        // mirrorTriangle(n);
        // triangle(n);
        // diagonal(n);
        // pattern9_01(n);
        pattern9_02(n);
        
    }
}