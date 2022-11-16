package cn.pxl.dataStructure.demo01Learning;

public class MyMain {

    public static void main(String[] args) {
        MyArrayListLearning<Integer> integerMyArrayListLearning = new MyArrayListLearning<>();
        integerMyArrayListLearning.add(10);
        integerMyArrayListLearning.add(20);
        integerMyArrayListLearning.add(30);
        integerMyArrayListLearning.add(40);
        System.out.println(integerMyArrayListLearning);
        integerMyArrayListLearning.remove(0);
        System.out.println(integerMyArrayListLearning);
        integerMyArrayListLearning.add(2,22);
        System.out.println(integerMyArrayListLearning);
        integerMyArrayListLearning.add(4,50);
        System.out.println(integerMyArrayListLearning);
        integerMyArrayListLearning.add(0,60);
        System.out.println(integerMyArrayListLearning);
    }

}
