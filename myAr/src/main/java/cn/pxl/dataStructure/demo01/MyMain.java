package cn.pxl.dataStructure.demo01;

public class MyMain {
    public static void main(String[] args) {
        MyArrayList<Integer> integerMyArrayList = new MyArrayList<>();
        integerMyArrayList.add(0);
        integerMyArrayList.add(1);
        integerMyArrayList.add(2);
        integerMyArrayList.add(3);
        integerMyArrayList.add(4);
        integerMyArrayList.add(5);
        integerMyArrayList.add(6);
        integerMyArrayList.add(7);
        integerMyArrayList.add(8);
        integerMyArrayList.add(9);
        integerMyArrayList.add(0,2);
        System.out.println(integerMyArrayList);
        integerMyArrayList.remove(0);
        System.out.println(integerMyArrayList);
        integerMyArrayList.clear();
        System.out.println(integerMyArrayList);
    }
}
