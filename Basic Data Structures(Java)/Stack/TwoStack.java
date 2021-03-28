import java.io.*;
import java.util.*;

  public class TwoStack {
    public static class TwoStack {
        int[] arr;
        int tos1;
        int tos2;
        int capacity=0;
    
        public TwoStack(int cap) {
          arr=new int[cap];
          capacity=cap;
          tos1=-1;
          tos2=capacity;
        }
    
        int size1() {
          return (this.tos1 +1);
        }
    
        int size2() {
            return (capacity-this.tos2);
        }
    
        void push1(int val) {
          if(this.tos1 +1 == this.tos2){
              System.out.println("Stack overflow");
          }
          else{
              this.arr[++tos1]=val;
          }
        }
    
        void push2(int val) {
            if(this.tos2 -1 == this.tos1){
                System.out.println("Stack overflow");
            }
            else{
                this.arr[--tos2]=val;
            }
        }
    
        int pop1() {
          int rData=0;
          if(this.tos1 == -1){
              System.out.println("Stack underflow");
              return -1;
            }
          else{
              rData=this.arr[tos1--];
              return rData;
          }
        }
    
        int pop2() {
            int rData=0;
            if(this.tos2 == this.capacity){
                System.out.println("Stack underflow");
                return -1;
              }
            else{
                rData=this.arr[tos2++];
                return rData;
            }
        }
    
        int top1() {
            if(this.tos1==-1){
                System.out.println("Stack underflow");
                return -1;
            }
         return this.arr[tos1];
        }
    
        int top2() {
            if(this.tos2==capacity){
                System.out.println("Stack underflow");
                return -1;
            }
          return this.arr[tos2];
        }
  }