public class ConstructionSet{

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode constructFromInOrder(int[] inOrder,int si ,int ei) {
       if(si>ei)
            return null;
       
       int mid = (si+ei)/2;
       TreeNode root=new TreeNode(inOrder[mid]);
       root.left= constructFromInOrder(inOrder,si,mid-1);
       root.right= constructFromInOrder(inOrder,mid+1,ei);

       return root;
    }

    public static int idx=0;
    public static TreeNode bstFromPreorder(int[] preorder,int ll,int rr) {
        if(idx == preorder.length || preorder[idx] < ll || preorder[idx] > rr )
            return null;

        TreeNode root=new TreeNode(preorder[idx++]);
        root.left=bstFromPreorder(preorder,ll,root.val);    
        root.right=bstFromPreorder(preorder,root.val,rr);
        return root;    
    }    
    public static  TreeNode bstFromPreorder(int[] preorder) {
        idx=0;
         int ll=-(int)1e9;
        int rr=(int)1e9;
        return bstFromPreorder(preorder,ll,rr);
    }



    public static TreeNode bstFromPostorder(int[] postorder,int ll,int rr) {
        if(idx == -1|| postorder[idx] < ll || postorder[idx] > rr )
            return null;

        TreeNode root=new TreeNode(postorder[idx--]);
        root.right=bstFromPostorder(postorder,root.val,rr);
        root.left=bstFromPostorder(postorder,ll,root.val);    
        return root;    
    }    
    public static TreeNode bstFromPostorder(int[] postorder) {
        idx=postorder.length-1;
        int ll=-(int)1e9;
        int rr=(int)1e9;
        return bstFromPostorder(postorder,ll,rr);
     }     


     public static class bstLPair{
        TreeNode par=null;
        int lr=0;
        int rr=0;

        bstLPair(TreeNode par, int lr,int rr){
            this.par=par;
            this.lr=lr;
            this.rr=rr;
        }
    }

    public static TreeNode constructBSTFromLevelOrder(int[] LevelOrder){
        if(LevelOrder.length==0)
           return null;
         
        int idx=0; 
        LinkedList<bstLPair> que=new LinkedList<>();
        TreeNode root= new TreeNode(LevelOrder[idx++]);     
        que.addLast(new bstLPair(root,-(int)1e9,root.val));    
        que.addLast(new bstLPair(root,root.val,(int)1e9));

        while(idx < LevelOrder.length){
            bstLPair rp=que.removeFirst();
            int lr=rp.lr,rr=rp.rr;
            if(LevelOrder[idx] < lr || LevelOrder[idx] > rr)
               continue;
            
            TreeNode node =new TreeNode(LevelOrder[idx++]);
            if(node.val < rp.par.val)
               rp.par.left=node;
            else
                rp.par.right=node;
            
            que.addLast(new bstLPair(node, rp.lr, node.val));
           que.addLast(new bstLPair(node, node.val, rp.rr));   
        }
        return root;
    }

    //psi=preorder starting index ,pei=preoder ending index
    public static TreeNode preOrIn(int[] pre, int psi, int pei, int[] in, int isi, int iei) {
        if(isi > iei)
            return null;
        int idx=isi;
        while(pre[psi] != in[idx])
            idx++;
        int tel=idx-isi;    //tel=total no. of elements.
        
        TreeNode root=new TreeNode(pre[psi]);
        root.left=preOrIn(pre,psi+1,psi+tel,in,isi,idx-1);
        root.right=preOrIn(pre,psi+tel+1,pei,in,idx+1,iei);

        return root;
    }

    public static TreeNode postOrIn(int[] post, int psi, int pei, int[] in, int isi, int iei) {
        if (isi > iei)
            return null;
        int idx = isi;
        while (in[idx] != post[pei])
            idx++;

        int tel = idx - isi;
        TreeNode root = new TreeNode(post[pei]);

        root.left = postOrIn(post, psi, psi + tel - 1, in, isi, idx - 1);
        root.right = postOrIn(post, psi + tel, pei - 1, in, idx + 1, iei);

        return root;
    }


    //889
    public TreeNode constructFromPrePost(int[] pre,int psi,int pei,int[] post,int ppsi,int ppei) {
        if(psi > pei)
            return null;
        
        TreeNode root = new TreeNode(pre[psi]);
        if(psi == pei)  //case when array contain 1 element.
            return root;
        int idx = ppsi;
    
        while(post[idx] != pre[psi+1])
            idx++;
         
        int tnel = idx - ppsi + 1; // [a,b] = b-a+1
        
        root.left = constructFromPrePost(pre,psi + 1,psi + tnel,post,ppsi,idx);
        root.right = constructFromPrePost(pre,psi + tnel + 1,pei,post,idx + 1,ppei - 1);

        return root;
    }
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return constructFromPrePost(pre,0,pre.length-1,post,0,post.length-1);
    }

     // HM_:https://practice.geeksforgeeks.org/problems/construct-tree-from-inorder-and-levelorder/1

    // https://www.geeksforgeeks.org/check-if-given-preorder-inorder-and-postorder-traversals-are-of-same-tree/

    //297
    public void serialize_preorder(TreeNode root,StringBuilder sb) {
        if(root == null){
            sb.append("# ");
            return;
        }

        sb.append(root.val+" ");
        serialize_preorder(root.left,sb);
        serialize_preorder(root.right,sb);
    }

    public String serialize_preorder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize_preorder(root,sb);
        return sb.toString();
    }

    public TreeNode deserialize_preorder(String []arr, int[] idx) {
        if(idx[0] >= arr.length || arr[idx[0]].equals("#")){
            idx[0]++;
            return null;
        }

        int i = idx[0]++;
        int val = Integer.parseInt(arr[i]);

        TreeNode root = new TreeNode(val);
        root.left = deserialize_preorder(arr,idx);
        root.right = deserialize_preorder(arr,idx);
        
        return root;
    }

    public TreeNode deserialize_preorder(String data) {
        String []arr = data.split(" ");
        int []idx = new int[1];
        
        return deserialize_preorder(arr,idx);
    }
}