package cn.pxl.algorithm.demo01sort;

import cn.pxl.algorithm.AbstractSort;
import cn.pxl.commons.Integers;
import cn.pxl.dataStructure.common.Asserts;
import java.util.Comparator;

//冒泡排序
public class MySelectionSort<E> extends AbstractSort<E> {

    public MySelectionSort(E[] elements) {
        super(null,elements);
    }

    public MySelectionSort(E[] elements,Comparator<E> comparator) {
        super(comparator,elements);
    }

    public static void main(String[] args) {
        
        Integer[] integers = Integers.random(10, 10, 100);
        MySelectionSort<Integer> integerMyselectionSort = new MySelectionSort<>(integers);
        Integers.println(integers);
        //第一种冒泡排序
        integerMyselectionSort.sort();
        Asserts.test(Integers.isAscOrder(integers));

    }

    @Override
    protected void sort() {
        E[] elements = getElements();
        if(elements.length <= 1) return;
        for (int j = 0; j < elements.length; j++) {
            int maxIndex = 0;
            for (int i = 1; i < elements.length - j; i++) {
                if(compare(maxIndex,i) < 0){
                    maxIndex = i;
                }
            }
            int tailIndex = elements.length - j - 1;
            swap(maxIndex,tailIndex);
        }
    }
}
