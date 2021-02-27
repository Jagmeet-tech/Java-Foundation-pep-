public class l005_String
{
    public static boolean isPalindrome(String str) {
        int si = 0, ei = str.length() - 1;
        while (si < ei) {
            if (str.charAt(si++) != str.charAt(ei--))
                return false;
        }
        return true;
    }
    public static void getAllSubStrings(String str)
    {
        for(int i=0;i<str.length();i++)
        {
            for(len=1;i+len<=str.length();len++)
            {
                System.out.println(str.substring(i,i+len));
            }
        }

       /**  for(int i=0;i<str.length();i++)  //my method
        {
            for(int j=i;j<str.length();j++)
            {
                for(int k=i;k<=j;k++)
                {
                    System.out.print(str.charAt(k));
                }
                System.out.println();
            }
        }*/
    }
    public static void getAllPalindromicSubStrings(String str) {

        for (int i = 0; i < str.length(); i++) {
            for (int len = 1; i + len <= str.length(); len++) {
                String s = str.substring(i, i + len);
                if (isPalindrome(s))
                    System.out.println(s);
            }
        }
    }
  public static String stringCompression1(String str)
  {
      StringBuilder sb=new StringBuilder();
      sb.append(str.charAt(0));
      for(int i=1;i<str.length();i++)
      {  
        if(str.charAt(i)==str.charAt(i-1))
            i++;
        else
            sb.append(str.charAt(i));
      }
      return sb.toString();
  }
  public static String stringCompression2(String str)
  {
      StringBuilder sb=new StringBuilder();
      for(int i=1;i<str.length();i++)
      {  
        sb.append(str.charAt(0));
        int count=1;
        if(str.charAt(i)!=str.charAt(i-1))
        {
            sb.append(str.charAt(i));
        }
        else{
            count++;
        }
      }
      return sb.toString();
  }

    public static String toggleCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z')
                sb.append((char)(ch - 'a' + 'A'));
            else
                sb.append((char)(ch - 'A' + 'a'));
        }
        return sb.toString();
    }
    public static String differenceOfTwoConsecutive(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            char char0 = str.charAt(i - 1);
            char char1 = str.charAt(i);
            sb.append(char1 - char0);
            sb.append(char1);
        }
        return sb.toString();
    }
    public static ArrayList<String> subSeq(String str)
    {
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        for(int i=0;i<str.size();i++)
        {
            char ch=str.charAt(i);
            int size=ans.size();
            for(int j=0;j<size;j++)
            {
                ans.add(ans.get(j)+ch);
            }
        }
        return ans;
    }


    public static void main(String[]args)
    {
        String str="abcd";
        getAllSubStrings(str);

    }
} 