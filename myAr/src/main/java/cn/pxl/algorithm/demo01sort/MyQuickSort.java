package cn.pxl.algorithm.demo01sort;

import cn.pxl.algorithm.AbstractSort;
import cn.pxl.commons.Integers;
import cn.pxl.dataStructure.common.Asserts;

import java.util.Comparator;

//快速排序
public class MyQuickSort<E> extends AbstractSort<E> {

    public static void main(String[] args) {

        Integer[] integers = Integers.random(10, 10, 100);
        MyQuickSort<Integer> myQuickSort = new MyQuickSort<>(integers);
        Integers.println(integers);

        myQuickSort.sort();

        Integers.println(integers);
        Asserts.test(Integers.isAscOrder(integers));

    }

    public MyQuickSort(Comparator<E> comparator, E[] elements) {
        super(comparator, elements);
    }

    public MyQuickSort(E[] elements) {
        this(null,elements);
    }

    @Override
    protected void sort() {
        E[] elements = getElements();
        //divideToPivot(0,elements.length,elements);
        divideToPivot2(0,elements.length,elements);
    }

    //分割出轴点元素。
    //头尾元素的索引：前闭后开。
    //这种方式：传统方式，头尾交替执行，采用flag方式判断。还有一种方式：可以采用双while循环 + break方式实现。
    private void divideToPivot(int beginIndex,int endIndex,E[] elements){
        //递归的停止条件是：分割后的左右子数组的元素个数不大于1。（1个时候无需分割）
        if (endIndex - beginIndex <= 1) return;
        int currentBeginIndex = beginIndex;
        int currentEndIndex = endIndex;
        //每次都将头元素作为轴点元素。
        E pivotElement = elements[beginIndex];
        //true : 从头开始向尾。
        boolean isTopBegin = false;
        //当头元素等于尾元素时，说明分割选出轴点元素已经完毕，这时只需用轴点元素覆盖掉 index 位置即可。
        //尾元素之所以 -1 是因为 endIndex 是开集，真实索引位置是 endIndex - 1。
        while (beginIndex < endIndex - 1){
            if(isTopBegin){
                //头元素与尾元素进行比较
                if(compare(elements[beginIndex],pivotElement) < 0){
                    //如果头部元素小于轴点元素，那么无需覆盖，因为本身就是小于轴点的放左边，这时只需头部 index ++ 继续从头部比较。
                    beginIndex ++;
                } else {
                    //如果头部元素大于等于轴点元素，那么直接覆盖掉尾部元素。尾部元素指针向前移动。
                    elements[--endIndex] = elements[beginIndex];
                    //接下来开始从尾部比较。
                    isTopBegin = false;
                }
            }else {
                //尾元素与头元素进行比较
                if(compare(elements[endIndex - 1],pivotElement) > 0){
                    //如果尾部元素大于轴点元素，那么无需覆盖，因为本身就是大于轴点的放右边，这时只需尾部index -- 继续从尾部比较。
                    endIndex --;
                } else {
                    //如果尾部元素小于等于轴点元素，那么直接覆盖掉头部元素。头部元素指针向后移动。
                    elements[beginIndex++] = elements[endIndex - 1];
                    //接下来开始从头部比较。
                    isTopBegin = true;
                }
            }
        }
        elements[beginIndex] = pivotElement;
        //外层快排好了，开始内层快排
        // beginIndex + 1 ：含头不含尾。右边分割后，开始头下标是  beginIndex + 1
        divideToPivot(currentBeginIndex,beginIndex,elements);
        divideToPivot(beginIndex + 1,currentEndIndex,elements);
    }

    //采用双while循环 + break方式实现。
    private void divideToPivot2(int beginIndex,int endIndex,E[] elements){
        //递归的停止条件是：分割后的左右子数组的元素个数不大于1。（1个时候无需分割）
        if (endIndex - beginIndex <= 1) return;
        int currentBeginIndex = beginIndex;
        int currentEndIndex = endIndex;
        //每次都将头元素作为轴点元素。
        E pivotElement = elements[beginIndex];
        //true : 从头开始向尾。
        boolean isTopBegin = false;
        //当头元素等于尾元素时，说明分割选出轴点元素已经完毕，这时只需用轴点元素覆盖掉 index 位置即可。
        //尾元素之所以 -1 是因为 endIndex 是开集，真实索引位置是 endIndex - 1。
        while (beginIndex < endIndex - 1){
            while (beginIndex < endIndex - 1){
                //尾元素与头元素进行比较
                if(compare(elements[endIndex - 1],pivotElement) > 0){
                    //如果尾部元素大于轴点元素，那么无需覆盖，因为本身就是大于轴点的放右边，这时只需尾部 index -- 继续从尾部比较。
                    endIndex --;
                } else {
                    //如果尾部元素小于等于轴点元素，那么直接覆盖掉头部元素。头部元素指针向后移动。
                    elements[beginIndex++] = elements[endIndex - 1];
                    //接下来开始从头部比较。
                    break;
                }
            }
            while (beginIndex < endIndex - 1){
                //头元素与尾元素进行比较
                if (compare(elements[beginIndex],pivotElement) < 0) {
                    //如果头部元素小于轴点元素，那么无需覆盖，因为本身就是小于轴点的放左边，这时只需头部 index ++ 继续从头部比较。
                    beginIndex ++;
                } else {
                    //如果头部元素大于等于轴点元素，那么直接覆盖掉尾部元素。尾部元素指针向前移动。
                    elements[--endIndex] = elements[beginIndex];
                    //接下来开始从尾部比较。
                    break;
                }
            }
        }
        elements[beginIndex] = pivotElement;
        //外层快排好了，开始内层快排
        // beginIndex + 1 ：含头不含尾。右边分割后，开始头下标是  beginIndex + 1
        divideToPivot(currentBeginIndex,beginIndex,elements);
        divideToPivot(beginIndex + 1,currentEndIndex,elements);
    }
}
