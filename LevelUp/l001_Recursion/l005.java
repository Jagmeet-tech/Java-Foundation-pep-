import java.util.ArrayList;
import java.util.Arrays;

public class l005{
    public static int twoEqualSumSubsets(int []arr,int idx,int sum1,String str1,int sum2,String str2){
        if(idx == arr.length){
            if(sum1 == sum2){
                System.out.println(str1 + " = " + str2);
                return 1;
            }
            return 0;
        }

        int count=0;
        count += twoEqualSumSubsets(arr,idx+1,sum1 + arr[idx] , str1 + arr[idx] + " ",sum2,str2);
        count += twoEqualSumSubsets(arr,idx+1,sum1, str1,sum2 + arr[idx],str2 + arr[idx] + " ");
        
        return count;
    }

    public static void twoProblem() {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        System.out.println(twoEqualSumSubsets(arr,0,0,"",0,""));

    }

    public static int kEqualSumSubset(int arr[],int []totalSetSum,int idx,ArrayList<ArrayList<Integer>> ans,String res){
        if(idx == arr.length){
            for(int ele: totalSetSum){
                if(ele != 0)
                    return 0;
            }
            System.out.println(ans);
            return 1;
        }


        int count = 0;
        for(int i=0;i<totalSetSum.length;i++){
            if( totalSetSum[i] < arr[idx])
                continue;
            
            boolean isFirstTime = false;    // used to find leader of set so that permutations cant happen.
            if(ans.get(i).size() == 0)
                isFirstTime = true;

            totalSetSum[i] -= arr[idx];
            ans.get(i).add(arr[idx]);
            count += kEqualSumSubset(arr,totalSetSum,idx+1,ans,res + arr[idx]+" ");
            
            totalSetSum[i] += arr[idx];
            ans.get(i).remove(ans.get(i).size() - 1);

            if(isFirstTime)
                break;
            
        }

        return count;
    }

    public static ArrayList<ArrayList<Integer>> kEqualSumSubset(int arr[],int k){
        int sum = 0;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int ele : arr)
            sum += ele;

        if(sum % k != 0 || arr.length < k)
            return ans;    
        
        for (int i = 0; i < k; i++)
            ans.add(new ArrayList<>());    
        
        int tar = sum / k;
        int totalSetSum[] = new int[k];
        Arrays.fill(totalSetSum,tar);
        System.out.println(kEqualSumSubset(arr,totalSetSum,0,ans,""));

        return ans;
     }

     
    public static void kSubSetProblem() {
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        kEqualSumSubset(arr, 3);
    }

    public static boolean isPalindrome(String str){
        int si = 0, ei = str.length()-1;
        while(si <= ei){
            if(str.charAt(si++) != str.charAt(ei--))
                return false;
        } 
        return true;
    }

    public static void allPalindromicPartitions(String str, String asf){
        if(str.length() == 0){
            System.out.println(asf);
            return ;
        }
        
        for(int i=0;i<str.length();i++){
            String ans = str.substring(0,i+1);
            if(isPalindrome(ans)){
                allPalindromicPartitions(str.substring(i+1),asf + "(" + ans + ") ");
            }
        }
    }

    //friends pairing======================================
    static int counter=0;
    public static int friendsPairing(int totalFriends, boolean[] vis, String asf) {
        if(totalFriends == 0){
            System.out.println(counter++ + "." + asf);
            return 1;
        }
        
        int firstUnusedFriend = 0;
        for(int i=1;i<vis.length;i++){
            if(!vis[i]){
                firstUnusedFriend = i;
                break; 
            }
        }

        int count = 0;
        vis[firstUnusedFriend] = true;
        count += friendsPairing(totalFriends - 1,vis, asf + "(" + firstUnusedFriend + ") "); // single  friend call

        for(int i = firstUnusedFriend + 1;i<vis.length;i++){
            if(!vis[i]){
                vis[i] = true;
                count += friendsPairing(totalFriends - 2,vis,asf + "(" + firstUnusedFriend + "," + i + ") "); // pairup call
                vis[i] = false;
            }
        }

        vis[firstUnusedFriend] = false;
        return count;
    }

    public static void friendsPairing() {
        int N = 5;
        boolean[] vis = new boolean[N + 1];
        System.out.println(friendsPairing(N, vis, ""));
    }
    

    //largest Number after K swaps========================================================
    static int maxNum = 0;

    public static void swap(StringBuilder sb,int i,int j){
        char ch1 = sb.charAt(i);
        char ch2 = sb.charAt(j);

        sb.setCharAt(i, ch2);
        sb.setCharAt(j, ch1);
    }

    public static void largestNumber(StringBuilder sb ,int k){
        if(k == 0)
            return ;

        boolean flag = false;
        for(int i=0;i<sb.length();i++){
            char maxch = sb.charAt(i);
            int idx = i;
            for(int j= i+1;j<sb.length();j++){
                if(sb.charAt(j) > maxch){
                    maxch = sb.charAt(j);
                    idx = j;
                }
            }
            if(idx == i)
                continue;

            flag = true;    
            swap(sb,i,idx);
            int possibleNum = Integer.parseInt(sb.toString());
            if(possibleNum > maxNum)
                maxNum = possibleNum;

            largestNumber(sb,k-1);
        }
        if(!flag)
            return ;
    }    

    public static void largestNumber() {
        String str = "56294137";
        int k = 4;
        largestNumber(new StringBuilder(str), k);
        System.out.println(maxNum);
    }

    public static void main(String []args){
        // twoProblem();
        // kSubSetProblem();
        // friendsPairing();
        largestNumber();
    }
}