import java.util.Scanner;
public class l002
{
    //public static Scanner scn=new Scanner(System.in);
    public static void floodFill(int maze[][], int sr,int sc, int n, int m, String ans, boolean [][]visited) {
        //boundary conditions check 
        if (sr < 0 || sc < 0 || sr == n || sc == m || maze[sr][sc] == 1 || visited[sr][sc] == true) {
            return;
        }
        if (sr == n - 1 && sc == m - 1) {
            System.out.println(ans);
            return;
        }
        visited[sr][sc]=true;
        //t l d r
        floodFill(maze, sr - 1, sc, n, m, ans + "t", visited);
        floodFill(maze, sr, sc - 1, n, m, ans + "l", visited);
        floodFill(maze, sr + 1, sc, n, m, ans + "d", visited);
        floodFill(maze, sr, sc + 1, n, m, ans + "r", visited);
        visited[sr][sc] = false;
    }

    public static int template_floodFill(int maze[][], int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS, String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        vis[sr][sc] = true;
        int n = vis.length;
        int m = vis[0].length;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            // check r and c with in boundary
            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c] && maze[r][c] != 1) {
                count += template_floodFill(maze, r, c, er, ec, vis, dir, dirS, ans + dirS[d]);
            }
        }
        vis[sr][sc] = false;
        return count;
    }
    public static int floodFillJump(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS,
    String ans) {
if (sr == er && sc == ec) {
    System.out.println(ans);
    return 1;
}

vis[sr][sc] = true;

int n = vis.length;
int m = vis[0].length;

int count = 0;
for (int rad = 1; rad <= Math.max(n, m); rad++)
    for (int d = 0; d < dir.length; d++) {
        int r = sr + rad * dir[d][0];
        int c = sc + rad * dir[d][1];

        // check r and c with in boundary
        if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
            count += floodFillJump(r, c, er, ec, vis, dir, dirS, ans + dirS[d] + rad);
        }
    }

vis[sr][sc] = false;
return count;
}
public static void printKnightsTour(int chess[][], int r, int c, int move,int n) {
    //dhai move ghoda
    if (r < 0 || c < 0 || r >= n || c >= n || chess[r][c] > 0) //boundary condi. for r and c 
    {
        return;
    }
    if (move == n * n) //base case
    {
        chess[r][c] = move;
        //System.out.println(move);
        displayBoard(chess);
        chess[r][c] = 0;
        return;
    }
    chess[r][c] = move;
    printKnightsTour(chess, r - 2, c + 1, move + 1,n);
    printKnightsTour(chess, r - 1, c + 2, move + 1,n);
    printKnightsTour(chess, r + 1, c + 2, move + 1,n);
    printKnightsTour(chess, r + 2, c + 1, move + 1,n);
    printKnightsTour(chess, r + 2, c - 1, move + 1,n);
    printKnightsTour(chess, r + 1, c - 2, move + 1,n);
    printKnightsTour(chess, r - 1, c - 2, move + 1,n);
    printKnightsTour(chess, r - 2, c - 1, move + 1,n);
    chess[r][c] = 0;
}
public static int knightTour(int sr, int sc, int move, int tnc, int vis[][], int dir[][]) {
    if (move == tnc) //base case
    {
        vis[sr][sc] = move;
        displayBoard(vis);
        vis[sr][sc] = 0;
        return 1;
    }
    int n = vis.length;
    int count = 0;
    vis[sr][sc] = move;
    for (int d = 0; d < dir.length; d++) {

        int r = sr + dir[d][0];
        int c = sc + dir[d][1];
        //checking boundary for r and c
        if (r >= 0 && c >= 0 && r < n && c < n && vis[r][c] == 0) {
            count += knightTour(r, c, move + 1, tnc, vis, dir);
        }
    }
    vis[sr][sc] = 0;
    return count;
}


public static void displayBoard(int[][] chess) {
    for (int i = 0; i < chess.length; i++) {
        for (int j = 0; j < chess[0].length; j++) {
            System.out.print(chess[i][j] + " ");
        }
        System.out.println();
    }

    System.out.println();
}
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        //floodFill
        // int dir[][] = {{-1, 0},{0,-1},{1,0},{0,1}};
        // String dirS[] = { "t","l","d","r"};
        // boolean[][] visited = new boolean[n][m]; //by default false 
        // System.out.println(template_floodFill(arr, 0, 0, n - 1, m - 1, visited, dir, dirS, ""));
        boolean[][] visited = new boolean[n][m]; //by default false 
        floodFill(arr, 0, 0, n, m, "", visited);
        // kinght tour
        // Scanner scn = new Scanner(System.in);
        // int n = scn.nextInt();
        // int sr = scn.nextInt();
        // int sc = scn.nextInt();
        // int[][] dir = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };
        // int[][] vis = new int[n][n];
        // // for(int[] d : vis) Arrays.fill(d,-1);

        // knightTour(sr, sc, 1, n * n, vis, dir);
    }
}