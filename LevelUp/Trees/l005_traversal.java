public class l005_traversal{
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode getRightMostNode(TreeNode node ,TreeNode curr){
        while(node.right!=null && node.right!=curr)
            node=node.right;
        return node;    
    }

    public static ArrayList<Integer> morrisInOrderTraversal(TreeNode root) {
        ArrayList<Integer> ans=new ArrayList<>();
        TreeNode curr=root;
        while(curr!=null){
            TreeNode leftNode=curr.left;
            if(leftNode == null){
                ans.add(curr.val);
                curr=curr.right;
            }else{  
                TreeNode rightMostNode = getRightMostNode(leftNode,curr);
                if(rightMostNode.right == null){    //thread creation
                    rightMostNode.right=curr;
                    curr=curr.left;
                }else{
                    rightMostNode.right=null;   //thread destroy
                    ans.add(curr.val);
                    curr=curr.right;
                }
            }
        }
        return ans;
    }

    public static TreeNode getRightMostNode(TreeNode node ,TreeNode curr){
        while(node.right!=null && node.right!=curr)
            node=node.right;
        return node;    
    }

    public static ArrayList<Integer> morrisPreOrderTraversal(TreeNode root) {
        ArrayList<Integer> ans=new ArrayList<>();
        TreeNode curr=root;
        while(curr!=null){
            TreeNode leftNode=curr.left;
            if(leftNode == null){
                ans.add(curr.val);
                curr=curr.right;
            }else{  
                TreeNode rightMostNode = getRightMostNode(leftNode,curr);
                if(rightMostNode.right == null){
                    rightMostNode.right=curr;   //thread creation
                    ans.add(curr.val);
                    curr=curr.left;
                }else{
                    rightMostNode.right=null;   //thread destroy
                    curr=curr.right;
                }
            }
        }
        return ans;
    }

 // validate BST==============================================

    public static boolean isValidBST(TreeNode root, TreeNode[] prev){
        if(root==null)
            return true;

        if(!isValidBST(root.left,prev))
            return false;

        if(prev[0]!=null && prev[0].val >= root.val)
            return false;

        prev[0]=root;

        if(!isValidBST(root.right,prev))
            return false;

        return true;    
    }

    public static boolean isValidBST_02(TreeNode root){
        long prev=-(long)1e13;
        TreeNode curr=root;
        while(curr!=null){
            TreeNode leftNode=curr.left;
            if(leftNode==null){
                if(prev >= curr.val)
                    return false;
                prev=curr.val;
                curr=curr.right;    
            }else{
                TreeNode rightMostNode = getRightMostNode(leftNode,curr);
                if(rightMostNode.right==null){
                    rightMostNode.right=curr;   //thread creation
                    curr=curr.left;
                }else{
                    rightMostNode.right=null;
                    if(prev >= curr.val)
                        return false;
                    prev=curr.val;
                    curr=curr.right;    
                }
            }
        }
        return true;
    }

    public static int kthSmallest(TreeNode root, int k){
        TreeNode curr=root;
        while(curr!=null){
            TreeNode leftNode= curr.left;
            if(leftNode ==null){
                if(--k == 0)
                    return curr.val;
                curr=curr.right;    
            }else{
                TreeNode rightMostNode = getRightMostNode(leftNode,curr);
                if(rightMostNode.right == null){
                    rightMostNode.right=curr;   //thread creation
                    curr=curr.left;
                }else{
                    rightMostNode.right=null;
                    if(--k == 0)
                        return curr.val;
                    curr=curr.right;    
                }
            }
        }
        return -1;
    }

    public static TreeNode getLeftMostNode(TreeNode node,TreeNode curr){
        while(node.left!=null && node.left!=curr)
            node=node.left;
        return node;    
    }

    public static int kthLargest(TreeNode root,int k){
        TreeNode curr=root;
        while(curr!=null){
            TreeNode rightNode=curr.right;
            if(rightNode == null){
                if(--k == 0)
                    return curr.val;
                curr=curr.left;    
            }else{
                TreeNode leftMostNode= getLeftMostNode(rightNode,curr);
                if(leftMostNode.left == null){
                    leftMostNode.left=curr;
                    curr=curr.right;
                }else{
                    leftMostNode.left=null;
                    if(--k == 0)
                        return curr.val;
                    curr=curr.left;       
                }
            }        
        }
        return -1;
    }

    public static Node bToDLL(Node root){
        Node dummy=new Node(-1);
        Node curr=root,prev=dummy;
        while(curr!=null){
            Node leftNode=curr.left;
            if(leftNode==null){
                prev.right=curr;
                curr.left=prev;
                prev=curr;
                curr=curr.right;
            }else{
                Node rightMostNode=getRightMostNode(leftNode,curr);
                if(rightMostNode.right==null){  //thread creation
                    rightMostNode.right=curr;
                    curr=curr.left;
                }else{
                    rightMostNode.right=null;   //thread destroy
                    prev.right=curr;
                    curr.left=prev;
                    prev=curr;
                    curr=curr.right;
                }
            }
        }
        Node head=dummy.right;
        dummy.right=head.left=null; //to make circular Dbll
        prev.right=head;
        head.left=prev;
        return head;
    }


    //173
    class BSTIterator {

        TreeNode curr=null;
        public BSTIterator(TreeNode root) {
            this.curr=root;
        }
        
        public int next() {
            int rv=-1;
            while(this.curr!=null){
            TreeNode leftNode =this.curr.left;
            if(leftNode==null){
                rv=this.curr.val;
                curr=curr.right;
                break;
            }else{
                TreeNode rightMostNode=getRightMostNode(leftNode,this.curr);
                if(rightMostNode.right==null){
                    rightMostNode.right=curr;
                    curr=curr.left;
                }else{
                    rightMostNode.right=null;
                    rv=this.curr.val;
                    curr=curr.right;
                    break;
                }
            }
        }
            return rv;
    }
        
        public boolean hasNext() {
            return this.curr!=null;
        }
    }

}