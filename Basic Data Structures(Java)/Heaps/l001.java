import java.util.PriorityQueue;
public class l001{
    public static void kSortedArray(int []arr,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        int idx=0;
        for(int ele:arr){
            pq.add(ele);
            if(pq.size() > k){
                arr[idx++]=pq.remove();  //removes minimum value by default.
            }   
        }
        while(pq.size()!=0){              
            arr[idx++]=pq.remove();
        }
        for(int ele:arr){
            System.out.println(ele);
        }
    }
    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists){
        ArrayList<Integer> rv=new ArrayList<>();
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->{
            return a[0]-b[0];
        });
        //{ele,index,index-from-list}
        for(int i=0;i<lists.size();i++){
            pq.add(new int[]{ lists.get(i).get(0),0,i });
        }
        while(pq.size()!=0){
            int ar[]=pq.remove();
            rv.add(ar[0]);
            int idx=ar[1];
            int listIdx=ar[2];
            int length=lists.get(listIdx).size();

            if(idx+1 < length){
                ar[1]++;
                ar[0]=lists.get(listIdx).get(idx+1);
                pq.add(ar);
            }
        }
        return rv;
    }
    //same question using divide and conquer
    public static ArrayList<Integer> mergeTwoList(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> ans = new ArrayList<>();
        int i = 0, n = list1.size();
        int j = 0, m = list2.size();

        while (i < n && j < m) {
            if (list1.get(i) < list2.get(j))
                ans.add(list1.get(i++));
            else
                ans.add(list2.get(j++));
        }

        while (i < n) {
            ans.add(list1.get(i++));
        }

        while (j < m) {
            ans.add(list2.get(j++));
        }

        return ans;
    }

    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists, int si, int ei) {
        if (si == ei)
            return lists.get(si);
        int mid = (si + ei) / 2;
        ArrayList<Integer> list1 = mergeKSortedLists(lists, si, mid);
        ArrayList<Integer> list2 = mergeKSortedLists(lists, mid + 1, ei);
        return mergeTwoList(list1, list2);
    }
}