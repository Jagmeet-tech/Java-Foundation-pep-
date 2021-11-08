import java.util.ArrayList;

public class l001Basics{
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.val = data;
            this.left = null; 
            this.right = null; 
        }
    }

    public static int size(TreeNode root){
        if(root == null)
            return 0;
        return size(root.left) + size(root.right) + 1;     
    }

    //in terms of edges--default
    public static int height(TreeNode root){
        if(root == null)
            return -1;
        return Math.max(height(root.left),height(root.right)) + 1;    
    }


    //this helps to find nodeToRootPath ..
    public static boolean find(TreeNode root,int data){
        if(root == null)
            return false;
        
        if(root.val == data)
            return true;

        return find(root.left,data) || find(root.right,data);        
    }

    public static int maximum(TreeNode root){
        if(root == null)
            return -(int)1e9;
        return Math.max(Math.max(maximum(root.left),maximum(root.right)),root.val);    
    }

    public static ArrayList<TreeNode> nodeToRootPath(TreeNode root,int data){
        if(root == null)
            return new ArrayList<>();

        if(root.val == data){
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }    

        ArrayList<TreeNode> left = nodeToRootPath(root.left,data);
        if(left.size() != 0){
            left.add(root);
            return;
        }

        ArrayList<TreeNode> right = nodeToRootPath(root.right,data);   
        if(right.size() != 0){
            right.add(root);
            return;
        }
        
        return new ArrayList<>();   //matlab yha ans nhi mila.
    } 

    public static boolean nodeToRootPath(TreeNode root,int data,ArrayList<TreeNode> ans){
        if(root == null)
            return false;

        if(root.val == data){
            ans.add(root);
            return true;
        } 
        
        boolean res = nodeToRootPath(root.left,data,ans) || nodeToRootPath(root.right,data,ans);
        if(res)
            ans.add(root);
            
        return res;    
    }

    public static void rootToAllLeafPath(TreeNode root,ArrayList<ArrayList<TreeNode>> ans,ArrayList<TreeNode> smallans){
        if(root == null)
            return ;
        if(root.left == null && root.right == null){
            ArrayList<TreeNode> base = new ArrayList<>(smallans);
            base.add(root);
            ans.add(base);
            return;
        }
        
        smallans.add(root);
        rootToAllLeafPath(root.left,ans,smallans);
        rootToAllLeafPath(root.right,ans,smallans);

        smallans.remove(smallans.size()-1);   
    }

    public static ArrayList<ArrayList<TreeNode>> rootToAllLeafPath(TreeNode root){
        ArrayList<ArrayList<TreeNode>> ans = new ArrayList<>();
        ArrayList<TreeNode> smallans = new ArrayList<>();
        rootToAllLeafPath(root,ans,smallans);
        return ans;
    }

    public static void singleChildNodes(TreeNode root,ArrayList<Integer> ans){
        if(root == null || (root.left == null && root.right == null))
            return ;
        if(root.left == null || root.right == null)
            ans.add(root.val);
            
        singleChildNodes(root.left,ans);    
        singleChildNodes(root.right,ans);    
            
    }

    public static int countSingleChildNodes(TreeNode root){
        if(root == null || (root.left == null && root.right == null))
            return 0;
        
       int left = countSingleChildNodes(root.left);
       int right = countSingleChildNodes(root.right);

       int count = left + right;
       if(root.left == null || root.right == null)
            count++;
        return count;
    }

    public static void kDown(TreeNode root,int k,TreeNode blockNode,List<Integer> ans){
        if(root == null || root == blockNode || k < 0)
            return ;

        if(k == 0){
            ans.add(root.val);
            return;
        }    

        kDown(root.left,k-1,blockNode,ans);    
        kDown(root.right,k-1,blockNode,ans);    
    }

    public static List<Integer> distanceK(TreeNode root,TreeNode targetNode,int k){
        ArrayList<TreeNode> path = new ArrayList<>();
        nodeToRootPath(root,targetNode.val,path);

        List<Integer> ans = new ArrayList<>();
        TreeNode blockNode = null;
        for(int i=0;i<path.size();i++){
            if(k-i < 0)
                break;
            kDown(path.get(i),k-i,blockNode,ans); 
            blockNode = path.get(i);
        }

        return ans;
    }

    //optimize in terms of space O(1)..
    public static int distancek_01(TreeNode root,TreeNode targetNode,int k,ArrayList<Integer>ans){
        if(root == null){
            return -1;
        }

        if(root == targetNode){
            kDown(root,k,null,ans);
            return 1;
        }

        int ld = distancek_01(root.left,targetNode,k,ans);
        if(ld != -1){
            kDown(root,k-ld,root.left,ans);
            return ld + 1;
        }

        int rd = distancek_01(root.right,targetNode,k,ans);
        if(rd != -1){
            kDown(root,k-rd,root.right,ans);
            return rd + 1;
        }
        return -1;        
    }

    //burning tree........................................................

    public static void kDown(TreeNode root,int time,TreeNode blockNode,ArrayList<ArrayList<Integer>> ans){
        if(root == null || root == blockNode)
            return ;
        
        if(ans.size() == time)
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        kDown(root.left,time + 1,blockNode,ans);
        kDown(root.right,time + 1,blockNode,ans);
    }

    public static int burningTree(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans){
        if(root == null){
            return -1;
        }

        if(root.val == target){
            kDown(root,0,null,ans);
            return 1;
        }

        int ld = burningTree(root.left,target,ans);
        if(ld != -1){
            kDown(root,ld,root.left,ans);
            return ld+1;
        }

        int rd = burningTree(root.right,target,ans);
        if(rd != -1){
            kDown(root,rd,root.right,ans);
            return rd+1;
        }

        return -1;
    }

}