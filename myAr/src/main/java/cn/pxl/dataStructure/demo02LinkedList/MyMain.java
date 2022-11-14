package cn.pxl.dataStructure.demo02LinkedList;

public class MyMain {
    public static void main(String[] args) {
        MyLinkedList<Integer> integerMyLinkedList = new MyLinkedList<>();
        for (int i = 0; i < 20; i++) {
            integerMyLinkedList.add(i + 1);
        }
        integerMyLinkedList.add(0,1000);
        System.out.println(integerMyLinkedList.get(0));
        System.out.println(integerMyLinkedList.get(4));
        integerMyLinkedList.set(0,200);
        System.out.println(integerMyLinkedList.get(0));
        System.out.println(integerMyLinkedList.get(1));
        System.out.println(integerMyLinkedList.get(2));

    }
}
