public class bfsQuestions{

    int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    //994
    public int orangesRotting(int[][] grid) {
        int m = grid[0].length;
        int n = grid.length;

        int freshOranges=0,time=0;
        LinkedList<Integer> queue = new LinkedList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 1)
                    freshOranges++;
                else if(grid[i][j] == 2){
                    queue.addLast(i*m + j); //2d encode
                    grid[i][j] = 2; //mark it visited
                }    
            }
        }
        
        if(freshOranges == 0)
            return 0;
        
        while(queue.size()!=0){
            int size = queue.size();
            while(size-- > 0){
                int rottedOrangeIDX = queue.removeFirst();
                int sr = rottedOrangeIDX / m;
                int sc = rottedOrangeIDX % m;

                for(int d = 0;d<4;d++){
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if(r >=0 && c>=0 && r<n && c<m && grid[r][c] == 1){
                        if(--freshOranges == 0)
                            return time + 1; 
                        grid[r][c] = 2;
                        queue.addLast(r*m + c);
                        
                    }
                }
            }
            time++;
        }
        return -1;
    }

    //1096
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length, m=grid[0].length, shortestPath=1;
        if(n == 0 || m == 0)
            return 0;
           
        if(grid[0][0] == 1 || grid[n-1][m-1] == 1)
            return -1;
            
        LinkedList<Integer> queue = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };
        
        queue.addLast(0);
        grid[0][0] = 1;
        
        while(queue.size()!=0){
            int size = queue.size();
            while(size-- > 0){
                int idx = queue.removeFirst();
                int sr = idx/m , sc = idx % m;
                
                if(sr == n-1 && sc == m-1)
                    return shortestPath;

                for(int d=0;d<dir.length;d++){
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if(r>=0 && c>=0 && r<n && c<m && grid[r][c] == 0){
                        grid[r][c] = 1;
                        queue.addLast(r*m + c);
                    } 
                }
            }
            shortestPath++;
        }
        return -1;
    }

    //512
    public int[][] updateMatrix(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return grid;

        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    vis[i][j] = true;
                    que.addLast(i * m + j);
                }
            }
        }

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;

                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                        vis[r][c] = true;
                        grid[r][c] = grid[sr][sc] + 1;
                        que.addLast(r * m + c);
                    }
                }
            }
        }

        return grid;
    }

    //286
    public void wallsAndGates(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return;

        int n = grid.length, m = grid[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    que.addLast(i * m + j);
                }
            }
        }

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;

                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 2147483647) {
                        grid[r][c] = grid[sr][sc] + 1;
                        que.addLast(r * m + c);
                    }
                }
            }
        }
    }

    //329
    public int longestIncreasingPath(int[][] matrix) {
        LinkedList<Integer> que = new LinkedList<>();
        int n = matrix.length,m = matrix[0].length;
        int [][]indegree = new int[n][m];
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++){
                for(int d=0;d<dir.length;d++){
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if(r>=0 && c>=0 && r<n && c<m && matrix[i][j] > matrix[r][c])
                        indegree[i][j]++;
                }

                if(indegree[i][j] == 0)
                    que.addLast(i*m + j);
            }
        
        int level = 0;    
        while(que.size()!=0){
            int size = que.size();
            while(size-- >0){
                int rvtx = que.removeFirst();
                int i = rvtx/m, j = rvtx % m;

                for(int d=0;d<dir.length;d++){
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if(r>=0 && c>=0 && r<n && c<m && matrix[i][j] < matrix[r][c] && --indegree[r][c] == 0)
                        que.addLast(r*m +c);    
                }

            }
            level++;
        }   
        return level; 
        
    }
}