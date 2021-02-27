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

 public int mid(){
     Node t1=this.head,int i=0,j=this.sizeLL-1;
     Node t2=this.tail;
     while(i<j){
         t1=t1.next;
         t2=t2.next;
         i++;
         j--;
     }
     if(i==j)
        return t1.data;
     else
        return t2.data;   
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
