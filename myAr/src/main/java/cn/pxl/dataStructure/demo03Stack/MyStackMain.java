package cn.pxl.dataStructure.demo03Stack;

public class MyStackMain {
    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);
        myStack.push(50);
        System.out.println(myStack);
        System.out.println("top element : " + myStack.top());
        System.out.println("element count : "+myStack.elementCount());
        System.out.println("pop : " + myStack.pop());
        System.out.println("element count : "+myStack.elementCount());
    }
}
