import java.util.*;
public class Stack{

    //ngor: next greater to the Right.
    //ngol: next greater to the left.
    //nsor: next smallest to the Right.
    //nsol: next smallest to the left.

    //Either we can use Stack class or LinkedList as stack also.

    public static int[] ngor(int []arr){
        int n = arr.length;
        int []res = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<n;i++){
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
                int idx = st.pop();
                res[idx] = arr[i];
            }
            st.push(i);    
        }

        while(st.size() > 0)
            res[st.pop()] = -1;
        
        return res;
    }

    public static int[] ngol(int []arr){
        int n = arr.length;
        int []res = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i=n-1;i>=0;i--){
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
                int idx = st.pop();
                res[idx] = arr[i];
            }
            st.push(i);    
        }

        while(st.size() > 0)
            res[st.pop()] = -1;
        
        return res;
    }

    public static int[] nsor(int []arr){
        Stack<Integer> st = new Stack<>();
        int n = arr.length; 
        int []res = new int[n];
        Arrays.fill(res,-1);

        for(int i=0;i<n;i++){
            while(st.size() > 0 && arr[st.peek()] > arr[i]){
                int idx = st.pop();
                res[idx] = arr[i];
            }
            st.push(i);
        }
        return res;
    }

    public static int[] nsol(int []arr){
        Stack<Integer> st = new Stack<>();
        int n = arr.length; 
        int []res = new int[n];
        Arrays.fill(res,-1);
        
        for(int i=n-1;i>=0;i--){
            while(st.size() > 0 && arr[st.peek()] > arr[i]){
                int idx = st.pop();
                res[idx] = arr[i];
            }
            st.push(i);
        }
        return res;
    }

    //496
    public static int[] nextGreaterElementI(int[] nums, int[] query) {
        int n = nums.length;
        HashMap<Integer,Integer> hm = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<n;i++){
            while(st.size() > 0 && nums[st.peek()] < nums[i]){
                int idx = st.pop();
                hm.put(nums[idx] , nums[i]);
            }
            st.push(i);    
        }
        int res[] = new int[query.length];
        for(int i=0;i<res.length;i++){
           res[i] = hm.getOrDefault(query[i],-1);     
        }
        return res;
    }

    //503
    public int[] nextGreaterElementII(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int res[] = new int[n];
        for(int i=0;i<2*n;i++){
            int idx  = i % arr.length;
            while(st.size() > 0 && arr[st.peek()] < arr[idx]){
                res[st.pop()] = arr[idx];
            }
            if(i < arr.length)
                st.push(idx);
        }
        while(st.size()>0)
            res[st.pop()] = -1;
        return res;    
    }

    //84
    public static int[] nsolIndex(int []arr){
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int res[] = new int[n];
        Arrays.fill(res,-1);

        for(int i=n-1;i>=0;i--){
            while(st.size() > 0 && arr[st.peek()] > arr[i]){
                res[st.pop()] = i;
            }
            st.push(i);
        }
        return res;
    }

    public static int[] nsorIndex(int []arr){
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int res[] = new int[n];
        Arrays.fill(res,n);
        
        for(int i=0;i<n;i++){
            while(st.size() > 0 && arr[st.peek()] > arr[i]){
                res[st.pop()] = i;
            }
            st.push(i);
        }
        return res;
    }

    public int largestRectangleArea(int[] heights) {
        int [] NSOL = nsolIndex(heights);
        int [] NSOR = nsorIndex(heights);

        int maxArea = 0;
        for(int i=0;i<heights.length;i++){
            int l = heights[i];
            int b = NSOR[i] - NSOL[i] - 1;
            int area = (l*b);
            maxArea = Math.max(maxArea,area);
        }
        return maxArea;
    }

    //85
    public int maximalRectangle(char[][] matrix) {
        int []heights = new int[matrix[0].length];
        int maxRect = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j =0;j<matrix[0].length;j++){
                if(matrix[i][j] == '1')
                    heights[j] += 1;
                else
                    heights[j] = 0;    
            }
            maxRect = Math.max(maxRect,largestRectangleArea(heights));
        }
        return maxRect;
    }

    //921
    public int minAddToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == ')' && st.size() > 0 && st.peek() == '('){
                st.pop();
            }else{
                st.push(ch);
            }    
        }
        return st.size();
    }

    //1963
    public int minSwaps(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == ']' && st.size() > 0 && st.peek() == '['){
                st.pop();
            }else{
                st.push(ch);
            }
        }
        int pair = st.size() / 2;
        return (int)Math.ceil(pair/2.0);
    }

    //gfg
    public int countRev (String s){
        if(s.length() % 2 != 0)
            return -1;
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '}' && st.size() > 0 && st.peek() == '{'){
                st.pop();
            }else{
                st.push(ch);
            }
        }
        
        int op = 0;     
        int cl = 0;     
        while(st.size() > 0){
            char ch = st.pop();
            if(ch == '{')
                op++;
            else
                cl++;    
        }
        //return (op + cl)/2 + 1;   fails for test case like ((( 
        return (int)(Math.ceil(op/2.0) + Math.ceil(cl/2.0));
    }
    
    public static void main(String []args){

    }
}
