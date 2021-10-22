public class l002RecursionTrees{
    public static int permutationWithInfi(int arr[],int target,String ans){       
        if(target == 0){
            System.out.println(ans);
            return 1;
        }
        int count =0;
        for(int coin : arr){
            if(target - coin >= 0)
                count += permutationWithInfi(arr,target - coin,ans + coin);
        }
        return count;
    }

    //If we sort arr[] then some calls reduces. 
    public static int combinationWithInfi(int arr[],int target,int idx,String ans){
        if(idx == arr.length || target == 0){
            if(target == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        for(int i = idx;i<arr.length;i++){
            if(target - arr[i] >= 0)
                count += combinationWithInfi(arr,target - arr[i],i,ans + arr[i]);
        }

        return count;
    }

    public static int combinationWithSingle(int arr[],int target,int idx,String ans){
        if(idx == arr.length || target == 0){
            if(target == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        for(int i = idx;i<arr.length;i++){
            if(target - arr[i] >= 0)
                count += combinationWithInfi(arr,target - arr[i],i+1,ans + arr[i]);
        }

        return count;
    }

    //here vis[] act as used coin.
    public static int permutationWithSingleCoin(int arr[],int target,String ans,boolean vis[]){
        if(target == 0){
            System.out.println(ans);
            return 1;
        }

        int count =0;
        for(int i=0;i<arr.length;i++){
            if(target - arr[i] >=0 && !vis[i]){
                vis[i] = true;
                count += permutationWithSingleCoin(arr,target-arr[i],ans + arr[i],vis);
                vis[i] = false;
            }
        }
        return count;
    }

    //without using vis[]
    public static int permutationWithSingleCoin(int arr[],int target,String ans){
        if(target == 0){
            System.out.println(ans);
            return 1;
        }

        int count =0;
        for(int i=0;i<arr.length;i++){
            if(arr[i] >0 && target - arr[i] >=0){
                int val = arr[i];

                arr[i] = -arr[i];
                count += permutationWithSingleCoin(arr,target-val,ans + arr[i]);
                arr[i] = -arr[i];
            }
        }
        return count;
    }

    //============================================================================================
    public static int permutationWithInfi_Subseq(int arr[],int target,int idx,String ans){
        if(target == 0 || idx == arr.length){
            if(target == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }   
        
        int count = 0;
            if(target - arr[idx] >=0)
                count += permutationWithInfi_Subseq(arr,target - arr[idx],0,ans + arr[idx]);
            count += permutationWithInfi_Subseq(arr,target - arr[idx],idx + 1,ans + arr[idx]);
        return count;
    }


    public static int combinationWithInfi_Subseq(int arr[],int target,int idx,String ans){
        if(target ==0 || idx == arr.length){
            if(target == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count=0;
        if(target-arr[idx] >=0)
            count += combinationWithInfi_Subseq(arr,target-arr[idx],idx,ans+arr[idx]);
        count += combinationWithInfi_Subseq(arr,target,idx+1,ans);
        

        return count;
    }

    public static int combinationWithSingle_Subseq(int arr[],int target,int idx,String ans){
        if(target ==0 || idx == arr.length){
            if(target == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        
        int count = 0;
        if(target - arr[idx] >=0)
            count += combinationWithSingle_Subseq(arr,target-arr[idx],idx+1,ans+arr[idx]);
        count += combinationWithSingle_Subseq(arr,target,idx+1,ans);
        
        return count;
    }

    public static int permutationWithSingle_Subseq(int arr[],int target,int idx,String ans){
        if(target == 0 || idx == arr.length){
            if(target ==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(arr[idx] > 0 && target-arr[idx] >=0){
            int val = arr[idx];
            arr[idx] = -arr[idx];
            count += permutationWithSingle_Subseq(arr,target-val,0,ans+val);
            arr[idx] = -arr[idx];
        }
        count += permutationWithSingle_Subseq(arr,target,idx + 1,ans); // include na krne ki call
        return count;
    }

     // 1D_Queen_Set=================================================================================

    // tboxes = total Boxes, tqn = total queen, qpsf = queen placed so far, bn = box_no,
    public static int queenCombination(int tboxes,int tqn,int qpsf,int bn,String ans){  //similar like combinationWithSingle()
        if(qpsf == tqn){
            System.out.println(ans);
            return 1;
        }
       
        int count =0;
        for(int i = bn;i<tboxes;i++){
            count += queenCombination(tboxes,tqn,qpsf + 1,i+1,ans + "b" + i + "q" + qpsf + " ");
        }

        return count;
    }

    //similar like permutationWithSingle()
    public static int queenPermutation(boolean tboxes[],int tqn,int qpsf,String ans){
        if(qpsf == tqn){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i=0;i<tboxes.length;i++){
            if(!tboxes[i]){
                tboxes[i] = true;
                count += queenPermutation(tboxes,tqn,qpsf + 1, ans + "b" + i + "q" + qpsf + " ");
                tboxes[i] = false;
            }
        }
        return count;
    }
    

    public static void main(String []args){
        int arr[]={2,3,5,7};
        boolean vis[] =new boolean[5];
        boolean tboxes[] = new boolean[6];
        // System.out.println(permutationWithInfi(arr,10,""));
        // System.out.println(combinationWithInfi(arr,10,0,""));
        // System.out.println(combinationWithSingle(arr,10,0,""));
        // System.out.println(permutationWithSingleCoin(arr,10,"",vis));
        // System.out.println(combinationWithInfi_Subseq(arr,10,0,""));
        // System.out.println(combinationWithSingle_Subseq(arr,10,0,""));
        // System.out.println(permutationWithSingle_Subseq(arr,10,0,""));
        // System.out.println(queenCombination(6,4,0,0,""));
        System.out.println(queenPermutation(tboxes,4,0,""));
    }
}