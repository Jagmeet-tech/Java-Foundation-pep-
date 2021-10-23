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

    //2d queen Set=======================================================================================
    // tboxes = total Bpxes, tqn = total queen, qpsf = queen placed so far, bn =
    // box_no,(solve by imaging like 1d but to find coordinate imagine 2d)
    public static int queenCombination2D(boolean [][]boxes,int tqn,int bn,String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }
        
        int n=boxes.length, m = boxes[0].length,count =0 ;
        for(int i=bn;i<n*m;i++){
            int r = i / m;
            int c = i % m;

            if(!boxes[r][c]){
                boxes[r][c] = true;
                count += queenCombination2D(boxes,tqn - 1,i + 1,ans + "(" + r + ", " + c + ") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }

    public static int queenPermutation2D(boolean [][]boxes,int tqn,String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }
        
        int n=boxes.length, m = boxes[0].length,count = 0 ;
        for(int i = 0;i < n*m;i++){
            int r = i / m;
            int c = i % m;

            if(!boxes[r][c]){
                boxes[r][c] = true;
                count += queenPermutation2D(boxes,tqn - 1,ans + "(" + r + ", " + c + ") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }

    //Nqueen series===========================================================================
    
    public static boolean isSafeToPlaceQueen(boolean [][]boxes,int x,int y){
       int dir[][] ={{-1,1},{-1,0},{-1,-1},{0,-1}};   // for nqueenCombination_01()
        // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };   // for nqueenPermutation_01()

        int n = boxes.length,m = boxes[0].length;
        for(int d=0;d < dir.length;d++){
            for(int rad=1;rad < Math.max(n,m);rad++){
                int r = x + rad * dir[d][0];
                int c = y + rad * dir[d][1];
                if(r>=0 && c>=0 && r<n && c<m){
                    if(boxes[r][c])
                        return false;
                }
                else 
                    break;
            }
        }
        return true;
    }

    public static int nqueenCombination_01(boolean [][]boxes,int tqn,int idx,String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }    

        int count = 0,n = boxes.length,m = boxes[0].length;
        for(int i = idx;i<n*m;i++){
            int r = i / m;
            int c = i % m;
            if(isSafeToPlaceQueen(boxes,r,c)){
                boxes[r][c] = true;
                count += nqueenCombination_01(boxes,tqn-1,i+1,ans + "(" + r + ", " + c + ") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }

    public static int nqueenPermutation_01(boolean [][]boxes,int tqn,int idx,String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }    

        int count = 0,n = boxes.length,m = boxes[0].length;
        for(int i = idx;i<n*m;i++){
            int r = i / m;
            int c = i % m;
            if(!boxes[r][c]){
                if(isSafeToPlaceQueen(boxes,r,c)){
                    boxes[r][c] = true;
                    count += nqueenPermutation_01(boxes,tqn-1,0,ans + "(" + r + ", " + c + ") ");
                    boxes[r][c] = false;
                }
            }
        }
        return count;
    }

    public static int nqueenCombinationSubseq_02(boolean [][]boxes,int tqn,int idx,String ans){
        int count=0,n = boxes.length,m = boxes[0].length;
        if(idx >= n*m || tqn == 0){
            if(tqn == 0){
                System.out.println(ans);
                return 1;
            }
            return 0; 
        }
        
        int r = idx / m;
        int c = idx % m; 
        if(isSafeToPlaceQueen(boxes,r,c)){
            boxes[r][c] = true;
            count += nqueenCombinationSubseq_02(boxes,tqn-1,idx + 1,ans + "(" + r + ", " + c + ") ");
            boxes[r][c] = false;
        }
        count += nqueenCombinationSubseq_02(boxes,tqn,idx + 1,ans);
        return count;
    }

    //Using shadow technique...
    static boolean rows[];
    static boolean cols[];
    static boolean Adiag[];
    static boolean diag[];

    static int calls=0;

    public static int nqueenCombination_03(int tqn,int idx,int n,int m,String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        calls++;
        for(int i =idx;i<n*m;i++){
            int r = i / m;
            int c = i % m;
            if(!rows[r] && !cols[c] && !diag[r+c] && !Adiag[r-c + (m-1)]){
               rows[r] = cols[c] = diag[r+c] = Adiag[r-c + (m-1)] = true;

               count += nqueenCombination_03(tqn-1,i+1,n,m,ans + "(" + r + ", " + c + ") ");
               
               rows[r] = cols[c] = diag[r+c] = Adiag[r-c + (m-1)] = false;
            }
        }
        return count;
    }

    public static int nqueenPermutation_03(int tqn,int idx,int n,int m,String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        calls++;
        for(int i =idx;i<n*m;i++){
            int r = i / m;
            int c = i % m;
            if(!rows[r] && !cols[c] && !diag[r+c] && !Adiag[r-c + m-1]){
               rows[r] = cols[c] = diag[r+c] = Adiag[r-c + m-1] = true;

               count += nqueenPermutation_03(tqn-1,0,n,m,ans + "(" + r + ", " + c + ") ");
               
               rows[r] = cols[c] = diag[r+c] = Adiag[r-c + m-1] = false;
            }
        }
        return count;
    }

    //   O(n)^q  Highly optimized  Nqueen:-  in terms of no. of calls..............
    public static int NqueenCombination_04(int floor,int tnq,int m,String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }
        
        int count = 0;
        calls++;
        for(int rooms =0;rooms<m;rooms++){
            int r = floor, c = rooms;
            if(!rows[r] && !cols[c] && !diag[r+c] && !Adiag[r-c + m-1]){
                rows[r] = cols[c] = diag[r+c] = Adiag[r-c + m-1] = true;

                count += NqueenCombination_04(floor + 1,tnq - 1,m,ans + "(" + r + ", " + c + ") ");
                
                rows[r] = cols[c] = diag[r+c] = Adiag[r-c + m-1] = false;
            }  
        }
        return count;
    }

    public static int NqueenPermutation_04(int floor,int tnq,int m,String ans){
        if(tnq == 0 || floor >= m){
            if(tnq == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        
        int count = 0;
        calls++;
        for(int rooms = 0;rooms<m;rooms++){
            int r = floor , c = rooms;
            if(!rows[r] && !cols[c] && !diag[r+c] && !Adiag[r-c + m-1]){
                rows[r] = cols[c] = diag[r+c] = Adiag[r-c + m-1] = true;

                count += NqueenPermutation_04(0,tnq - 1,m,ans + "(" + r + ", " + c + ") ");
                
                rows[r] = cols[c] = diag[r+c] = Adiag[r-c + m-1] = false;
            }
        }
        count += NqueenPermutation_04(floor + 1,tnq,m,ans);
        return count;
    }


    public static void main(String []args){
        int arr[]={2,3,5,7};
        int n=4,m=4;
        boolean vis[] =new boolean[5];
        boolean tboxes[] = new boolean[6];
        boolean boxes[][] = new boolean[4][4];


        rows = new boolean[n];
        cols = new boolean[m];
        diag = new boolean[n + m - 1];
        Adiag = new boolean[n + m - 1];
        // System.out.println(permutationWithInfi(arr,10,""));
        // System.out.println(combinationWithInfi(arr,10,0,""));
        // System.out.println(combinationWithSingle(arr,10,0,""));
        // System.out.println(permutationWithSingleCoin(arr,10,"",vis));
        // System.out.println(combinationWithInfi_Subseq(arr,10,0,""));
        // System.out.println(combinationWithSingle_Subseq(arr,10,0,""));
        // System.out.println(permutationWithSingle_Subseq(arr,10,0,""));
        // System.out.println(queenCombination(6,4,0,0,""));
        // System.out.println(queenPermutation(tboxes,4,0,""));
        // System.out.println(queenPermutation2D(boxes,4,""));
        // System.out.println(nqueenCombination_01(boxes,4,0,""));
        // System.out.println(nqueenPermutation_01(boxes,4,0,""));
        // System.out.println(nqueenCombinationSubseq_02(boxes,4,0,""));
        // System.out.println(nqueenCombination_03(4,0,n,m,""));
        // System.out.println(nqueenPermutation_03(4,0,n,m,""));
        // System.out.println(NqueenCombination_04(0,4,m,""));
        System.out.println(NqueenPermutation_04(0,4,m,""));
        System.out.println(calls);

    }
}