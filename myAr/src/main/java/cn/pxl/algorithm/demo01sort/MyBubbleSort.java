package cn.pxl.algorithm.demo01sort;

import cn.pxl.algorithm.AbstractSort;
import cn.pxl.commons.Integers;
import cn.pxl.dataStructure.common.Asserts;
import java.util.Comparator;

//冒泡排序
public class MyBubbleSort<E> extends AbstractSort<E> {

    public MyBubbleSort(E[] elements) {
        super(null,elements);
    }

    public MyBubbleSort(E[] elements,Comparator<E> comparator) {
        super(comparator,elements);
    }

    public static void main(String[] args) {

        Integer[] integers = Integers.random(10, 10, 100);
        MyBubbleSort<Integer> integerMyBubbleSort = new MyBubbleSort<>(integers);
        Integers.println(integers);
        //第一种冒泡排序
        integerMyBubbleSort.sort();
        Integers.println(integers);
        Asserts.test(Integers.isAscOrder(integers));
        //对person进行升序排序
//        MyBubbleSort<Person> myBubbleSort = new MyBubbleSort<>();
//        myBubbleSort.bubbleSort(persons);
//        for (Person person : persons) {
//            System.out.println(person);
//        }
    }
    @Override
    protected void sort() {
        E[] elements = getElements();
        if(elements.length <= 1) return;
        boolean stopFlag;
        for (int j = 0; j < elements.length; j++) {
            stopFlag = true;
            for (int i = 1; i < elements.length - j; i++) {
                if(compare(i-1,i) > 0){
                    swap(i,i-1);
                    stopFlag = false;
                }
            }
            if (stopFlag) return;
        }
    }

}
