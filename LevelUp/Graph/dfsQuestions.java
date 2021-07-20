public class dfsQuestions{

    // https://www.hackerrank.com/challenges/journey-to-the-moon/problem

    //694
    int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    String[] dirS = { "D", "U", "R", "L" };

    StringBuilder sb;
    int n, m;

    public static void dfs_numDistinctIslands(int[][] grid,int i,int j){
        grid[i][j] = 0;
        for(int d=0;d<4;d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r>=0 && c>=0 && r<n && c<m && grid[r][c] == 1){
                sb.append(dirS[d]);
                dfs_numDistinctIslands(grid,r,c);
                sb.append("b");
            }
        }
    } 

    public int numDistinctIslands(int[][] grid){
        n = grid.length, m = grid[0].length;
        HashSet<String> set =new HashSet<>();   //we havenot take hashset of stringbuilder ,as it adds duplicate string(as it creates new  address every time).   
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 1){
                    sb = new StringBuilder();  
                    dfs_numDistinctIslands(grid,i,j);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    //1905
    public boolean dfs_countSubIslands(int[][] grid1, int[][] grid2,int i,int j){
        grid2[i][j] = 0;
        boolean res = true;
        for(int d=0;d<4;d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r>=0 && c>=0 && r<n && c<m && grid2[r][c] == 1 )
                res = dfs_countSubIslands(grid1,grid2,r,c) && res;
        }
        return res && (grid1[i][j] == 1);
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
         n = grid2.length; 
         m=grid2[0].length;
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid2[i][j] == 1){
                    count += dfs_countSubIslands(grid1,grid2,i,j) ? 1 : 0;
                }
            }
        } 
        return count;
    }

}