import java.util.ArrayList;
public class BinarySearchTree{
    public static class Node{
        int data;
        BinarySearchTree left;
        BinarySearchTree right;
    
        Node(int data){
            this.data=data;
        }
    } 
    public static Node constructTree(int []arr,int si,int ei){
        if(si>ei)  //single node bhi nhi rha 
            return null;
        int mid=(si+ei)/2;
        Node node=new Node(arr[mid]);
        node.left=constructTree(arr,si,mid-1);
        node.right=constructTree(arr,mid+1,ei);
        return node;    
    }
    public static Node constructTree(int arr[]){
        return constructTree(arr,0,arr.length-1);
    }
    public static int size(Node root){
        return root==null ? 0 : size(root.left) + size(root.right)+1;
    }
    public static int height(Node root){ //in terms of edges (by default)
        return root==null ? 0 : Math.max(height(root.left)+height(root.right))+1;
    } 
    public static int sum(Node node) {
        if(node==null)
            return 0;
        return sum(node.left)+sum(node.right)+node.data;
    }
    public static int maximum(Node root){ //Iterative
        Node curr=root;
        while(curr.right!=null){
            curr=curr.right; //kyunki maximum binarysearchtree mein rightmost mein milega. 
        }
        return curr.data;
    }
    public static int maximumRec(Node root){ //Recursion
        if(root.right==null){
            return root.data;
        }
        return maximumRec(root.right);
    }
    public static int minimum(Node root){
        Node curr=root;
        while(curr.left!=null){
            curr=curr.left; //minimum element BST mein leftmost mein hi hoga.
        }
        return curr.data;
    }
    public static boolean find(Node root,int data){
        Node curr=root;
        while(curr!=null){
            if(curr.data==data)
                return true;
            else if(curr.data < data){
                curr=curr.right;
            }
            else    
                curr=curr.left;    
        }
        return false;
    }
    public static Node addNode(Node root,int data){  //recursive
        if(root==null){
            return new Node(data); //created new node and return it.
        }
        if(root.data==data) //if data is present in tree then no need to insert it ,even we cant return null beacuse that loses the node which is present in BSt having value same as data.
        {} 
        else if(root.data < data){
           root.right=addNode(root.right,data);
        }
        else
           root.left=addNode(root.left,data);
        return root;    
    }
    public static Node addNode_Itr(Node root,int data){  //Iterative
        if(root==null)  
            return new Node(data);

        Node curr=root; //traverse
        Node add=new Node(data);
        while(true){  // we cant write curr!=null or curr.right!=null beacuse its iterative one and we have to know where to add node whether to right or left and we cant make curr null.  
            if(curr.data==data) //beacuse duplicate values insert nhi kr skte.
                break;
            else if(curr.data<data){
                if(curr.right!=null)  // check krna pdega ki right mein insert kr skte hai ki right mein node hai jiske baad insert krna hai.
                    curr=curr.right;
                else{
                    curr.right=add;
                    break;
                }
            }
            else{
                if(curr.left!=null)
                    curr=curr.left;
                else{    
                    curr.left=add;
                    break;
                }    
            }   
        }
        return root;
    }
    public static Node Lca(Node root,int p ,int q){ //Least common Ancestor
        Node curr=root;
        while(curr!=null){ 
            if(curr.data < p && curr.data < q){ //means p and q are on right side.
                curr=curr.right;
            }
            else if(curr.data > p && curr.data > q){ //means p and q are on left side.
                curr=curr.left;
            }
            else{ /*means curr break ho rha hai matlab p left mein and q right mein ho skta hai toh curr hi LCA hai but essa bhi ho skta hai ki p and q BST mein ho na. 
                  sidha curr bhi return kr skte the but interviewer se puchna ki p and q tree mein hai ya nhi. 
                  and find agar curr se kr rhe hai toh time complexity kam ho jayegi bajye root se kre. */
                return (find(curr,p) && find(curr,q)?curr:null);
            }
        }
        return null;
    } 
    public static void pir(Node node, int d1, int d2) { //print in Range inclusive.
        if(node==null)
            return;
        pir(node.left,d1,d2);  //doing inorder traversal because asked in question to print in increasing order.
        if(node.data >= d1 && node.data <= d2 )
            System.out.println(node.data);
        pir(node.right,d1,d2);        
    }


    public static Node removeNode(Node root ,int data){
        if(root==null)
            return null;
        if(data > root.data)
            root.right=removeNode(root.right,data);
        else if(data < root.data)
            root.left=removeNode(root.left,data);
        else{  //data equal
            if(root.left==null || root.right==null) //handles single child and leaf node cases.
                return root.left!=null ? root.left :root.right;
            int maxData=maximum(root.left); //two childs
            root.data=maxData;
            root.left=removeNode(root.left,maxData);
        }            
        return root;
    }
    public static void inOrder(Node root,ArrayList<Integer> ans){
        if(root==null)
            return;
        inOrder(root.left,ans);
        ans.add(root.data);
        inOrder(root.right,ans);
    }
    public static void targetSumPair(Node root,int target){
        ArrayList<Integer> ans=new ArrayList<>();
        inOrder(root,ans);
        int i=0,j=ans.size()-1;
        while(i!=j){
            int sum=ans.get(i)+ans.get(j);
            if(sum>target)
                j--;
            else if(sum<target)
                i++;
            else{
                System.out.println(ans.get(i)+" "+ans.get(j));
                i++;
                j--;
            }        
        }
    }
}