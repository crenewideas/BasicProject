package cn.pxl.dataStructure.demo05BinarySearchTree;

import cn.pxl.dataStructure.common.printer.LevelOrderPrinter;
import cn.pxl.dataStructure.entity.Person;

import java.sql.Array;
import java.util.ArrayList;

public class MyBinaryTreeMain {

    public static void main(String[] args) {
        doTest01();
    }

    private static void doTest01(){
        //默认比较器的 二叉搜索树
        MyBinarySearchTree<Person> myBinarySearchTree = new MyBinarySearchTree<>();
        //自定义比较器的 二叉搜索树(年龄越小的，比较时按越大处理。)
        MyBinarySearchTree<Person> myComparatorBinarySearchTree = new MyBinarySearchTree<>((e1,e2)-> e2.getAge() - e1.getAge());
        Person[] persons = new Person[11];
        persons[0] = new Person(7);
        persons[1] = new Person(4);
        persons[2] = new Person(2);
        persons[3] = new Person(5);
        persons[4] = new Person(1);
        persons[5] = new Person(9);
        persons[6] = new Person(8);
        persons[7] = new Person(3);
        persons[8] = new Person(11);
        persons[9] = new Person(10);
        persons[10] = new Person(12);


        for (Person person : persons) {
            myBinarySearchTree.add(person);
            myComparatorBinarySearchTree.add(person);
        }
//        LevelOrderPrinter levelOrderPrinter = new LevelOrderPrinter(myBinarySearchTree);
//        levelOrderPrinter.print();
//        LevelOrderPrinter levelOrderPrinter2 = new LevelOrderPrinter(myComparatorBinarySearchTree);
//        levelOrderPrinter2.print();
        LevelOrderPrinter levelOrderPrinter = new LevelOrderPrinter(myBinarySearchTree);
        levelOrderPrinter.print();
        System.out.println("");

//测试停止功能是否成功
//        myBinarySearchTree.postTraversal(myBinarySearchTree.getRootNode(),(person -> {
//            System.out.println(person.getAge());
//            return person.getAge() == 5;
//        }));

        //myBinarySearchTree.preorderTraversal(myBinarySearchTree.getRootNode(), System.out::println);
        //myBinarySearchTree.interTraversal(myBinarySearchTree.getRootNode(), System.out::println);
        //myBinarySearchTree.levelOrderTraversal(myBinarySearchTree.getRootNode(), System.out::println);

        //测试打印字符串功能是否成功
        //System.out.println(myBinarySearchTree);

        //测试层序遍历方式获取树的高度。
        System.out.println(myBinarySearchTree.height(myBinarySearchTree.getRootNode()));

        System.out.println(myBinarySearchTree.levelOrderTraversalHeight());
    }



}
