package cn.pxl.dataStructure.demo04Queue;

public class MyQueueMain {
    public static void main(String[] args) {
        doMyCycleQueue();
    }

    private static void doMyQueue(){
        MyQueue<Integer> integerMyQueue = new MyQueue<>();
        integerMyQueue.enQueue(10);
        integerMyQueue.enQueue(20);
        integerMyQueue.enQueue(30);
        integerMyQueue.enQueue(40);
        //队列先进先出。
        while (!integerMyQueue.isEmpty()){
            System.out.println(integerMyQueue.deQueue());
        }

    }

    private static void doMyCycleQueue(){
        MyCycleQueue<Integer> integerMyCycleQueue = new MyCycleQueue<>();
        for (int i = 1; i <= 30; i++) {
            integerMyCycleQueue.enQueue(i);
        }
        System.out.println(integerMyCycleQueue);
        while (!integerMyCycleQueue.isEmpty()){
            System.out.println(integerMyCycleQueue.deQueue());
        }
        System.out.println(integerMyCycleQueue);
    }
}
