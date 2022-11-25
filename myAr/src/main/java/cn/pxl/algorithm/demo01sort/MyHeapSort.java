package cn.pxl.algorithm.demo01sort;

import cn.pxl.algorithm.AbstractSort;

import java.util.Comparator;

public class MyHeapSort<E> extends AbstractSort<E> {

    public MyHeapSort(Comparator<E> comparator, E[] elements) {
        super(comparator, elements);
    }

    public MyHeapSort(E[] elements) {
        this(null,elements);
    }

    @Override
    protected void sort() {
        E[] elements = getElements();
        //将数组转换为堆,原地建堆
//        new
//        int heapSize = elements.length;
//        while (heapSize > 1){
//            swap(0,-- heapSize);
//        }
    }
}
