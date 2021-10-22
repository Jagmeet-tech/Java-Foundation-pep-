 class l001Avl{
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        int bal=0;
        int height=0;

        TreeNode(int val) {
            this.val = val;
            this.bal=0;
            this.height=0;
        }
    }
//=====================================================================

    //O(1)
    public static void updateHeightBalance(TreeNode root){
        int lh=root.left!=null ? root.left.height:-1;   //height in terms of edges. 
        int rh=root.right!=null ? root.right.height:-1;   //height in terms of edges.
        
        root.bal=lh-rh;
        root.height=Math.max(lh,rh)+1;
    }

    //O(1)
    public static TreeNode rightRotation(TreeNode A){
        TreeNode B=A.left;
        TreeNode BKaRight=B.right;

        B.right=A;
        A.left=BKaRight;

        updateHeightBalance(A);
        updateHeightBalance(B);
        
        return B;
    }

    //O(1)
    public static TreeNode leftRotation(TreeNode A){
        TreeNode B=A.right;
        TreeNode BKaLeft=B.left;
        
        B.left=A;
        A.right=BKaLeft;

        updateHeightBalance(A);
        updateHeightBalance(B);

        return B;
    }

    //O(1)
    public static TreeNode getRotations(TreeNode root){
        updateHeightBalance(root);
       
        if(root.bal==2){    //ll,lr
            if(root.left.bal==1){   //ll
                return rightRotation(root);
            }else{    //lr
                root.left=leftRotation(root.left);
                return rightRotation(root);
            }
        }
        else if(root.bal== -2){  //rr,rl
            if(root.right.bal==-1){   //rr
                return leftRotation(root);
            }else{    //rl
                root.right=rightRotation(root.right);
                return leftRotation(root);
            }
        }
        return root;
    }
//================================================================================== 

    //O(logn)
    public static  TreeNode addNode(TreeNode root, int val) {
        if(root==null)
            return new TreeNode(val);
        if(root.val < val)
            root.right=addNode(root.right,val); 
        else
            root.left=addNode(root.left,val);  
        
        root= getRotations(root);    
        return root;    
    }

    //O(logn)
    public static int getMaximum(TreeNode root){
        while(root.right!=null)
            root=root.right;   
        return root.val;    
    }

    //O(logn)
    public static TreeNode deleteNode(TreeNode root, int key) {
        if(root==null)
            return null;
         
        if(root.val < key)
            root.right=deleteNode(root.right,key); 
        
        else if(root.val > key)
            root.left=deleteNode(root.left,key);
        else{        //equal
            if(root.left==null || root.right==null)     //single child or no child case
                return root.left!=null ? root.left:root.right;
            else{
                int maxData=getMaximum(root.left);
                root.val=maxData;
                root.left=deleteNode(root.left,maxData);
            }
        }     
        root=getRotations(root);       
        return root;
    }

    // O(n)
    public static void display(TreeNode node) {
        if (node == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append((node.left != null ? node.left.val : "."));
        sb.append(" -> " + node.val + " <- ");
        sb.append((node.right != null ? node.right.val : "."));

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = null;
        for (int i = 1; i <= 100; i++) {
            root = addNode(root, i * 10);
        }

        display(root);
    }

    // 1382    
}