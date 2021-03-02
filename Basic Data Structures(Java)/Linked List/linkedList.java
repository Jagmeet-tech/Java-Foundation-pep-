import java.util.Scanner;
public class linkedList{
    private class Node{ //private because you cant create object outside.
        int data=0;
        Node next=null;
    
        Node(int data){
            this.data=data;
        }
    }
    private Node head=null;
    private Node tail=null;
    private int sizeLL=0;

//Exception=====================================================================
    private void EmptyException()throws Exception{
        if(this.sizeLL==0)
            throw new Exception("Linked List is Empty");
    }
    private void IndexOutOfBoundsSizeInclusive(int idx)throws Exception{
        if(idx<0 || idx>this.sizeLL)
            throw new Exception("Index out of bounds:-1");
    }
    private void IndexOutOfBoundExclusiveSize(int idx)throws Exception{
        if(idx<0 || idx>=this.sizeLL)
            throw new Exception("Index out of bounds:-1");
    }
    public int length(Node node){
        int count=0;
        while(node!=null){
            count++;
            node=node.next;
        }
        return count;
    }
    public void reverseDI(){ 
        Node node=this.head;
        int c1=0;
        int c2=length(node)-1;
        while(c1<c2){
            Node t1=getNodeAt(c1);
            Node t2=getNodeAt(c2);
            int temp=t1.data;
            t1.data=t2.data;
            t2.data=temp;
            c1++;
            c2--;
        }
    }
    //by default first half mid,
    public int mid(){
        Node prev=this.head;
        Node forw=this.head;
        //while(fast!=null && fast.next!=null) //2nd half mid for even
        while(forw.next!=null && forw.next.next!=null){ //first condition for odd list and second is for even list.
            forw=forw.next.next; //2 increase
            prev=prev.next; //1 increase
        }
        return prev.data;
     }
      }
      
      public void reversePI(){
        Node prev=null;
        Node curr=this.head;
        Node forw=this.head.next;
        while(curr!=null){
            forw=curr.next; //backup 
            curr.next=prev; //link
            prev=curr; //moves
            curr=forw; //moves
        }
        this.tail=this.head;
        this.head=prev;
      }  
      public int kthFromLast(int k){
        // Assume valid values of k passed means fast==null nhi ho skta kabhi bhi.(fast.next==null)ho skta hai valid hai
        Node slow=this.head;
        Node fast=this.head;
        while(k-->0){
            fast=fast.next;
        }
        while(fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        return slow.data;    
      }   
//====================================================================================
public static linkedList mergeTwoSortedLists(linkedList l1, linkedList l2) {
    linkedList ans =new linkedList();
    Node node1=l1.head;
    Node node2=l2.head;
    while(node1!=null && node2!=null){
        if(node1.data < node2.data){
            ans.addLastNode(node1.data);
            node1=node1.next;
        }
        else{
            ans.addLastNode(node2.data);
            node2=node2.next;
        }    
    }
    while(node1!=null){
        ans.addLastNode(node1.data);
        node1=node1.next;
    }
    while(node2!=null){
        ans.addLastNode(node2.data);
        node2=node2.next;
    }
    return ans;
  }

  public static Node mid(Node head){
      Node slow=head;
      Node fast=head;
      while(fast.next!=null && fast.next.next!=null){
          slow=slow.next;
          fast=fast.next.next;
      }
      return slow;
  }
  public static linkedList mergeSort(Node head, Node tail){
   //origional list must be preserved it doenot change.
    if(head==tail){
        linkedList base=new linkedList();
        base.addLast(head.data);
        return base;
    }
    Node mid=mid(head);
    Node h1=head;
    Node t1=mid;
    Node h2=mid.next;
    Node t2=tail;
    mid.next=null; //to break the list
    LinkedList firsthalf=mergeSort(h1,t1);
    LinkedList secondhalf=mergeSort(h2,t2);
    mid.next=h2; //to join the list again so that we dont lost the original list
    return mergeTwoSortedLists(firsthalf,secondhalf);
  }
//Display===========================================================================    
    public void display(){
        Node node=this.head;
        while(node!=null){
            System.out.print(node.data+"-> ");
            node=node.next;
        }
    }

//Get==============================================================================
    private Node getNodeAt(int idx){
        Node node =this.head;
        while(idx-->0){
            node=node.next;
        }
        return node;

    }
    public int getAt(int idx)throws Exception{
        EmptyException();
        IndexOutOfBoundExclusiveSize(idx);
        Node node=getNodeAt(idx);
        return node.data;
    }
    public int getLast()throws Exception{
        EmptyException();
        return this.tail.data;
    }
    public int getFirst()throws Exception{
        EmptyException();
        return this.head.data;
    }

//Remove=========================================================================
    private Node removeAtNode(int idx){
        Node node=null;
        if(idx==0)
           return removeFirstNode();
        else if(idx==sizeLL-1)
            return removeLastNode();
        else{
            Node prev=getNodeAt(idx-1);
            Node curr=prev.next;
            prev.next=curr.next;
            curr.next=null;
            this.sizeLL--;
            return curr;
        }        
}
    public int removeAt(int idx)throws Exception{
        EmptyException();
        IndexOutOfBoundExclusiveSize(idx);
        Node node=removeAtNode(idx);
        return node.data;
    }
    private Node removeLastNode(){
        Node node =this.head;
        if(this.sizeLL==1){
            this.head=null;
            this.tail=null;
        }
        else{
            node=this.tail;
            Node temp=getNodeAt(this.sizeLL-2);
            temp.next=null;
            this.tail=temp;
        }
        this.sizeLL--;
        return node;
    }
    public int removeLast()throws Exception{
        EmptyException();
        Node node =removeLastNode();
        return node.data;
    }
    private Node removeFirstNode(){
        Node node=this.head;
        if(this.sizeLL==1){
            this.head=null;
            this.tail=null;
        }
        else{
            this.head=node.next;
            node.next=null;
        }
        this.sizeLL--;    
        return node;
    }
    public int removeFirst()throws Exception{
        EmptyException();
        Node node=removeFirstNode();
        return node.data;
    }

//Add============================================================================
    private void addAtNode(int idx,Node node){
        if(idx==0)
            addFirstNode(node);
        else if(idx==this.sizeLL)   
            addLastNode(node); 
        else{
            Node prev=getNodeAt(idx-1);
            node.next=prev.next;
            prev.next=node;
            this.sizeLL++;
        }    
    }
    public void addAt(int idx,int data)throws Exception{
        IndexOutOfBoundsSizeInclusive(idx); //Exception handled for index(idx)
        Node node=new Node(data);
        addAtNode(idx,node);
    }
    private void addLastNode(Node node){
        if(this.head==null){
            this.head=node;
            this.tail=node;
        }
        else{
            this.tail.next=node;
            this.tail=node;
        }
        this.sizeLL++;
    }
    public void addLast(int data){
        Node node=new Node(data);
        addLastNode(node);
    }
    private void addFirstNode(Node node){
        if(this.head==null){
            this.head=node;
            this.tail=node;
        }
        else{
            node.next=this.head;
            this.head=node;
        }
        this.sizeLL++;
    }
    public void addFirst(int data){
        Node node=new Node(data);
        addFirstNode(node);
    }
}
