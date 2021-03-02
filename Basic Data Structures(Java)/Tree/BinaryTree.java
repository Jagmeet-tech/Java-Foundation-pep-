public class BinaryTree{
	public static class Node{
		int data=0;
		Node left=null;
		Node right=null;
		Node(int data){
			this.data=data;
		}
	}
	//Traversal================================================================
	public static void preOrder(Node root,ArrayList<Integer> ans){
		if(root==null){
			return;
		}
		ans.add(root.data);
		preOrder(root.left,ans);
		preOrder(root.right,ans);
	}
	public static void inOrder(Node root,ArrayList<Integer> ans){
		if(root==null){
			return;
		}
		inOrder(root.left,ans);
		ans.add(root.data);
		inOrder(root.right,ans);
	}
	public static void postOrder(Node root,ArrayList<Integer> ans){
		if(root==null){
			return;
		}
		postOrder(root.left,ans);
		postOrder(root.right,ans);
		ans.add(root.data);
	}	 
	public static ArrayList<Integer> traversal(Node root){
		ArrayList<Integer> ans=new ArrayList<>();
		preOrder(root, ans);
        // inOrder(root, ans);
        // postOrder(root, ans);
	}
	//Basics Functions=======================================================
	public static int size(Node root){
		if(root==null){
			return 0;
		}
		int leftSize=size(root.left);
		int rightSize=size(root.right);
		return (leftSize+rightSize+1);
	}
	//By default
	public static int height(Node root){ //in terms of edges 
		if(root==null){
			return -1; //beacause leaf node pe koi edge nhi hoti toh 0 rakha agr toh 57 line mein 1 return hojayega jiska matlab 1 edge hai.
		}
		int leftSide=height(root.left);
		int rightSide=height(root.right);
		return (Math.max(leftSide,rightSide)+1);
	}
	public static int heightInTermsOfNode(Node root){ //in terms of edges 
		if(root==null){
			return 0; //beacause leafnode koi bhi consider krna hai. 
		}
		int leftSide=heightInTermsOfNode(root.left);
		int rightSide=heightInTermsOfNode(root.right);
		return (Math.max(leftSide,rightSide)+1);
	}
	public static int maximum(Node root){
		if(root==null){
			return (int)-1e9;
		}
		int leftSide=maximum(root.left);
		int rightSide=maximum(root.right);
		return Math.max(Math.max(leftSide,rightSide),root.data);
	}
	public static int minimum(Node root){
		if(root==null){
			return (int)1e9;
		}
		int leftSide=minimum(root.left);
		int rightSide=minimum(root.right);
		return Math.min(Math.min(leftSide,rightSide),root.data);
	}
	public static int sum(Node root){
		if(root==null){
			return 0;
		}
		int leftSum=sum(root.left);
		int rightSum=sum(root.right);
		return leftSum + rightSum + root.data;
	}
	public static boolean findData(Node root,int data){
		if(root==null){
			return false;
		}
		boolean ans= (root.data==data);
		return ans || findData(root.left,data) || findData(root.right,data); // F||F||T
	}
	public static boolean findData2(Node root,int data){//same as findData but easy coding understanding.
		if(root==null){
			return false;
		}
		if(root.data==data)
			return true;
		return findData2(root.left,data);
		return findData2(root.right,data);	
	} 

}