package cn.pxl.algorithm.demo01sort;

import cn.pxl.algorithm.AbstractSort;
import cn.pxl.commons.Integers;
import cn.pxl.dataStructure.common.Asserts;

import java.util.Comparator;

public class MyInsertSort<E> extends AbstractSort<E> {

    public static void main(String[] args) {

        Integer[] integers = Integers.random(10, 10, 100);
        MyInsertSort<Integer> myInsertSort = new MyInsertSort<>(integers);
        Integers.println(integers);
        //第一种插入排序
//        myInsertSort.sort();
        //第二种插入排序
        myInsertSort.sort2();
        Integers.println(integers);
        Asserts.test(Integers.isAscOrder(integers));
        //对person进行升序排序
//        MyBubbleSort<Person> myBubbleSort = new MyBubbleSort<>();
//        myBubbleSort.bubbleSort(persons);
//        for (Person person : persons) {
//            System.out.println(person);
//        }
    }

    public MyInsertSort(Comparator<E> comparator, E[] elements) {
        super(comparator, elements);
    }

    public MyInsertSort(E[] elements) {
        this(null,elements);
    }

    //每次交换改为挪动元素
    protected void sort2() {
        E[] elements = getElements();
        for (int begin = 1; begin < elements.length; begin++) {
            int currentIndex = begin;
            //当前元素值。
            E element = elements[currentIndex];
            while (currentIndex > 0 && compare(element,elements[currentIndex - 1]) < 0) {
                elements[currentIndex] = elements[currentIndex - 1];
                currentIndex--;
            }
            elements[currentIndex] = element;
        }
    }

    @Override
    protected void sort() {
        E[] elements = getElements();
        for (int begin = 1; begin < elements.length; begin++) {
            int currentIndex = begin;
            while (currentIndex > 0 && compare(currentIndex,currentIndex - 1) < 0){
                swap(currentIndex,--currentIndex);
            }
        }
    }
}
