import java.util.Stack;
import java.util.LinkedList;

public class questions {
    public static boolean isBalancedBrackets(String str) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                st.push(ch);
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (st.size() == 0)
                    return false; // more closing brackets
                else if (ch == ')' && st.peek() != '(')
                    return false;
                else if (ch == '}' && st.peek() != '{')
                    return false;
                else if (ch == ']' && st.peek() != '[')
                    return false;
                else
                    st.pop();
            }
        }

        return st.size() == 0;
    }
//===============================================================================================
    // NG : Next Greater
    // NS : Next Smaller
    // OR : on Right
    // OL : on Left

//Used predefined LinkedList to implement stack because linkedList fn. name are ease to learn.
public static int[] NGOR(int[] arr){
    LinkedList<Integer> st=new LinkedList<>();
    int res[]=new int[arr.length];
    Arrays.fill(res,-1);
    for(int i=0;i<arr.length;i++){
        while(st.size()!=0 && arr[st.getFirst()] < arr[i]){ //size ka check zaruri hai kyunki remove krte krte stack mein koi value bachi hi na toh loop nhi chlna chahiye. 
            res[st.removeFirst()]=arr[i];
        }
        st.addFirst(i);
    }
    return res;
}

public static int[] NGOL(int []arr){
    LinkedList<Integer> st=new LinkedList<>();
    int res[]=new int[arr.length];
    Arrays.fill(res,-1);
    for(int i=arr.length-1;i>=0;i--){
        while(st.size()!=0 && arr[st.getFirst()] < arr[i]){
            res[st.removeFirst()]=arr[i];
        }
        st.addFirst(i);
    }
    return res;
}

public static int[] NSOR(int []arr){
    LinkedList<Integer> st=new LinkedList<>();
    int res[]=new int[arr.length];
    //Arrays.fill(res,-1);    for normal NSOR
    Arrays.fill(res,arr.length); // as to maintain the formula used in largestAreaHistogram() we fill it with arr.length.
    for(int i=0;i<arr.length;i++){
        while(st.size()!=0 && arr[st.getFirst()] > arr[i]){ 
           // res[st.removeFirst()]=arr[i];   for normal NSOR. 
            res[st.removeFirst()]=i;  //for largestAreaHistogram() we need to store indexes of smallest element rather than value.
        }
        st.addFirst(i);
    }
    return res;
}

public static int[] NSOL(int []arr){
    LinkedList<Integer> st=new LinkedList<>();
    int res[]=new int[arr.length];
    Arrays.fill(res,-1);
    for(int i=arr.length-1;i>=0;i--){
        while(st.size()!=0 && arr[st.getFirst()] > arr[i]){
            // res[st.removeFirst()]=arr[i];   for normal NSOL.
             res[st.removeFirst()]=i; //for largestAreaHistogram() we need to store indexes of smallest element rather than value.
        }
        st.addFirst(i);
    }
    return res;
}

public static int largestAreaHistogram(int []arr){
    int []nsol=NSOL(arr);
    int []nsor=NSOR(arr);
    int area=0;
    for(int i=0;i<arr.length;i++){
       int l=arr[i];
       int b=nsor[i]-nsol[i]-1; //indexes in both array will get subtracted.
       if(area < l*b)
        area=l*b;
    }
    return area;
}

public static boolean duplicateBrackets(String str) {
    Stack<Character> st = new Stack<>();

    for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) != ')') {
            st.push(str.charAt(i));
            continue;
        }
        boolean isThereExpression = false;
        while (st.peek() != '(') {
            isThereExpression = true;
            st.pop();
        }
        if (!isThereExpression)
            return true;
        st.pop();    
    }

    return false;
}

//hw:leetcode:42
}