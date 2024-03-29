import java.util.Arrays;
public class l002StringSet{

    public static void print1D(int arr[]){
        for(int ele: arr)
            System.out.print(ele+" ");
        System.out.println();    
    }
    
    public static void print2D(int arr[][]){
        for(int ele[]: arr)
            print1D(ele);   
    }

    // 516
    // LPSS : longestPalindromicSubsequence
    public int LPSS(String str,int i,int j,int dp[][]) {
       if(i >= j){
           return dp[i][j]= (i==j)?1:0;
       }
       
       if(dp[i][j] != 0)
            return dp[i][j];

       if(str.charAt(i) == str.charAt(j))
            return dp[i][j]= LPSS(str,i+1,j-1,dp) +2;
       else
           return dp[i][j]= Math.max(LPSS(str,i+1,j,dp),LPSS(str,i,j-1,dp));        
    }

    public int LPSS_DP(String str,int I,int J,int dp[][]) {
        int n=str.length();
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(i >= j){
                     dp[i][j]= (i==j)?1:0;
                     continue;
                }
            
                if(str.charAt(i) == str.charAt(j))
                     dp[i][j]= dp[i+1][j-1] +2; //LPSS(s,i+1,j-1,dp) +2;
                else
                     dp[i][j]= Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        return dp[I][J];       
     }

     public static String LPSS_StringDP(String str) {
        int n = str.length();
        String[][] dp = new String[n][n];
        for (String[] d : dp)
            Arrays.fill(d, "");

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (i >= j) {
                    dp[i][j] = (i == j) ? str.charAt(i) + "" : "";
                    continue;
                }

                if (str.charAt(i) == str.charAt(j))
                    dp[i][j] = str.charAt(i) + dp[i + 1][j - 1] + str.charAt(j);
                else
                    dp[i][j] = dp[i + 1][j].length() > dp[i][j - 1].length() ? dp[i + 1][j] : dp[i][j - 1];
            }
        }

        return dp[0][n - 1];
    }

     
    public static void longestPalindromicSubsequence() {
        String str = "peeeddfeeeep";
        int n = str.length();
        int[][] dp = new int[n][n];

        // for (int[] d : dp)
        // Arrays.fill(d, -1);

        // System.out.println(LPSS(str, 0, n - 1, dp));
        System.out.println(LPSS_StringDP(str));
        // print2D(dp);

    }

     // LPS : Longest Palindromic Substring
     public static void LPS(String str, boolean[][] dp){
         int n=str.length();
         for(int gap=0;gap<n;gap++){
             for(int i=0,j=gap,j<n;i++,j++){
                 if(gap==0)
                    dp[i][j]=true;
                 else if(gap==1)
                    dp[i][j]=str.charAt(i)==Str.charAt(j);
                 else   
                    dp[i][j]=str.charAt(i)==Str.charAt(j) && dp[i+1][j-1];      
             }
         }
     }

    // 005, 647
    public static int LPS(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];

        int maxLen = 0;
        int count = 0;

        int sp = 0, ep = 0;

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = 1;
                else if (gap == 1)
                    dp[i][j] = str.charAt(i) == str.charAt(j) ? 2 : 0;
                else if (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] > 0)
                    dp[i][j] = dp[i + 1][j - 1] + 2;

                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    sp = i;
                    ep = j;
                }

                if (dp[i][j] != 0)
                    count++;
            }
        }

        return count;
        // return str.substring(sp,ep+1);
    }

        //LCSS: longest common subsequence
        public static int LCSS(String str1,String str2,int n,int m,int dp[][]){
            if(n==0 || m==0)
                return dp[n][m]=0;
            if(dp[n][m]!=-1)
                return dp[n][m];
            if(str1.charAt(n-1) == str2.charAt(m-1))
                return dp[n][m]=LCSS(str1,str2,n-1,m-1,dp) +1;
            else
                return dp[n][m]=Math.max(LCSS(str1,str2,n-1,m,dp),LCSS(str1,str2,n,m-1,dp));         
        }
    
    
        public static int LCSS_DP(String str1,String str2,int N,int M,int dp[][]){
            
            for(int n=0;n<=N;n++){
                for(int m=0;m<=M;m++){
                if(n==0 || m==0){
                    dp[n][m]=0;
                    continue;
                }
               
                if(str1.charAt(n-1) == str2.charAt(m-1))
                    dp[n][m]=dp[n-1][m-1] +1;       //LCSS(str,n-1,m-1,dp) +1;
                else
                    dp[n][m]=Math.max(dp[n-1][m],dp[n][m-1]);
                }
            }
            return dp[N][M]; 
        }

        public int longestCommonSubsequence(String text1, String text2) {
            int n=text1.length(),m=text2.length();
            int dp[][]=new int[n+1][m+1];
            for(int []d:dp)
                Arrays.fill(d,-1);
            // return LCSS(text1,text2,n,m,dp);
            return LCSS_DP(text1,text2,n,m,dp);
        }

    public static void main(String []args){
        longestPalindromicSubsequence();
    }
}