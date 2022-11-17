package cn.pxl.leetCode.demo04Queue;

import java.util.Stack;

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
public class Xlp232 {
    private Stack<Integer> oneStack = new Stack<>();
    private Stack<Integer> twoStack = new Stack<>();

    public void push(int x) {
        oneStack.push(x);
    }

    public int pop() {
        if(twoStack.isEmpty()){
            while (!oneStack.isEmpty()){
                twoStack.push(oneStack.pop());
            }
        }
        return twoStack.pop();
    }

    public int peek() {
        if(twoStack.isEmpty()){
            while (!oneStack.isEmpty()){
                twoStack.push(oneStack.pop());
            }
        }
        return twoStack.peek();
    }

    public boolean empty() {
        return oneStack.isEmpty() && twoStack.isEmpty();
    }


}
