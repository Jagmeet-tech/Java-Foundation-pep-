import java.util.ArrayList;
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

//==============================================================================
public static boolean nodeToRootPath(Node root,int data,ArrayList<Node> ans){
    if(root==null)
		return false;
	boolean res=root.data==data||nodeToRootPath(root.left,data,ans)||nodeToRootPath(root.right,data,ans); //line for finding data ki agr root pe nhi hai toh left pe kro check or right pe kro chck (T||F||F).
	if(res)
		ans.add(root);
	return res;		
  }
 public static void printAtDepthK(Node root,Node block,int k,ArrayList<Integer> ans){
	//preorder tarverse krna hai tabhi Root-Left-Right Question mein given hai. 
	if(root==null || root==block || k<0){
		 return;
	 }
	 if(k==0){
		 ans.add(root.data);
		 return;
	 }
	 printAtDepthK(root.left,block,k-1,ans);
	 printAtDepthK(root.right,block,k-1,ans);
 } 
 public static ArrayList<Integer> printKNodesFar(Node node, Node target, int k) {
	ArrayList<Node> path=new ArrayList<>();
	nodeToRootPath(node,target.data,path);
	ArrayList<Integer> ans =new ArrayList<>();
	Node block=null;
	for(int i=0;i<path.size();i++){
		printAtDepthK(path.get(i),block,k-i,ans);
		block=path.get(i);
	}
	return ans;
  }

 public static void printSingleChildNodes(Node node, Node parent){
    if(node==null)
		return;
	if(parent!=null && (parent.left==null || parent.right==null)){
		System.out.println(node.data);
		return;
	}
	printSingleChildNodes(node.left,node);
	printSingleChildNodes(node.right,node);
  }
  public static Node removeLeaves(Node root){
    if(root==null){
		return null;
	}
	if(root.left==null && root.right==null){
		return null;
	}
	root.left=removeLeaves(root.left);
	root.right=removeLeaves(root.right);
	return root;
  }
  //without return type 
  public static void removeLeaves(Node root,Node par){
	  if(root==null){
		  return null;
	  }
	  if(root.left==null && root.right==null){ //leaf node
		if(par.left==node)
			par.left=null;
		else
			par.right=null;
		return;		
	  }
	  removeLeaves(root.left,root);
	  removeLeaves(root.right,root);
  }
  public static Node removeLeaves(Node root){
	  if(root.left==null && root.right==null)
	  	return null;
	  removeLeaves(root,null);
	  return root;
  } 
  //=======================================================================
  public static Node prev=null;
  public static boolean isBst(Node root){ //inorder traverse and check prev data chota hona chahiye cuurnet root ke data se.
	  if(root==null){
		  return true;
	  }
	  boolean leftRes=isBst(root.left);
	  if(!leftRes) return false;
	  if(prev!=null && prev.data > root.data) return false; 
	  prev=root;
	  boolean rightRes=isBst(root.right);
	  if(!rightRes)	return false;
	  return true;
  }
  public static class isBStSolPair{
	  int maxEle=-(int)1e8;
	  int minEle=(int)1e8;
	  boolean isBst=true;
  }
	  public static isBStSolPair isBst_(Node root){
		  if(root==null){ //khali node bhi bst hota hai.
			//   isBStSolPair base=new isBStSolPair();
			//   base.isBst=true; but by default true hai members mein.
			//   return base;
			return new isBStSolPair(); //one line
		  }  

		  isBStSolPair left=isBst_(root.left);
		  isBStSolPair right=isBst_(root.right);
		  isBStSolPair myres=new isBStSolPair();
		  myres.isBst=false; 
		  if(left.isBst && right.isBst && left.maxEle < root.data && root.data < right.minEle){
			myres.isBst=true;
			myres.minEle=Math.min(root.data,left.minEle);
			myres.maxEle=Math.max(root.data,right.maxEle);
		  }
		  return myres;
 	 }


	 public static class isBalPair{
		int height=-1; //in terms of edges.
		boolean balancefactor=true;
	 }  
	 public static isBalPair isBal_(Node root){
		if(root==null){
			return new isBalPair();
		}
		isBalPair leftRes=isBal_(root.left);
		if(!leftRes.balancefactor) return leftRes;
		isBalPair rightRes=isBal_(root.right);
		if(!rightRes.balancefactor) return rightRes;
		isBalPair myres=new isBalPair();
		myres.balancefactor=false;
		if(leftRes.balancefactor && rightRes.balancefactor && Math.abs(leftRes.height-rightRes.height)<=1){
			myres.balancefactor=true;
			myres.height=Math.max(leftRes.height,rightRes.height)+1;
		}
		return myres;
	 }
	 public static boolean isBal(Node root){
		 isBalPair ans=isBal_(root);
		 return ans.balancefactor;
	 }
	 //==============================================================================================
	 //(In O(n) time only we find all information togther like isBst,height,largestBst,largestBstSize,totalnoBst  by combining all members in a class.)
	 public static class isBalPairAllSoln{
		 int maxEle=-(int)1e8;
		 int minEle=(int)1e8;
		 boolean isBst=true;
		 
		 int height=-1;
		 boolean isBal=true;

		 int totalnoBst=0;
		 Node largestBst=null;
		 int largestBstSize=0;
	 }
	 public static isBalPairAllSoln allSoln(Node root){
		 if(root==null){
			 return new isBalPairAllSoln();
		 }
		 isBalPairAllSoln left=allSoln(root.left);
		 isBalPairAllSoln right=allSoln(root.right);
		 isBalPairAllSoln myans=new isBalPairAllSoln();
		 myans.isBst=left.isBst && right.isBst && left.maxEle < root.data && root.data <right.minEle;
		 myans.isBal=left.isBst && right.isBst && Math.abs(left.height-right.height)<=1;
		
		 myans.maxEle=Math.max(root.data,right.maxEle);
		 myans.minEle=Math.min(root.data,left.minEle);
		 myans.height=left.height+right.height +1;

		 myans.totalnoBst=left.totalnoBst+right.totalnoBst +(myans.isBst ? 1:0);
		 if(myans.isBst){
			 myans.largestBst=root;
			 myans.largestBstSize=left.largestBstSize+right.largestBstSize+1;
		 }
		 else{
			 if(left.largestBstSize > right.largestBstSize){
				 myans.largestBst=left.largestBst;
				 myans.largestBstSize=left.largestBstSize;
			 }
			 else{
				myans.largestBst=right.largestBst;
				myans.largestBstSize=right.largestBstSize;
			 }
		 }
		 return myans;
	}
	
	public static Node Lca(Node root,int d1,int d2){ //Least common Ancestor
		ArrayList<Node> list1=new ArrayList<>();
		ArrayList<Node> list2=new ArrayList<>();
		nodeToRootPath(root,d1,list1); //we have chosen nodetoroot path way in Binary search beacuse values are present anywhere but in BST values are pesent in an order so we compare them and then find Lca.
		nodeToRootPath(root,d2,list2);	
		int i=list1.size()-1;
		int j=list2.size()-1;
		Node lca=null;
		while(i>=0 && j>=0){
			if(list1.get(i)!=list2.get(j))
				break;
			lca=list1.get(i);
			i--;
			j--;
		}
		return lca;
	  }
}