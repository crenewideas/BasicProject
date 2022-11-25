package cn.pxl.algorithm.demo01sort;

import cn.pxl.algorithm.AbstractSort;
import cn.pxl.commons.Integers;
import cn.pxl.dataStructure.common.Asserts;

import java.util.Comparator;

//归并
public class MyMergeSort<E> extends AbstractSort<E> {

    public static void main(String[] args) {

        Integer[] integers = Integers.random(10, 10, 100);
        MyMergeSort<Integer> myMergeSort = new MyMergeSort<>(integers);
        Integers.println(integers);
        //第一种插入排序
        myMergeSort.sort();

        Integers.println(integers);
        Asserts.test(Integers.isAscOrder(integers));
        //对person进行升序排序
//        MyBubbleSort<Person> myBubbleSort = new MyBubbleSort<>();
//        myBubbleSort.bubbleSort(persons);
//        for (Person person : persons) {
//            System.out.println(person);
//        }
    }

    public MyMergeSort(Comparator<E> comparator, E[] elements) {
        super(comparator, elements);
    }

    public MyMergeSort(E[] elements) {
        this(null,elements);
    }

    //对区间：[beginIndex,endIndex);范围内的元素进行分割并排序。
    //TODO 有BUG！
//    private E[] sort(int beginIndex,int endIndex) {
//        return null;
//        E[] elements = getElements();
//        if(beginIndex >= endIndex) return null;
//        int mid = (beginIndex + endIndex) >> 1;
//        //归并子集1
//        E[] preSort = sort(beginIndex, mid);
//        //归并子集2
//        E[] afterSort = sort(mid + 1, endIndex);
//        //这里处理的是最底层的分组(如0，1；2，3；4，5等)，因为他们再分时候返回的preSort，afterSort都是null;
//        if(preSort != null ){
//            if(compare(beginIndex,mid) > 0){
//                swap(beginIndex,mid);
//            }
//        }else if(afterSort != null){
//            if(compare(mid + 1,endIndex) > 0){
//                swap(mid + 1,endIndex);
//            }
//        }
//        else
//        {
//            //这里是 preSort == null || afterSort == null 都不为空的情况，则对 preSort 和 afterSort 的子集进行合并。
//            //这里要排序的范围是 beginIndex 到 endIndex;
//            int firstPoint = beginIndex;
//            int secondPoint = mid + 1;
//            E[] newElements = (E[])new Object[endIndex - beginIndex];
//            int elementIndex = 0;
//            while (firstPoint < mid || secondPoint < endIndex){
//                if (compare(firstPoint,secondPoint) <= 0) {
//                    newElements[elementIndex] = elements[firstPoint];
//                    firstPoint ++;
//                } else {
//                     newElements[elementIndex] = elements[secondPoint];
//                     secondPoint ++;
//                }
//                elementIndex ++;
//            }
//            for (int i = 0; i < newElements.length; i++) {
//                elements[beginIndex + i] = newElements[i];
//            }
//        }
//        //合并
//        return  elements;
//    }


    private void sort(int beginIndex,int endIndex,E[] tempElements){
        if(endIndex - beginIndex < 2) return;
        int mid = (beginIndex + endIndex) >> 1;
        sort(beginIndex,mid,tempElements);
        sort(mid,endIndex,tempElements);
        merge(beginIndex,endIndex,mid,tempElements,getElements());
    }

    private void merge(int beginIndex, int endIndex, int mid, E[] tempElements,E[] elements) {
        int tempLeftIndex = 0;
        int leftIndex = beginIndex;
        int rightIndex = mid;
        int tempCount = mid - beginIndex;
        // 备份左边数据
        for (int i = 0; i < tempCount; i++) {
            tempElements[i] = elements[beginIndex + i];
        }

        //拷贝数组中元素尚未完全移动完。
        while (tempLeftIndex < tempCount){

            if(rightIndex < endIndex && compare(elements[rightIndex],tempElements[tempLeftIndex]) < 0 ){
                //临时数组中的 tempLeftIndex 处的元素较大，那么移动较小的元素（右边数组中元素）赋值给左下标处
                //临时数组还未遍历完，但原始数组已经到达了末尾，这时，需要把临时数组中还没有移动的元素，都移动到原始数组的末尾。
                elements[leftIndex++] = elements[rightIndex++];
            } else {
                // 临时数组中的 tempLeftIndex 处的元素较小，那么较小的元素赋值给左下标处
                elements[leftIndex++] = tempElements[tempLeftIndex++];
            }
        }
    }

    @Override
    protected void sort() {
        E[] elements = getElements();
        int beginIndex = 0;
        int endIndex = elements.length;
        E[] newElements = (E[]) new Object[elements.length >> 1];
        sort(beginIndex,endIndex,newElements);
    }
}
