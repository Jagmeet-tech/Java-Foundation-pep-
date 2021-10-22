import java.util.Arrays;
public class l004CrossWord{
    static char[][] box = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' }, { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' }, { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

    static String[] words = { "agra", "norway", "england", "gwalior" };

    public static boolean[] placeH(String word,int r,int c){
        boolean []loc = new boolean[word.length()];
        for(int i=0;i<word.length();i++){
            if(box[r][c+i] == '-'){
                loc[i] = true;
                box[r][c+i] = word.charAt(i);
            }
        }
        return loc;
    }

    public static boolean[] placeV(String word,int r,int c){
        boolean []loc = new boolean[word.length()];
        for(int i=0;i<word.length();i++){
            if(box[r+i][c] == '-'){
                loc[i] = true;
                box[r+i][c] = word.charAt(i);
            }
        }
        return loc;
    }

    public static void unplaceH(boolean []loc,int r,int c){
        for(int i=0;i<loc.length;i++){
            if(loc[i]){
                loc[i] = false;
                box[r][c+i] = '-';
            }
        }
    }

    public static void unplaceV(boolean []loc,int r,int c){
        for(int i=0;i<loc.length;i++){
            if(loc[i]){
                loc[i] = false;
                box[r+i][c] = '-';
            }
        }
    }

    public static boolean isSafeToPlaceWordH(String word,int r,int c){
        for(int i=0;i<word.length();i++){
            if(c + i >= box[0].length)
                return false;
            
            if(box[r][c+i] != '-' && box[r][c+i] != word.charAt(i))
                return false;
        }
        return true;
    }

    public static boolean isSafeToPlaceWordV(String word,int r,int c){
        for(int i=0;i<word.length();i++){
            if(r + i >= box.length)
                return false;
            
            if(box[r+i][c] != '-' && box[r+i][c] != word.charAt(i))
                return false;
        }
        return true;
    }

    public static int crossWord(int idx){
        if(idx == words.length){
            print2D();
            return 1;
        }

        int n = box.length , m = box[0].length , count = 0;
        String word = words[idx];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(box[i][j] == '-' || box[i][j] == word.charAt(0)){
                    if(isSafeToPlaceWordH(word,i,j)){
                        boolean []loc = placeH(word,i,j);
                        count += crossWord(idx + 1);
                        unplaceH(loc,i,j);
                    }

                    if(isSafeToPlaceWordV(word,i,j)){
                        boolean []loc = placeV(word,i,j);
                        count += crossWord(idx + 1);
                        unplaceV(loc,i,j);
                    }
                }
            }
        }
        return count; 
    }

    public static void print2D(){
        for(int i=0;i<box.length;i++){
            for(int j=0;j<box[0].length;j++){
                System.out.print(box[i][j] +" ");
            }
            System.out.println();
        }
    }

    public static void crossWord(){
        Arrays.sort(words,(a,b)->{
            return b.length() - a.length();
        });

        System.out.println(crossWord(0));
    }

    public static void main(String []args){
        crossWord();
    }
}