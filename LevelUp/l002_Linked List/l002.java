public class l002{
    
    public static int length(ListNode head){
        ListNode curr=head;
        int len=0;
        while(curr!=null){
            len++;
            curr=curr.next;
        }
        return len;
    }

    ListNode th=null , tt=null;
    
    public static void addFirstNode(ListNode node){
        if(th==null){
            th=tt= node;
        }else{
            node.next=th;
            th=node;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head.next==null || k==1 ){
            return head;
        }
        int len=length(head);
        ListNode curr=head;
        ListNode ph=null,pt=null; //ph-permanent head
        while(curr!=null && len >= k){
            int itr=k;
            while(itr-- > 0){
                ListNode forw=curr.next;
                curr.next=null;
                addFirstNode(curr);
                curr=forw;
            }

            if(ph==null){
                ph=th;
                pt=tt;
            }else{
                pt.next=th;
                pt=tt;
            }
            th=th=null;
            len-=k;
        }

        pt.next=curr;
        return ph;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head.next==null || m==n){
            return head;
        }   

        ListNode curr=head,prev=null;
        int i=1;
        while(curr!=null){
            while(i>=m && i<=n){
                ListNode forw=curr.next;
                curr.next=null;
                addFirstNode(curr);
                curr=forw;
                i++;
            }
            
            if(i>n){
                if(prev==null){
                    tt.next=curr;
                    return th;
                }else{
                    prev.next=th;
                    tt.next=curr;
                    return head;
                }                
            }

            prev=curr;
            curr=curr.next;
            i++;
        }
        return null;    
    }

    public static ListNode removeDuplicates(ListNode head) {
        if(head==null || head.next==null)
            return head;
        
        ListNode prev=head,curr=head.next;
        while(curr!=null){      //it checks unequal values then also curr becames null .
            while(curr!=null && prev.val==curr.val){    //it checks similar values then also curr becames null.
                ListNode forw=curr.next;
                curr.next=null;
                curr=forw;
            }

            prev.next=curr;
            prev=prev.next;
            if(curr!=null)
                curr=curr.next;
        } 
        return head;   
    }    

    public static ListNode removeAllDuplicates(ListNode head) {
        if(head==null || head.next==null)
            return head;
        
        ListNode dummy=new ListNode(-1);    
        ListNode curr=head.next,prev=dummy;
        prev.next=head;

        while(curr!=null){  //it checks unequal values then also curr becames null .
            boolean isLoopRun=false;
            while(curr!=null && prev.next.val==curr.val){  //it checks similar values then also curr becames null.
                ListNode forw=curr.next;
                curr.next=null;     //breaking the link
                curr=forw;
                isLoopRun=true;
            }
            
            if(isLoopRun){
                prev.next=curr; //new value check kro duplicate hai ya nhi.
            }else{
                prev=prev.next;
                prev.next=curr;
            }

            if(curr!=null)
                curr=curr.next;
        }    
        return dummy.next;
    }
    
    
    // Copy List with Random Pointer
    public static void copyNodes(ListNode head){
        ListNode curr=head;
        while(curr!=null){
            ListNode forw=curr.next;
            ListNode newNode =new ListNode(curr.val);
           
            curr.next=newNode;
            newNode.next=forw;

            curr=forw;
        }
    }

    public static void copyRandom(ListNode head){
        ListNode curr=head;
        while(curr!=null){
            if(curr.random!=null)
                curr.next.random=curr.random.next;
            
            curr=curr.next.next;
        }
    }

    public static Node extractList(ListNode head){
        ListNode dummy=new ListNode(-1);
        ListNode curr=head,prev=dummy;

        while(curr!=null){
            prev.next=curr.next;
            prev=prev.next;
            curr.next=curr.next.next;
            curr=curr.next;
        }
        
        return dummy.next;
    }

    public Node copyRandomList(Node head) {
        copyNodes(head);
        copyRandom(head);
        return extractList(head);

    }
}