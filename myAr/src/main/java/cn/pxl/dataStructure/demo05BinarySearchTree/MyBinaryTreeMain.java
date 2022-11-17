package cn.pxl.dataStructure.demo05BinarySearchTree;

import cn.pxl.dataStructure.common.printer.LevelOrderPrinter;
import cn.pxl.dataStructure.entity.Person;

public class MyBinaryTreeMain {

    public static void main(String[] args) {
        doTest01();
    }

    private static void doTest01(){
        //默认比较器的 二叉搜索树
        MyBinarySearchTree<Person> myBinarySearchTree = new MyBinarySearchTree<>();
        //自定义比较器的 二叉搜索树(年龄越小的，比较时按越大处理。)
        MyBinarySearchTree<Person> myComparatorBinarySearchTree = new MyBinarySearchTree<>((e1,e2)-> e2.getAge() - e1.getAge());
        Person[] persons = new Person[10];
        for (int i = 0; i < 10; i++) {
            persons[i] = new Person(i +1);
        }
        for (Person person : persons) {
            myBinarySearchTree.add(person);
            myComparatorBinarySearchTree.add(person);
        }
//        LevelOrderPrinter levelOrderPrinter = new LevelOrderPrinter(myBinarySearchTree);
//        levelOrderPrinter.print();
        LevelOrderPrinter levelOrderPrinter2 = new LevelOrderPrinter(myComparatorBinarySearchTree);
        levelOrderPrinter2.print();

    }



}
