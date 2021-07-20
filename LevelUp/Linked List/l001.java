public class l001{
    
    public static ListNode midNode(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next!=null && fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }    
        return slow;
    }
    
    public static ListNode midNode2(ListNode head) {  //gets second midNode.
        if(head==null || head.next==null)
            return head;
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next!=null && fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        if(fast.next!=null){
            slow=slow.next;
        }
        return slow;
    }
    
    public static ListNode reverseLL(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode prev=null;
        ListNode curr=head;
        while(curr!=null){
            ListNode forw=curr.next;        //backup
            curr.next=prev;             //link
            prev=curr;              //move
            curr=forw;
        }    
        return prev;
    }
    
    public static boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null)
            return true;
        ListNode midNode=midNode(head);
        ListNode nHead=midNode.next;
        midNode.next=null;
        nHead=reverseLL(nHead);
        ListNode c1=head,c2=nHead;
        boolean isPal=true;
        while(c2!=null){
            if(c1.val!=c2.val){
                isPal=false;
                break;
            }
            c1=c1.next;
            c2=c2.next;    
        }
        nHead=reverseLL(nHead);
        midNode.next=nHead;
       return isPal;
    }
    
    public static void fold(ListNode head) {
        if(head==null ||head.next==null)
            return;
        ListNode mid=midNode(head);
        ListNode nHead=mid.next;
        mid.next=null;
        nHead=reverseLL(nHead);
        ListNode c1=head,c2=nHead;
        while(c2!=null){
            ListNode forw1=c1.next;       //jo sabse aage hote hai jaise forw1 && forw2 unke null   aane ke chances zyada hai toh unko pehle place kro.
            ListNode forw2=c2.next;
            c1.next=c2;
            c2.next=forw1;
            c1=forw1;
            c2=forw2;
        }   
    }
    
    public static void unfold(ListNode head) {
        if(head==null || head.next==null)
            return ;
        ListNode c1=head,nHead=c1.next,c2=c1.next;
        while(c2!=null && c2.next!=null){
            c1.next=c2.next;
            c1=c2.next;
            c2.next=c1.next;
            c2=c1.next;
        }    
        c1.next=null;
        nHead=reverseLL(nHead);
        c1.next=nHead;
    }


    //merge Sorted Two List.
    public static ListNode mergeTwoLists(ListNode l1,ListNode l2){
        if(l1==null || l2==null)
            return (l1==null)?l2:l1;
        ListNode dummy=new ListNode(-1);
        ListNode p=dummy;
        ListNode c1=l1,c2=l2;
        while(c1!=null && c2!=null){
            if(c1.val < c2.val){
                p.next=c1;
                p=c1;
                c1=c1.next;
            }else{
                p.next=c2;
                p=c2;
                c2=c2.next;
            }
        }
        if(c1!=null)
            p.next=c1;
        if(c2!=null)
            p.next=c2;
        dummy=dummy.next;
        return dummy;    
    }


    public static ListNode mergeKLists(ListNode[] lists) {
       if(lists.length==0)
            return null;
        ListNode head=null;
        for(ListNode node : lists)
            head=mergeTwoLists(head,node);
        return head;
    }


    // T : O(Nklogk), S : O(logK) -> N *k times of (avg length Of Linkedlist),
    // where k is length of lists.
    //Using Divide And Conquer Technique.
    public static ListNode mergeKList(ListNode[] lists, int si, int ei) {
        if(si==ei){
            return lists[si];
        }
        int mid=(si+ei)/2;
        ListNode leftmergeLists=mergeKList(lists,si,mid);
        ListNode rightmergeLists=mergeKList(lists,mid+1,ei);
        return mergeTwoLists(leftmergeLists,rightmergeLists);
    }


    public static ListNode mergeKLists(ListNode[] lists){
        if(lists.length==0)
            return null ;
        return mergeKList(lists,0,lists.length-1);    
    }


    public static ListNode mergeSort(ListNode head) {
     if(head==null || head.next==null)
        return head;
     ListNode mid=midNode(head);
     ListNode nHead=mid.next;
     mid.next=null; //breaks into two lists.but these list are unsorted also.so first sort them and then combine them.
     return mergeTwoLists(mergeSort(head), mergeSort(nHead));     
    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null || n<=0)
            return head;
        ListNode slow=head;
        ListNode fast=head;
        while(n-- >0)
            fast=fast.next;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        ListNode p=slow.next; 
        if(fast==null){
            slow.next=null;
            head=p;
        }else{
            slow.next=p.next;
            p.next=null;
        } 
        return head; 
    }
    
    public static ListNode segregateEvenOdd(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode dummyOdd=new ListNode(-1);   
        ListNode dummyEven=new ListNode(-1);
        ListNode ep=dummyEven,op=dummyOdd,curr=head;
        while(curr!=null){
            if((curr.val % 2) !=0){
                op.next=curr;
                op=curr;
            }else{
                ep.next=curr;
                ep=curr;
            }
            curr=curr.next;
        }
        op.next=null;
        ep.next=dummyOdd.next;
        head=dummmyEven.next;
        // dummyOdd = dummmyEven =null;
        return head;
    }

    public static void swap(ListNode ptr,ListNode itr){
        // ListNode temp=ptr;
        //  ptr.val=itr.val;
        //  itr.val=temp.val;
        int val=ptr.val;
        ptr.val=itr.val;
        itr.val=val;
        
    }
    public static ListNode segregate01(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode ptr=head;
        ListNode itr=head;
        while(itr!=null){
            if(itr.val == 0){
                swap(ptr,itr);
                ptr=ptr.next;
            }
            itr=itr.next;    
        }
        return head;    
    }

    public static ListNode segregate012(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode zero=new ListNode(-1);
        ListNode one=new ListNode(-1);
        ListNode two=new ListNode(-1);
        ListNode zp=zero,op=one,tp=two,curr=head;
        while(curr!=null){
            if(curr.val == 0){
                zp.next=curr;
                zp=curr;
            }else if(curr.val == 1){
                op.next=curr;
                op=curr;
            }else{
                tp.next=curr;
                tp=curr;
            }
            curr=curr.next;
        }
        op.next=two.next;
        zp.next=one.next;
        tp.next=null;
        head=zero.next;
        //zero.next=one.next=two.next=null;
        return head;
    }

    public static ListNode segregateOnLastIndex(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode small=new ListNode(-1);
        ListNode large=new ListNode(-1);
        ListNode sp=small,lp=large,curr=head;
        ListNode pivotNode=head;
        while(pivotNode.next!=null)
            pivotNode=pivotNode.next;
        while(curr!=null){
            if(curr.val <= pivotNode.val){
                sp.next=curr;
                sp=curr;
            }else{
                lp.next=curr;
                lp=curr;
            }
            curr=curr.next;
        }
        lp.next=null;
        sp.next=large.next;
        // head=small.next;
        //small.next=large.next=null;
        return sp;   //returning only values greater than equal to pivot.   
    }

    public static ListNode segregate(ListNode head, int pivotIdx) {
        if(head==null || head.next==null)
            return head;
        ListNode small=new ListNode(-1);    
        ListNode large=new ListNode(-1);
        ListNode sp=small,lp=large,curr=head;
        ListNode pivotNode=head;
        while(pivotIdx-- > 0)
            pivotNode=pivotNode.next;
        while(curr!=null){
            if(curr!=pivotNode && curr.val <= pivotNode.val ){
                sp.next=curr;
                sp=curr;
            }else if(curr!=pivotNode){
                lp.next=curr;
                lp=curr;
            }
            curr=curr.next;
        } 
        lp.next=null;
        sp.next=pivotNode;
        pivotNode.next=large.next;
        head=small.next;
        return head;       
      }


      public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);

        ListNode dummy = new ListNode(-1);
        ListNode c1 = l1, c2 = l2, prev = dummy;
        int carry = 0;
        while (c1 != null || c2 != null || carry != 0) {
            int sum = carry + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);
            carry = sum / 10;
            sum %= 10;

            prev.next = new ListNode(sum);
            prev = prev.next;

            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        ListNode head = dummy.next;

        head = reverseLL(head);
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);

        return head;
    }

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);

        ListNode dummy = new ListNode(-1);
        ListNode c1 = l1, c2 = l2, prev = dummy;
        int borrow = 0;
        while (c1 != null || c2 != null) {
            int diff = borrow + (c1 != null ? c1.val : 0) - (c2 != null ? c2.val : 0);
            if (diff < 0) {
                diff += 10;
                borrow = -1;
            } else
                borrow = 0;

            prev.next = new ListNode(diff);
            prev = prev.next;

            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        ListNode head = dummy.next;
        head = reverseLL(head);

        while (head != null && head.val == 0) // 1000000000 - 99999999 = 1, 999 - 999 = 0
            head = head.next;

        l1 = reverseLL(l1);
        l2 = reverseLL(l2);

        return head;
    }

      public static boolean isCyclePresentInLL(ListNode head) {
        if(head==null || head.next==null)
            return false;
        
        ListNode slow=head;    
        ListNode fast=head;
        while(fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(fast==slow)
                return true;
        }
        return false;    
    }

    public static ListNode CycleNode(ListNode head) {   //return the node where the cycle begins.
        if(head==null || head.next==null)
            return null;
    
        ListNode slow=head;    
        ListNode fast=head;
        while(fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(fast==slow)
                break;
        }

        if(fast!=slow)
            return null;
        
        slow=head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next; //same speed.
        }
        return slow;    
    }


    public static ListNode CycleNode2(ListNode head) {   //return the node where the cycle begins.
        if(head==null || head.next==null)
            return null;
    
        ListNode slow=head;    
        ListNode fast=head;
        while(fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(fast==slow)
                break;
        }

        if(fast!=slow)
            return null;
        
        slow=head; 

        boolean isLoopRun=false;        //Used for case when starting point and meeting point are same and tail length is 0.
        
        ListNode meetingPoint=fast;   
        
        int a=1, b=0, c=0, bc=0, nDash=0, n=0, count=0;   // bc is (b + c)
        
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
            
            if(nDash==0 && fast == meetingPoint)
                bc=count;
            if(fast == meetingPoint)
                nDash++;
            a++;
            count++;
            isLoopRun=true;
        }

        if(!isLoopRun){
            fast=fast.next;
            bc=1;
            while(fast!=slow){
                fast=fast.next;
                bc++;
            }

            a=0;    //we know this value by dry run when r=2 in copy.
            c=0;
            n=1;
            nDash=0;
            b=bc;
        }else{
            n=nDash + 1;
            c = a - (bc)*nDash;
            b=bc-c;            
        }
        System.out.println("Length Of Tail is:" + a);
        System.out.println("Length Of b is:" + b);
        System.out.println("Length Of c is:" + c);
        System.out.println("No Of rotation by fast pointer before meeting point:" + n);
        System.out.println("No Of rotation by fast pointer after meeting point:" + nDash);

        return slow;
    }

    public static ListNode IntersectionNodeInTwoLL(ListNode headA, ListNode headB) {    //Floyad Cycle method.
        if(headA==null || headB ==null)
            return null;
         
        ListNode tail=headA;
        while(tail.next!=null)
            tail=tail.next;
        
        tail.next=headB;
        ListNode intersectNode=CycleNode(headA);
        tail.next=null;
        
        return intersectNode;
    }

    public static ListNode IntersectionNodeInTwoLL(ListNode headA, ListNode headB) {    //using difference method.
        ListNode t1=headA;
        ListNode t2=headB;
        int size1=0,size2=0;
        while(t1!=null){
            size1++;
            t1=t1.next;
        }
        while(t2!=null){
            size2++;
            t2=t2.next;
        }
        t1=headA;
        t2=headB;
        int diff=Math.abs(size1-size2);
        if(size1 > size2){
            for(int i=0;i<diff;i++)
                    t1=t1.next;
        }else{
            for(int i=0;i<diff;i++)
                    t2=t2.next;
        }
        while(t1!=t2){
            t1=t1.next;
            t2=t2.next;
        }
        return t1;  
    }

}