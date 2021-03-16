import java.util.ArrayList;
public class GenericTree{
    public static class Node{
        int data=0;
        ArrayList<Node> childs=new ArrayList<>();
        Node(int data){
            this.data= data;
        }
    }
    public static int size(Node root){
        int sz=0;
        for(Node child:root.childs){
            sz+=size(child);
        }
        return sz+1;
    }
    public static int height(Node root){ //by default in terms of edges.
        int h=-1; //0 for return height in terms of nodes,-1 in terms of edges.
        for(Node child:root.childs){
            h=Math.max(h,height(child));
        }
        return h+1;
    }
    public static int maximum(Node root){
        int maxValue=root.data;
        for(Node child:root.childs){
            maxValue=Math.max(maxValue,maximum(child));
        }
        return maxValue;
    }
    public static int minimum(Node root){
        int minValue=root.data;
        for(Node child:root.childs){
            minValue=Math.min(minValue,minimum(child));
        }
        return minValue;
    }
    public static boolean find(Node root,int data){
       boolean res=root.data==data;
       for(Node child:root.childs){
           res=res|| find(child,data); // ||- can be used as the replacement for if-else. 
       }   
       return res;
    }
    public static boolean nodeToRootPath(Node root,int data,ArrayList<Node> ans){
        boolean res=root.data==data;
        for(Node child:root.childs){
            res=res||nodeToRootPath(child,data,ans);
        }
        if(res)
            ans.add(root);
        return res;    
    }
    public static Node lca(Node node, int d1, int d2) { //lowest common ancestor.
        ArrayList<Node> list1=new ArrayList<>();
        ArrayList<Node> list2=new ArrayList<>();
        nodeToRootPath(root,d1,list1);
        nodeToRootPath(root,d2,list2);
        int i=list1.size()-1;
        int j=list2.size()-1;
        Node lca=null;
        while(i>=0 && j>=0){
            if(list1.get(i)!=list2.get(j)) //comparing nodes
                break;
            lca=list1.get(i);    
            i--;
            j--;    
        }
        return lca;
    }
    public static int distBetweenNodes(Node node, int d1, int d2) { //lowest common ancestor.
        ArrayList<Node> list1=new ArrayList<>();
        ArrayList<Node> list2=new ArrayList<>();
        nodeToRootPath(root,d1,list1);
        nodeToRootPath(root,d2,list2);
        int i=list1.size()-1;
        int j=list2.size()-1;
        //Node lca=null;
        int lcaDist=0;
        while(i>=0 && j>=0){
            if(list1.get(i)!=list2.get(j)) //comparing nodes
                break;
           // lca=list1.get(i);
            lcaDist++;    
            i--;
            j--;    
        }
        //int dis=list1.size()+list2.size()-2*(lcaDist) +1;//count dist. in terms of nodes   and +1 is for lca eliminated two times.
        int dis=list1.size()+list2.size()-2*(lcaDist) +1-1;//count dist. in terms of edges
        return dis;
    }


    static int main_data=(int)1e8; //Using static 
    public static int kthLargest(Node root,int k){
        while(k-->0){
           main_data=maximum(root,main_data);
        }
        return main_data;
    }
    public static int maximum(Node root,int data){
        int maxValue=-(int)1e8;
        for(Node child:root.children){
            if(Math.max(maxValue,maximum(child,data)) < data)
                maxValue=Math.max(maxValue,maximum(child,data));
        }
        if(maxValue>root.data)
            return maxValue;
        else
            return root.data;    
    }
} 