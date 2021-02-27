import java.util.Scanner;
public class l004_2d
{
    public static Scanner scn=new Scanner(System.in);
    public static void input(int[][] arr)
    {
        int n=arr.length;
        int m=arr[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j]=scn.nextInt();
            }
        }
    }
    public static void display(int[][] arr)
    {
        int n=arr.length;
        int m=arr[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void forEachdisplay(int arr[][])
    {
        for(int[] a:arr){
            for(int e:a){
                System.out.print(e);
            }
            System.out.println();
        }
    }
 public static boolean findData(int arr[][],int data)
    {
        int n=arr.length;
        int m=arr[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
               if(arr[i][j]==data)
                return true; 
            }
        }
        return false;
    }   
public static int maxEle(int[][] arr){
    int n=arr.length;
    int m=arr[0].length;
    int max=(int)-1e9;
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            if(arr[i][j]>max)
              max=arr[i][j];
        }
    }
    return max;
}    
public static int minEle(int[][] arr){
    int n=arr.length;
    int m=arr[0].length;
    int min=(int)1e9;
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            if(arr[i][j]<min)
              min=arr[i][j];
        }
    }
   return min;
}
public static void wavetraversalLeftToRight(int [][]arr)
{
    int n=arr.length;
    int m=arr[0].length;
    for(int i=0;i<n;i++)
    {
        if(i%2==0)
            for(int j=0;j<m;j++)
                System.out.print(arr[i][j]+" ");
        else
            for(int j=m-1;j>=0;j--)
                System.out.print(arr[i][j]+" ");
        System.out.println();               
    }
}
public static void wavetraversalUpToDown(int[][] arr) {
    int n = arr.length;
    int m = arr[0].length;
    for (int j = 0; j < m; j++) {
        if (j % 2 == 0)
            for (int i = 0; i < n; i++)
                System.out.println(arr[i][j]);
        else
            for (int i = n - 1; i >= 0; i--)
                System.out.println(arr[i][j]);
    }
}
public static void diagonalTaversal(int [][]arr)
{
    int n=arr.length;
    int m=arr[0].length;
    for(int gap=0;gap<m;gap++)
    {
        for(int i=0, j=gap;i<n && j<m ;i++,j++)
            System.out.print(a[i][j]+" ");
        System.out.println();    
    }
}
public static void rotate90(int[][] arr) {
    int n = arr.length;
    //int m = arr[0].length;
    //transpose 
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            int temp = arr[i][j];
            arr[i][j] = arr[j][i];
            arr[j][i] = temp;
        }
    }
    //reverse of columns
    int j1 = 0, j2 = n - 1;
    while (j1 < j2) {
        for (int i = 0; i < n; i++) {
            int temp = arr[i][j1];
            arr[i][j1] = arr[i][j2];
            arr[i][j2] = temp;
        }
        j1++;
        j2--;
    }

}
public static void exitPoint(int arr[][]) {
    int n = arr.length;
    int m = arr[0].length;
    int dir = 0, i = 0, j = 0;
    while (true) {
        dir = (dir + arr[i][j]) % 4; //because four directions were given and we have to make it in range.
        if (dir == 0) { //0 - right, 1- down,2-left ,3-up 
            j++;
            if (j == m) {
                System.out.println(i);
                System.out.println(--j);
                break;
            }
        } else if (dir == 1) {
            i++;
            if (i == n) {
                System.out.println(--i);
                System.out.println(j);
                break;
            }
        } else if (dir == 2) {
            j--;
            if (j < 0) {
                System.out.println(i);
                System.out.println(++j);
                break;
            }
        } else {
            i--;
            if (i < 0) {
                System.out.println(++i);
                System.out.println(j);
                break;
            }

        }

    }
}
public static void spiral(int arr[][]) {
    int n = arr.length;
    int m = arr[0].length;
    int tne = n * m;
    int rmin = 0, rmax = n - 1, cmin = 0, cmax = m - 1;
    while (tne > 0) {
        //left wall
        for (int i = rmin; i <= rmax && tne > 0; i++) {
            System.out.println(arr[i][cmin]);
            tne--;
        }
        cmin++;
        //bottom wall
        for (int j = cmin; j <= cmax && tne > 0; j++) {
            System.out.println(arr[rmax][j]);
            tne--;
        }
        rmax--;
        //right wall
        for (int i = rmax; i >= rmin && tne > 0; i--) {
            System.out.println(arr[i][cmax]);
            tne--;
        }
        cmax--;
        //top wall
        for (int j = cmax; j >= cmin && tne > 0; j--) {
            System.out.println(arr[rmin][j]);
            tne--;
        }
        rmin++;
    }
}
public static void searchIn2dArray(int arr[][], int data) {
    int n = arr.length;
    int i = n - 1, j = 0;
    while (i >= 0 && j < n) {
        if (arr[i][j] > data)
            i--;
        else if (arr[i][j] < data)
            j++;
        else {
            if (arr[i][j] == data) {
                System.out.println(i + "\n" + j);
                return;
            }
        }
    }
    System.out.println("Not Found");
}
public static void saddle(int arr[][])
{
    int n=arr.length;
    int m=arr[0].length;
    for(int y=0;y<n;y++)
    {
        int r=0,x=0;
        int min=arr[y][0];
        for(int j=1;j<m;j++)  //row
        {
            if(min > arr[y][j]){
                min=arr[y][j];
                r=j;
            }
        }
        int max=arr[y][r];
        for(int i=1;i<n;i++)  //column
        {
            if(max < arr[i][r]){
                max=arr[i][r];
                x=i;
            }
        }
        if(min==max)
            System.out.println(arr[r][x]);
    }
    
}
public static void main(String []args)
    {
        int n=scn.nextInt();
        int m=scn.nextInt();
        int [][]arr=new int[n][m];
        input(arr);
        display(arr);
        System.out.println(findData(arr,0));
        System.out.println(maxEle(arr));
        System.out.println(minEle(arr));
    }
    
}