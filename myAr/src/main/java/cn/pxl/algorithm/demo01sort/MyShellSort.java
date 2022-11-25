package cn.pxl.algorithm.demo01sort;

import cn.pxl.algorithm.AbstractSort;
import cn.pxl.commons.Integers;
import cn.pxl.dataStructure.common.Asserts;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//希尔排序，也成为改进版的插入排序。
public class MyShellSort<E> extends AbstractSort<E> {

    public static void main(String[] args) {

        Integer[] integers = Integers.random(10, 10, 100);
        MyShellSort<Integer> myShellSort = new MyShellSort<>(integers);
        Integers.println(integers);

        myShellSort.sort();

        Integers.println(integers);
        Asserts.test(Integers.isAscOrder(integers));

    }

    public MyShellSort(Comparator<E> comparator, E[] elements) {
        super(comparator, elements);
    }

    public MyShellSort(E[] elements) {
        this(null,elements);
    }

    @Override
    protected void sort() {
        E[] elements = getElements();
        sort(elements);
        //分成多少列进行排序
    }

    //分成step列进行排序。
    private void sort(E[] elements){
        int step = elements.length;
        List<Integer> integers = shellStepSeq(step);
        for (int i = 0; i < integers.size(); i++) {
            // offSet : 步长，就是每行有多少个元素。
            Integer offSet = integers.get(i);
            // col : 列数，当前是第几列元素。
            for (int col = 0; col < offSet; col++) {
                //对相应列的元素进行插入排序。
                for (int begin = col + offSet; begin < elements.length; begin += offSet ) {
                    //对该列中的所有元素进行插入排序。
                    int currentIndex = begin;
                    while (currentIndex - offSet >= 0 && compare(currentIndex,currentIndex - offSet) < 0){
                        swap(currentIndex,currentIndex -= offSet);
                    }
                }
            }
        }
    }

    private List<Integer> shellStepSeq(int step){
        ArrayList<Integer> seq = new ArrayList<>();
        while ((step >>= 1) > 0){
            seq.add(step);
        }
        System.out.println(seq);
        return seq;
    }


}
