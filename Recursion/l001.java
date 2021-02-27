import java.util.*;
public class l001
{
    public static Scanner scn=new Scanner(System.in);
    
    public static int getCountAscii2(String str,String ans)
    {
        if(str.length()==0)
        {
            return 1;
        }
        int count=0;
        char ch=str.charAt(0);
        String ros=str.substring(1);
        count+=getCountAscii2(ros,""+ans);
        count+=getCountAscii2(ros,ch+ans);
        count+=getCountAscii2(ros,(int)ch+ans);
        return count;
    }
    public static void getAscii2(String str,String ans)
    {
        if(str.length()==0)
        {
            System.out.println(ans);
            return;
        }
        char ch=str.charAt(0);
        String ros=str.substring(1);
        getAscii2(ros,""+ans);
        getAscii2(ros,ans+ch);
        getAscii2(ros,ans+(int)ch);
    }
    public static ArrayList<String> getAscii(String str)
    {
        if(str.length()==0)
        {
            ArrayList<String> base=new ArrayList<String>();
            base.add("");
            return base;
        }
        char ch=str.charAt(0);
        String ros=str.substring(1);
        ArrayList<String> rres=getAscii(ros);
        ArrayList<String> myres=new ArrayList<>();
        for(String s:rres)
        {
            myres.add(""+s);
            myres.add(s+ch);
            myres.add(s+(int)ch);
            
        }
        return myres;
        
    }
   
    static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int printKPC(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return 1;
        }

        char ch = str.charAt(idx);
        String code = codes[ch - '0'];
        int count = 0;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            count += printKPC(str, idx + 1, ans + c);
        }

        return count;
    }
    public static ArrayList<String> getKPC(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> recAns = getKPC(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>();

        char ch = str.charAt(idx);
        String code = codes[ch - '0'];
        int count = 0;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            for (String s : recAns) {
                myAns.add(c + s);
            }
        }

        return myAns;
    }
    public static void printSubseq(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        char ch = str.charAt(0);
        String ros = str.substring(1);
        printSubseq(ros, ans + ch);
        printSubseq(ros, ans + "");

    }
    public static ArrayList < String > getSubseq(String str) {
        if (str.length() == 0) {
            ArrayList < String > base = new ArrayList < > ();
            base.add("");
            return base;
        }
        char ch = str.charAt(0);
        String ros = str.substring(1);
        ArrayList < String > rres = getSubseq(ros);
        ArrayList < String > myres = new ArrayList < > ();
        for (String s: rres) {
            myres.add("" + s);
        }
        for (String s: rres) {
            myres.add(ch + s);
        }
        return myres;
    }
    public static void printSS_02(String str, int idx, StringBuilder ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return;
        }

        ans.append(str.charAt(idx));
        printSS_02(str, idx + 1, ans);
        ans.deleteCharAt(ans.length() - 1);

        printSS_02(str, idx + 1, ans);
    }
    public static void main(String[]args)
    {
        String str=scn.next();
        System.out.println(getCountAscii2(str,""));
        //getAscii2(str,"");
        //System.out.println(getAscii(str));
    }

}