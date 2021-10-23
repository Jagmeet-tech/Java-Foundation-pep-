import java.util.ArrayList;
import java.util.Arrays;

public class l003Recursion{
    static String s1 = "send";
    static String s2 = "more";
    static String s3 = "money";

    static int []mapping = new int[128];
    static boolean []usedNumber = new boolean[10]; 

    public static int convertStringToNumber(String s){
        int ans = 0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            ans = ans *  10 + mapping[ch]; 
        }
        return ans;
    }
    
    public static int crypto(String s,int idx){
        if(idx == s.length()){
            if(mapping[s1.charAt(0)] == 0 || mapping[s2.charAt(0)] == 0 || mapping[s3.charAt(0)] == 0 )
                return 0;

            int x = convertStringToNumber(s1); 
            int y = convertStringToNumber(s2); 
            int z = convertStringToNumber(s3);
            
            if(x + y == z){
                StringBuilder sb = new StringBuilder();
                for(int i='a';i<='z';i++){
                    if(mapping[i] >= 0){
                        sb.append((char)i + "-" + mapping[i] + " ");
                    }
                }
                System.out.println(sb.toString());
                return 1; 
            }
            return 0;
        }
       
        int count =0;
        char ch = s.charAt(idx);
        for(int i =0;i<9;i++){
            if(!usedNumber[i]){
                usedNumber[i] = true;
                mapping[ch] = i;

                count+= crypto(s,idx+1);

                usedNumber[i] = false;
                mapping[ch] = -1;
            }
        }
        return count;
    } 


    public static void crypto(){
        String str = s1+s2+s3;
        int freq[] = new int[26];
        for(int i=0;i<str.length();i++){
            freq[str.charAt(i) - 'a']++;
        }

        str = "";
        for(int i=0;i<26;i++){
            if(freq[i] > 0)
                str += (char)(i + 'a');
        }
        // System.out.println(str);
        Arrays.fill(mapping,-1);
        crypto(str,0);
    }


    public static void main(String []args){
        crypto();
    }


    public static int wordBreak(String s,int idx,String ans,HashSet<String> dict,int maxlen){
        if(idx >= s.length()){
            System.out.println(ans);
            return 1;
        }
        
        int count =0;
        for(int i = idx;i<=s.length();i++){
            String word = s.substring(idx,i);
            if(word.length() > maxlen)
                break;
            if(dict.contains(word))
                count += wordBreak(s,i,ans + word + " ",dict,maxlen);    
        }
        return count;
    }

    public static void wordBreak(String s,String ans,HashSet<String> dict){
        int maxlen=0;
        for(String str : dict)
            maxlen = Math.max(maxlen,str.length());
        wordBreak(s,0,ans,dict,maxlen);    
    }














    //sudoku ---------------------------------- 
    
    public static boolean isSafeToPlaceNum(char [][]board ,int row,int col,int num){
        int n=board.length,m=board[0].length;
        //row
        for(int j=0;j<m;j++){
            if((board[row][j]-'0')==num)
                return false;
        }
        //col
        for(int i=0;i<n;i++){
            if((board[i][col]-'0')==num)
                return false;
        }
        //mat
        row=(row/3)*3;
        col=(col/3)*3;     //convert it into sub-matrices of 3X3 by dividing it with 3 and multiply with 3 to get initial coordinate so that i can traverse through 3X3 matrices   
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if((board[i+row][j+col]-'0')==num)
                    return false;
            }
        }
        return true;
    }
    
    public boolean solveSudoku(char[][] board,int idx){
        if(idx == 81){
            return true;
        }

        int r= idx / 9;     //imagine like 1d but actually we wil find r,c as we are working in 2d. (1d to 2d)
        int c= idx % 9;
        if(board[r][c]!='.'){
            if(solveSudoku(board,idx+1))
                return true;
        }
        else{
            for(int num=1;num<=9;num++){        //like permutations recursion.
                if(isSafeToPlaceNum(board,r,c,num)){
                    board[r][c]=(char)(num + '0');    //imagine like 1d but when to place value in 2d find r,c.           
                    if(solveSudoku(board,idx+1))
                        return true;
                    board[r][c]='.';     
                }
            }
        }
        return false;
    }
    
//sudoku optimize in terms of code(storing that indexes that are empty where we can place value. )----------------   

public boolean sudokuSolver(char [][]board,int idx,ArrayList<Integer> IDX){
    if(idx == IDX.size()){
        return true;
    }
    int r=IDX.get(idx) / 9;
    int c=IDX.get(idx) % 9;
    for(int num=1;num<=9;num++){
        if(isSafeToPlaceNum(board,r,c,num)){
            board[r][c]=(char)(num+'0');
            if(sudokuSolver(board,idx+1,IDX))
                return true;
            board[r][c]='.';    
        }
    }
    return false;
}
public void sudokuSolver(char [][]board){
    ArrayList<Integer> IDX=new ArrayList<>(); //containing  index of empty block.
    for(int i=0;i<9;i++){
        for(int j=0;j<9;j++){
            if(board[i][j] =='.')
                IDX.add( i* 9 + j);      //row*9 +col  (1d)
        }
    }
    sudokuSolver(board,0,IDX);
}
    

}