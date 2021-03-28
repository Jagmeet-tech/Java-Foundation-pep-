    public static void printStar(int n){
        int nst=1,nsp=3;
        int a=0,b=1,c;
        for(int r=1;r<=n;r++){
            for(int cst=0;cst<nst;cst++){
                System.out.print(a+"\t");
                c=a+b;
                a=b;
                b=c;
            }
            nst+=1;
            System.out.println();
        }
    }
//==========================================================================

 public static int pow(int num,int sb){
         int pow=1;
        while(num!=0){
            num/=10;
            pow*=sb;
        }
        return pow/sb;
    }
     public static int anyBaseToDecimal(int num,int sb){
         int pow=pow(num,sb);
         //System.out.println(pow);//64
         int val=0;
         int num1=num;
         int len=0;
         while((num1/10)!=0){
             num1/=10;
             len++;
         }
         
         while(num!=0){
             int rem=num /(int)Math.pow(10,len);
             val=rem*pow+val;
             num%=(int)Math.pow(10,len);
             len--;
             pow/=sb;
         }
         return val;
     }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    Scanner scn =new Scanner(System.in);
        int num=scn.nextInt();
        int sb=scn.nextInt();
        int db=scn.nextInt();
        int val=anyBaseToDecimal(num,sb);
        
        int pow2=1; 
        int bin=0;
        while(val!=0){
            int res=val%db;
            bin=res*pow2+bin;
            val=val/db;
            pow2=pow2*10;
            //System.out.println(bin);
        }
        System.out.println(bin);
       
    }
//=============================================================================

   public static void famous(int n,int k){
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;n!=0;i++,n--){
            int no=scn.nextInt();
            if(ans.size()==0)
                  ans.add(no);
            else{
                while(ans.size()>0 && ans.get(ans.size()-1) < no && k-->0){
                    ans.remove(ans.size()-1);
                }
                ans.add(no);
            }
        }
        for(int ele:ans)
            System.out.print(ele+" ");
        System.out.println();
    }
