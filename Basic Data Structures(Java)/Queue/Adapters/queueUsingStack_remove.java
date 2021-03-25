import java.util.Stack;
public class queueUsingStack_remove{
    public class queueUsingStack_pop {
        Stack<Integer> st = new Stack<>();
        Stack<Integer> temp = new Stack<>();
    
        public int size() {
            return st.size();
        }
    
        public boolean isEmpty() {
            return st.isEmpty();
        }
    
        private void transferToAnotherStack() {
            while (st.size() != 0) {
                temp.push(st.pop());
            }
        }
    
        // O(n)
        public void add(int data) {
            transferToAnotherStack();
            this.st.push(data);
            while (this.temp.size() != 0)
                this.st.push(this.temp.pop());
        }
    
        // O(1)
        public int peek() {
            if(st.size()==0){
                System.out.println("Queue underflow");
                return -1;
            }
            return this.st.peek(); //we cant use peekVal variable to maintain peek as peek is top value in stack but when we delete top value then peekVal becomes unstable. 
        }
    
        // O(1)
        public int remove() {
            if(st.size()==0){
                System.out.println("Queue underflow");
                return -1;
            }
            return this.st.pop();
        }
    
}