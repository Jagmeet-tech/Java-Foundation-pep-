public class dsuQuestions{

    static int[] par, size;

    public static int findParent(int u){
        if(par[u] == u)
            return u;
        return par[u] = findParent(par[u]);
        
        // return par[u] == u ? u : par[u] = find(par[u]);
    }

    //695
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0)
            return 0;
        int n = grid.length, m =grid[0].length;
        par = new int[n*m];
        size = new int[n*m];
        int [][]dir = {{1,0},{0,1}};

        int count = 0;      //count is used as to calculate GCC.... 
        int maxSize = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                par[i*m + j] = i*m + j;
                size[i*m + j] = 1;
                count++;
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 1){
                    int par1 = findParent(i*m + j);

                    for(int d=0; d<dir.length;d++){
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if(r>=0 && c>=0 && r<n && c<m && grid[r][c] == 1){
                            int par2 = findParent(r*m + c);
                            if(par1 != par2){
                                par[par2] = par1;   //global leader
                                size[par1] += size[par2];
                                count--;    //ekk merge ka decrement
                            }
                        }
                    }
                    maxSize = Math.max(maxSize , size[par1]);
                }
            }
        }
        return maxSize;
    }


    //1061
    public static String smallEquivalentString(String s1,String s2,String basestr){
        int n = s1.length();
        par = new int[26];
        for(int i =0 ;i < 26;i++){
            par[i] = i;
        }

        for(int i=0;i<n;i++){
            int p1 = findParent(s1.charAt(i) - 'a');
            int p2 = findParent(s2.charAt(i) - 'a');

            par[p1] = Math.min(p1,p2);
            par[p2] = Math.min(p1,p2);
        }

        StringBuilder sb =new StringBuilder();
        for(int i =0;i<basestr.length();i++){
            char ch = (char)(findParent(basestr.charAt(i) - 'a') + 'a');
            sb.append(ch);
        }   
        return sb.toString();
    }

    //990
    public boolean equationsPossible(String[] equations) {
        int n = equations.length;
        par = new int[26];

        for(int i=0;i<26;i++){
            par[i] = i;
        }

        for(String s:equations){
            char ch1 = s.charAt(0),ch2 = s.charAt(1), ch3 = s.charAt(3);
            int p1 = findParent(ch1 - 'a');
            int p2 = findParent(ch3 - 'a');
            if(ch2 == '='){
                if(p1 != p2)
                    par[p1] = p2;
            }
        }

        for(String s:equations){
             char ch1 = s.charAt(0),ch2 = s.charAt(1), ch3 = s.charAt(3);
            int p1 = findParent(ch1 - 'a');
            int p2 = findParent(ch3 - 'a');
            if(ch2 == '!'){
                if(p1 == p2)
                    return false;
            }
        }
        return true;
    }

    //839
    public static boolean isSimilar(String s1,String s2){
        int count = 0;
        int n = s1.length();
        for(int i=0;i<n;i++){
            if(s1.charAt(i) != s2.charAt(i) && ++count > 2)
                return false;
        }
        return true;
    }

    public int numSimilarGroups(String[] strs) {
        int n = strs.length, group = n;
        par = new int[n];
        for(int i=0;i<n;i++)
            par[i] = i;
    
        for(int i =0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(isSimilar(strs[i],strs[j])){
                    int p1 = findParent(i);
                    int p2 = findParent(j);
                    if(p1 != p2){
                        par[p2] = p1;
                        group--;
                    }
                }
            }
        }
        return group;    
    }

    //305
    public List<Integer> numIslands2(int n, int m, int[][] positions){
        int n = positions.length, m = positions[0].length;
        List<Integers> ans = new ArrayList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        par = new int[n*m];
        Arrays.fill(par,-1);

        int count=0;
        for(int []p : positions){
            int i = p[0] ,j = p[1];

            if(par[i*m + j] == -1){
                par[i*m + j] = i*m + j;
                count++;
                int p1 = findParent(i*m + j);  
                for(int d=0;d<dir.length;d++){
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if(r>=0 && c>=0 && r<n && c<m && par[r*m + c] != -1){
                        int p2 = findParent(r*m + c);
                        if(p1 != p2){
                            par[p2] = p1;
                            count--;
                        }
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }

    //684
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length , m = edges[0].length;
        int []ans = new int[2];
        par = new int[n + 1];
  
        for(int i=0;i<n;i++)
            par[i] = i;
  
        for(int []e : edges){
            int i = e[0], j = e[1];
            int p1 = findParent(i);
            int p2 = findParent(j);
            if(p1 == p2 ){
              ans[0] = i;
              ans[1] = j;
            }
            else{
                par[p2] = p1;
            }
        }
        return ans;
      }
}