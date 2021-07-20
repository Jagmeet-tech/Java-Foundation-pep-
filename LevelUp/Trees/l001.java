import java.util.LinkedList;
import java.util.ArrayList;
public class l001{
    
    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> ans= new ArrayList<>();
       if(root==null)
           return ans;
       LinkedList<TreeNode> queue= new LinkedList<>();
       queue.addLast(root);
       int size=0,level=0;
       while(queue.size()!=0){
           ArrayList<Integer> smallans=new ArrayList<>();
           size=queue.size();
           while(size-->0){  //same level
               TreeNode rnode=queue.removeFirst();
               smallans.add(rnode.val);

               if(rnode.left!=null)
                   queue.addLast(rnode.left);
               
               if(rnode.right!=null)
                   queue.addLast(rnode.right);    

           }
           ans.add(smallans);
           level++;
       }
      return ans;
   }

    public static ArrayList leftView(TreeNode root){
       
       ArrayList<Integer> ans=new ArrayList<>();
        if(root==null)
           return ans;
       LinkedList<TreeNode> queue= new LinkedList<>();
       queue.addLast(root);
       int size=0;
       while(queue.size()!=0){
           size=queue.size();
           ans.add(queue.getFirst().val);
           while(size-->0){  //same level
               TreeNode rnode=queue.removeFirst();

               if(rnode.left!=null)
                   queue.addLast(rnode.left);
               
               if(rnode.right!=null)
                   queue.addLast(rnode.right);    

           }
       }
      return ans;
   }

    public List<Integer> rightView(TreeNode root){
       
       List<Integer> ans=new ArrayList<>();
        if(root==null)
           return ans;
       LinkedList<TreeNode> queue= new LinkedList<>();
       queue.addLast(root);
       int size=0;
       while(queue.size()!=0){
           size=queue.size();
           ans.add(queue.getFirst().val);
           while(size-->0){  //same level
               TreeNode rnode=queue.removeFirst();
               
               if(rnode.right!=null)
                   queue.addLast(rnode.right);    

               if(rnode.left!=null)
                   queue.addLast(rnode.left);

           }
       }
      return ans;
   }   

   //length:minMax[1]-minMax[0]+1
   public static void widthOfShadow(TreeNode root,int vl,int minMax[]){
       if (root== null)
            return ;

       minMax[0]=Math.min(vl,minMax[0]);     
       minMax[1]=Math.max(vl,minMax[1]);     
       widthOfShadow(root.left,vl-1,minMax);     
       widthOfShadow(root.right,vl+1,minMax);     
   }



//==============================================================================
    public class ListNode{
        int data=0;
        ListNode left=null;
        ListNode right=null;

        ListNode(int data){
            this.data=data;
        }
    }

    public static void verticalOrderSum(TreeNode root,ListNode node){
        
        node.data+=root.data;
        if(root.left!=null){
            if(node.prev==null){
                ListNode nnode=new ListNode(0);
                nnode.next=node;
                node.prev=nnode;
            }
            verticalOrderSum(root.left,node.prev);
        }

        if(root.right!=null){
            if(node.next==null){
                ListNode nnode=new ListNode(0);
                nnode.prev=node;
                node.next=nnode;
            }
            verticalOrderSum(root.right,node.next);
        }
    }

    public static void verticalOrderSum(TreeNode root){
        ListNode curr=new ListNode(0);
        verticalOrderSum(root,curr);
    }


    public static void diagonalOrderSum(TreeNode root,ListNode node){
        
        node.data+=root.data;
        if(root.left!=null){
            if(node.prev==null){
                ListNode nnode=new ListNode(0);
                nnode.next=node;
                node.prev=nnode;
            }
            diagonalOrderSum(root.left,node.prev);
        }

        if(root.right!=null){
            diagonalOrderSum(root.right,node);
        }
    }

    public static void verticalOrderSum(TreeNode root){
        ListNode curr=new ListNode(0);
        diagonalOrderSum(root,curr);
    }    


    public static class allSoluPair {
        TreeNode pred = null;
        TreeNode succ = null;

        int ceil = (int) 1e9;
        int floor = -(int) 1e9;

        TreeNode prev = null;
    }

    public static void allSolution(TreeNode node, int data, allSoluPair pair) {
        if (node == null)
            return;

        if (node.val < data)
            pair.floor = Math.max(pair.floor, node.val);

        if (node.val > data)
            pair.ceil = Math.min(pair.ceil, node.val);

        allSolution(node.left, data, pair);

        if (node.val == data)
            pair.pred = pair.prev;

        if (pair.prev != null && pair.prev.val == data)
            pair.succ = node;

        pair.prev = node;

        allSolution(node.right, data, pair);
    }
}
}