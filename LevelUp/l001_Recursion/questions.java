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

    //39
    public static int combinationWithInfi(int arr[],int target,int idx,List<List<Integer>>ans,List<Integer>smallans){
        if(target == 0){
            List<Integer> base = new ArrayList<>(smallans);
            ans.add(base);
            return 1;
        }
        
        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(target - arr[i] >=0){
                smallans.add(arr[i]);
                count += combinationWithInfi(arr,target-arr[i],i,ans,smallans);
                smallans.remove(smallans.size()-1);
            }
        }
        return count;
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
         List<List<Integer>> ans = new ArrayList<>();
         combinationWithInfi(candidates,target,0,ans,new ArrayList<>());
         return ans;
    }

    // 40
    public int combinationSum2(int[] arr, int tar, int idx, List<Integer> smallAns, List<List<Integer>> res) {
        if (tar == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return 1;
        }

        int count = 0;
        boolean[] vis = new boolean[51];
        for (int i = idx; i < arr.length; i++) {
            if (!vis[arr[i]] && tar - arr[i] >= 0) {

                vis[arr[i]] = true;

                smallAns.add(arr[i]);
                count += combinationSum2(arr, tar - arr[i], i + 1, smallAns, res);
                smallAns.remove(smallAns.size() - 1);
            }
        }

        return count;
    }

    public int combinationSum2_01(int[] arr, int tar, int idx, List<Integer> smallAns, List<List<Integer>> res) {
        if (tar == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return 1;
        }

        int count = 0;
        int prev = -1;
        for (int i = idx; i < arr.length; i++) {
            if (prev != arr[i] && tar - arr[i] >= 0) {
                smallAns.add(arr[i]);
                count += combinationSum2_01(arr, tar - arr[i], i + 1, smallAns, res);
                smallAns.remove(smallAns.size() - 1);
            }

            if (tar - arr[i] < 0)
                break;

            prev = arr[i];
        }

        return count;
    }

    public int combinationSum2_Subseq(int[] arr, int tar, int idx, List<List<Integer>> ans, List<Integer> smallAns) {
        if (idx >= arr.length || tar == 0) {
            if(tar == 0){
                List<Integer> base = new ArrayList<>(smallAns);
                ans.add(base);
                return 1;
            }
            return 0;
        }

        int count =0;
        if(tar - arr[idx] >=0){
            smallAns.add(arr[idx]);
            count += combinationSum2_Subseq(arr,tar-arr[idx],idx+1,ans,smallAns);
            smallAns.remove(smallAns.size()-1);
        }
        idx++;
        while(idx < arr.length && arr[idx] == arr[idx-1]){
            idx++;
        }
        count += combinationSum2_Subseq(arr,tar,idx,ans,smallAns);

        return count;
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);  //its required to sort to make widthwise blocked.
        List<List<Integer>> ans = new ArrayList<>();
         combinationSum2_Subseq(candidates,target,0,ans,new ArrayList<>());
         return ans;   
    }

    //77
    public static int combination(int n,int k,int idx,List<List<Integer>> ans,List<Integer> smallAns){
        if(k == 0){
            List<Integer> base = new ArrayList<>(smallAns);
            ans.add(base);
            return 1;
        }
        
        int count=0;
        for(int i =idx;i<=n;i++){
            smallAns.add(i);
            count += combination(n,k-1,i+1,ans,smallAns);
            smallAns.remove(smallAns.size()-1);
        }
        return count;
    }

    //216
    public static int combinationIII(int target,int k,int idx,List<List<Integer>> ans,List<Integer> smallAns){
        if(k == 0 || target == 0){
            if(k==0 && target ==0){
                List<Integer> base = new ArrayList<>(smallAns);
                ans.add(base);
                return 1;
            }
            return 0;
        }
        
        int count=0;
        for(int i =idx;i<=9;i++){
            if(target - i >=0){
                smallAns.add(i);
                count += combinationIII(target-i,k-1,i+1,ans,smallAns);
                smallAns.remove(smallAns.size()-1);
            }
            else
                break;
        }
        return count;
    }
    
    public List<List<Integer>> combinationSumIII(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationIII(n,k,1,ans,new ArrayList<>());
        return ans;
    }

    //46
        //tel = total elements.
       public static int permute(int tel,int[] nums,List<List<Integer>> ans,List<Integer> smallAns){
            if(tel == 0){
                List<Integer> base = new ArrayList<>(smallAns);
                ans.add(base);
                return 1;
            }
            
            int count=0;
            for(int i=0;i<nums.length;i++){
                if(nums[i] != -11){  //-11 as per constraints.
                    int val = nums[i];
                    nums[i] = -11;
                    smallAns.add(val);
                    count += permute(tel-1,nums,ans,smallAns);
                    smallAns.remove(smallAns.size()-1);
                    nums[i] =val;
                }
                
            }
            return count;
        }
        
        public List<List<Integer>> permute(int[] nums) {
             List<List<Integer>> ans = new ArrayList<>();
             permute(nums.length,nums,ans,new ArrayList<>());
            return ans;
        }

    //47
    public static int permuteUnique(int tel,int[] nums,List<List<Integer>> ans,List<Integer> smallAns) {
        if(tel == 0){
            List<Integer> base = new ArrayList<>(smallAns);
            ans.add(base);
            return 1;
        }
        
        int prev=-12,count=0;       //-12 as per constraints
        for(int i =0;i<nums.length;i++){
            if(nums[i] > -11 && prev!=nums[i]){ //-11 as per constraints
                int val = nums[i];
                nums[i] = -11;
                smallAns.add(val);
                count += permuteUnique(tel-1,nums,ans,smallAns);
                smallAns.remove(smallAns.size()-1);
                nums[i] = val; 
                prev = nums[i];
            } 
        }
        return count;
    }
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>>  ans= new ArrayList<>();
        permuteUnique(nums.length,nums,ans,new ArrayList<>());
        return ans;
    }

    //784
    public int letterCasePermutation(int idx,String s,List<String> res,String ans) {
        if(idx == s.length()){
            String base = new String(ans);
            res.add(base);
            return 1;
        }
        
        int count=0;
        char ch = s.charAt(idx);
        if(ch >='0' && ch <='9')
            count += letterCasePermutation(idx+1,s,res,ans); 
        else{
            ch = (char)((ch >= 'A' && ch <='Z')?(ch - 'A' + 'a') : (ch - 'a' + 'A')) ;
            count += letterCasePermutation(idx+1,s,res,ans.substring(0,idx) + ch + ans.substring(idx+1,s.length()));       
            count +=  letterCasePermutation(idx+1,s,res,ans);
        }
        return count;
    }

    //52 nqueen II
    static boolean rows[];
    static boolean cols[];
    static boolean Adiag[];
    static boolean diag[];

    public static int nqueenCombination_03(int tqn,int idx,int n,int m,String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i =idx;i<n*m;i++){
            int r = i / m;
            int c = i % m;
            if(!rows[r] && !cols[c] && !diag[r+c] && !Adiag[r-c + (m-1)]){
               rows[r] = cols[c] = diag[r+c] = Adiag[r-c + (m-1)] = true;

               count += nqueenCombination_03(tqn-1,i+1,n,m,ans + "(" + r + ", " + c + ") ");
               
               rows[r] = cols[c] = diag[r+c] = Adiag[r-c + (m-1)] = false;
            }
        }
        return count;
    }


    //51 nqueen
    public static int nqueenCombination_03(boolean [][]boxes,int tqn,int idx,int n,int m,List<List<String>> res){
        if(tqn == 0){
            List<String> smallAns = new ArrayList<>();
            for(int i=0;i<n;i++){
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<m;j++){
                    if(boxes[i][j])
                        sb.append("Q");
                    else
                        sb.append(".");
                }
                smallAns.add(sb.toString());
            }
            res.add(smallAns);
            return 1;
        }

        int count = 0;
        calls++;
        for(int i =idx;i<n*m;i++){
            int r = i / m;
            int c = i % m;
            if(!rows[r] && !cols[c] && !diag[r+c] && !Adiag[r-c + (m-1)]){
                boxes[r][c] =true;
               rows[r] = cols[c] = diag[r+c] = Adiag[r-c + (m-1)] = true;

               count += nqueenCombination_03(boxes,tqn-1,i+1,n,m,res);
               
               rows[r] = cols[c] = diag[r+c] = Adiag[r-c + (m-1)] = false;
                boxes[r][c] =false;
            }
        }
        return count;
    }

    //139
    public static int wordBreak(String s,int idx,String ans,HashSet<String> dict,int maxlen,int []dp){
        if(idx >= s.length()){
            return dp[idx] = 1;
        }
        
        if(dp[idx]!=-1)
            return dp[idx];
        boolean res = false;
        for(int i = idx;i<=s.length();i++){
            String word = s.substring(idx,i);
            if(word.length() > maxlen)
                break;
            if(dict.contains(word))
                 res = res || wordBreak(s,i,ans + word + " ",dict,maxlen,dp) == 1 ? true:false ;    
        }
        return dp[idx] =  res ? 1 : 0;
    }
    
    public boolean wordBreak(String str, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>();
        int maxlen=0;
        int n = str.length();
        int dp[] = new int[n+1];
        Arrays.fill(dp,-1);
        for(String s : wordDict){
            dict.add(s);
             maxlen = Math.max(maxlen,s.length());
        }
           
        return wordBreak(str,0,"",dict,maxlen,dp) == 1?true:false; 
    }

}