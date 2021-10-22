public class l001{
    static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };
    static String[] Sdir = { "r", "l", "d", "u", "s", "w", "n", "e" };
    
    public static int floodFill(int sr,int sc,int er,int ec,String ans,boolean [][]vis){
        if(sr == er && sc == ec){
            System.out.println(ans);
            return 1;
        }

        
        int count =0;
        vis[sr][sc] = true;
        for(int d=0;d<dir.length;d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r>=0 && c>=0 && r<=er && c<=ec && !vis[r][c]){
                count += floodFill(r,c,er,ec,ans + Sdir[d]+" ",vis);
            }
        }

        vis[sr][sc] = false;
        return count;
    }


    static class pairPath{
        int longestLen;
        String longestPath; 
        int count;

        pairPath(int len,String path,int count){
            this.longestLen = len;
            this.longestPath = path;
            this.count = count;
        }
    }

    public static pairPath floodFill_01(int sr,int sc,int er,int ec,String ans,boolean [][]vis){
        if(sr == er && sc == ec){
            return new pairPath(0,"",1);
        }

        vis[sr][sc] = true;
        pairPath myPair = new pairPath(0,"",0);
        for(int d=0;d<dir.length;d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r>=0 && c>=0 && r<=er && c<=ec && !vis[r][c]){
                pairPath recAns = floodFill_01(r,c,er,ec,ans + Sdir[d]+" ",vis);

                if(myPair.longestLen < recAns.longestLen + 1){
                    myPair.longestLen = recAns.longestLen + 1;
                    myPair.longestPath = Sdir[d] + recAns.longestPath;
                }

                myPair.count += recAns.count; 
            }
        }

        vis[sr][sc] = false;
        return myPair;
    }

    public static void main(String []args){
        int n = 4, m = 4;
        boolean[][] vis = new boolean[n][m];

        // System.out.println(floodFill(0,0,n-1,m-1,"",vis));
        pairPath ans= floodFill_01(0,0,n-1,m-1,"",vis);
        System.out.println(ans.longestLen + "  "+ans.longestPath + "  "+ ans.count);
    }
}