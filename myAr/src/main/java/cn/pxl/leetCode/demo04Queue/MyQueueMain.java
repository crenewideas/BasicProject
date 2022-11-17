package cn.pxl.leetCode.demo04Queue;

public class MyQueueMain {
    public static void main(String[] args) {
        Xlp225 xlp225 = new Xlp225();
//        System.out.println(xlp225.empty());
//        xlp225.push(1);
//        System.out.println(xlp225.empty());
//        System.out.println(xlp225.pop());
//        System.out.println(xlp225.empty());
        for (int i = 0; i < 10; i++) {
            xlp225.push(i);
        }
        while (!xlp225.empty()){
            System.out.println(xlp225.top());
            System.out.println(xlp225.pop());
        }
        System.out.println(xlp225.empty());
    }


}
