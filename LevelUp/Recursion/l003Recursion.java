public class l003Recursion{
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