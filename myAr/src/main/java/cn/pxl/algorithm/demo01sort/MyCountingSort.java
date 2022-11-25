package cn.pxl.algorithm.demo01sort;

import cn.pxl.algorithm.AbstractSort;
import cn.pxl.commons.Integers;
import cn.pxl.dataStructure.common.Asserts;

import java.util.*;

//计数排序，适合对一定范围内的整数进行排序（必须有范围,并且只能比较整数 (E必须是Integer类型)）。
//这种排序不是比较排序（无需比较）。
public class MyCountingSort<E> extends AbstractSort<E> {

    public static void main(String[] args) {

        Integer[] integers = Integers.random(10, 10, 100);
        MyCountingSort<Integer> myCountingSort = new MyCountingSort<>(integers);
        Integers.println(integers);

        myCountingSort.sort();

        Integers.println(integers);
        Asserts.test(Integers.isAscOrder(integers));

    }

    public MyCountingSort(Comparator<E> comparator, E[] elements) {
        super(comparator, elements);
    }

    public MyCountingSort(E[] elements) {
        this(null,elements);
    }

    @Override
    protected void sort() {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 10; i < 100; i++) {
            map.put(i,0);
        }
        sort(map);
    }

    //分成step列进行排序。
    private void sort(Map<Integer,Integer> map){
        Integer[] elements;
        try {
            elements = (Integer[])getElements();
        }catch (Exception e){
            System.out.println("计数排序只能针对整数类型！");
            throw e;
        }

        for (Integer element : elements) {
            map.put(element,map.get(element)+1);
        }
        int countIndex = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer count = entry.getValue();
            if (count == 0) continue;
            Integer key = entry.getKey();
            for (Integer i = 0; i < count; i++) {
                elements[countIndex] = key;
                countIndex ++;
            }
        }
    }

}
