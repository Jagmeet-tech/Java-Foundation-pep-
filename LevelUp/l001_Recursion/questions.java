import java.util.Collections;

public class questions{

    // special matrix gfg
    static int mod = (int)1e9 + 7;
    public static int floodFill(int sr,int sc,int er,int ec,boolean [][]vis,int [][]dp){
        
        if(sr == er && sc == ec)
            return dp[sr][sc]=1;
            
        int count =0;
        if(dp[sr][sc]!=-1)
            return dp[sr][sc];
            
        // vis[sr][sc] = true;  Not need as 2 dirn call so no cycle.
            if(sc + 1 <= ec && !vis[sr][sc+1])
                count = (count % mod + floodFill(sr,sc+1,er,ec,vis,dp)%mod)%mod;
            if(sr + 1 <= er && !vis[sr+1][sc])
                count = (count % mod + floodFill(sr+1,sc,er,ec,vis,dp)%mod)%mod;
        // vis[sr][sc] = false;
        return dp[sr][sc] = count;
    }
    public int FindWays(int n, int m, int[][] blocked_cells)
    {
        n++;
        m++;
        boolean vis[][] = new boolean[n][m]; 
        for(int d[] : blocked_cells){
            vis[d[0]][d[1]] = true;
        }
        
        if(vis[1][1] || vis[n-1][m-1])
            return 0;
        int [][]dp = new int[n][m];
        for(int d[]:dp)
            Arrays.fill(d,-1);
            
        return floodFill(1,1,n-1,m-1,vis,dp);
    }


    //Rat in Maze-I gfg
    public static int floodFill(int sr,int sc,int er,int ec,String ans,boolean [][]vis,ArrayList<String> arr,int[][]board){
        if(sr == er && sc == ec){
            arr.add(ans);
            return 1;
        }

        
        int count =0;
        vis[sr][sc] = true;
        for(int d=0;d<dir.length;d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r>=0 && c>=0 && r<=er && c<=ec && !vis[r][c] && board[r][c]!=0){
                count += floodFill(r,c,er,ec,ans + Sdir[d],vis,arr,board);
            }
        }

        vis[sr][sc] = false;
        return count;
    }

    public static ArrayList<String> findPath(int[][] m, int n) {
        boolean[][] vis = new boolean[n][n];
        ArrayList<String> arr = new ArrayList<>();
        if(m[0][0] == 0 || m[n-1][n-1]==0)
            return arr;
        int ans = floodFill(0,0,n-1,n-1,"",vis,arr,m);
       Collections.sort(arr);
        return arr;
    }
}