public class l006_DiaSet{
    public static class TreeNode {  //used in leetcode
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Node {  //used in geeks
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }

    //in terms of edges
    public static int height(TreeNode root){
        if(root==null)
            return -1;
        
        int lh=height(root.left);     
        int rh=height(root.right);     
        return Math.max(lh,rh)+1;    
    }

    //O(n^2)
    public static int diameter_01(TreeNode root){
        if(root==null)
            return 0;

        int ld=diameter_01(root.left);  //Diameter end points are on left side  
        int rd=diameter_01(root.right);  //Diameter end points are on right side
        
        int lh=height(root.left);   // Diameter end points are on either side
        int rh=height(root.right);

        return Math.max(Math.max(ld,rd),lh + rh + 2);
    }

    //{diameter,height}  O(n)
    public static int[] diameter_02(TreeNode root){
       
        if(root==null)
            return new int[]{0,-1};

        int []lp= diameter_02(root.left);
        int []rp= diameter_02(root.right);

        int []myAns=new int[2];
        myAns[0]=Math.max(Math.max(lp[0],rp[0]),lp[1] + rp[1] + 2);  //maximum diamter
        myAns[1]=Math.max(lp[1],rp[1]) + 1;

        return myAns;
    }
    
    public int diameterOfBinaryTree(TreeNode root) {
        int []ans= diameter_02(root);
        return ans[0];
    }

    //O(n)
    public static int diameter_03(TreeNode root,int []dia){
       
        if(root==null)
            return -1;

        int lh=diameter_03(root.left,dia);
        int rh=diameter_03(root.right,dia);

        dia[0]=Math.max(dia[0],lh + rh + 2);
        return Math.max(lh,rh) + 1;
    } 

    //112
    public static boolean hasPathSum(TreeNode root,int target){
        if(root==null)
            return false;

        if(root.left==null && root.right==null && target-root.val == 0)
            return true;
         
        return hasPathSum(root.left, target-root.val) || hasPathSum(root.right, target-root.val);    
    }

    //113
    public void pathSum(TreeNode root,List<List<Integer>> ans,List<Integer> smallans,int target){
        if(root==null)
            return;
        
        if(root.left==null && root.right==null && target- root.val == 0){
            List<Integer> base=new ArrayList<>(smallans);
            base.add(root.val);
            ans.add(base);
            return;
        }    
        smallans.add(root.val);

        pathSum(root.left,ans,smallans,target-root.val);
        pathSum(root.right,ans,smallans,target-root.val);

        smallans.remove(smallans.size() - 1);
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> smallans=new ArrayList<>();
        pathSum(root,ans,smallans,targetSum);
        return ans;
    }

    //GFG:Maximum Path Sum between 2 Leaf Nodes 
    public static class leaftoleafPair{
        int LTLMaxSum = -(int)1e9;  //Leave to Leave Max Sum
        int NTLMaxSum = -(int)1e9;  //Node to leave Max Sum
    }

    public static leaftoleafPair maxleafSum(Node root){
        if(root==null)
            return new leaftoleafPair();

        if(root.left==null && root.right == null ){
            leaftoleafPair base=new leaftoleafPair();
            base.NTLMaxSum=root.data;
            return base;
        }  

        leaftoleafPair lRes=maxleafSum(root.left);
        leaftoleafPair rRes=maxleafSum(root.right);

        leaftoleafPair myRes=new leaftoleafPair();
        myRes.LTLMaxSum=Math.max(lRes.LTLMaxSum , rRes.LTLMaxSum);

        if(root.left!=null && root.right!=null){    //checking whether root is not leaf or single child  
            myRes.LTLMaxSum=Math.max(myRes.LTLMaxSum , lRes.NTLMaxSum + rRes.NTLMaxSum + root.data)
        }

        myRes.NTLMaxSum = Math.max(lRes.NTLMaxSum , rRes.NTLMaxSum) + root.data;
        return myRes;
    }

    public int maxPathSum(Node root){
        leaftoleafPair res= maxleafSum(root);
        int ans = res.LTLMaxSum;
        int ans2 = res.NTLMaxSum;
        return ans!= -(int)1e9 ? ans:ans2;
    } 


    //124==================================================================
    public class NTNPair{
        int maxPossibleAns=-(int)1e9;
        int NTNMaxSum=0;    //by putting 0 we dont need to handle leaf case. 
    }

    public int getMax(int ...arr){
        int maxEle = arr[0];
        for(int ele : arr)
            maxEle = Math.max(maxEle,ele);
        return maxEle;
    }

    public NTNPair maxPathSum_(TreeNode root){
        NTNPair myAns = new NTNPair();
        if(root==null)
            return myAns;

        NTNPair left = maxPathSum_(root.left);
        NTNPair right = maxPathSum_(root.right);

        int onesidedMax = Math.max(left.NTNMaxSum,right.NTNMaxSum) + root.val;
        myAns.maxPossibleAns = getMax(left.maxPossibleAns,right.maxPossibleAns,root.val,onesidedMax,left.NTNMaxSum + root.val + right.NTNMaxSum);

        myAns.NTNMaxSum = Math.max(onesidedMax,root.val);
        return myAns; 

    }

    public int maxPathSum(TreeNode root) {
        NTNPair ans=maxPathSum_(root);
        return ans.maxPossibleAns;
    }

    //968
    //{-1 : camera required, 0: already covered, 1: I have a camera}
    public int minCameraCover(TreeNode root,int []countOfCamera){
        if(root==null)  
            return 0;
         
        int lr = minCameraCover(root.left,countOfCamera);
        int rr = minCameraCover(root.right,countOfCamera);

        if(lr == -1 || rr== -1){ 
            countOfCamera[0]++;
            return 1;
        }
        if(lr == 1 || rr == 1)      
            return 0;   
        
        return -1;
    }

    public int minCameraCover(TreeNode root) {
        int countOfCamera[]=new int[1];
        int ans=minCameraCover(root,countOfCamera);
        if(ans==-1)
            countOfCamera[0]++;
        return countOfCamera[0];
    }

    //337
    // {with robbery, without robbery}
    public int[] houseRobIII(TreeNode root){
        if(root==null)
            return new int[2];

        int []lr= houseRobIII(root.left);    
        int []rr= houseRobIII(root.right);
        
        int ans[] = new int[2];
        ans[0] = lr[1] + rr[1] + root.val;  //with robbery
        ans[1] =  Math.max(lr[0],lr[1]) + Math.max(rr[0],rr[1]);    //without robbery

        return ans;
    } 

    public int rob(TreeNode root) {
        int[] ans = houseRobIII(root);

        return Math.max(ans[0], ans[1]);
    }


    //1372
    // {forward, backward, maxZigZag Length}
    public int[] longestZigZag_(TreeNode root){
        if(root==null)
            return new int[]{-1,-1,-1};

        int []lans = longestZigZag_(root.left);    
        int []rans = longestZigZag_(root.right);
        
        int ans[]= new int[3];
        ans[0] = lans[1] + 1;
        ans[1] = rans[0] + 1;
        ans[2] = Math.max(Math.max(lans[2],rans[2]), Math.max(ans[0],ans[1]));

        return ans;
    }

    public int longestZigZag(TreeNode root) {

        int[] ans = longestZigZag_(root);

        return ans[2];

    }
}