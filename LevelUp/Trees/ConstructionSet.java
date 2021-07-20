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
}