package cn.pxl.algorithm;

import lombok.Data;

import java.util.Comparator;
@Data
public abstract class AbstractSort <E>{
    private Comparator<E> comparator = null;
    private E[] elements;

    public AbstractSort(Comparator<E> comparator, E[] elements) {
        this.comparator = comparator;
        this.elements = elements;
    }

    public AbstractSort(E[] elements) {
        this.elements = elements;
    }

    protected void swap(int index1, int index2){
        E temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    };

    protected int compare(int index1,int index2){
        E element1 = elements[index1];
        E element2 = elements[index2];
        if(comparator != null){
            return comparator.compare(element1,element2);
        }
        if(element1 instanceof Comparable){
            return ((Comparable<E>) element1).compareTo(element2);
        }
        throw new IllegalArgumentException("必须实现Comparator或Comparable接口");
    }

    protected int compare(E element1,E element2){
        if(comparator != null){
            return comparator.compare(element1,element2);
        }
        if(element1 instanceof Comparable){
            return ((Comparable<E>) element1).compareTo(element2);
        }
        throw new IllegalArgumentException("必须实现Comparator或Comparable接口");
    }

    protected abstract void sort();
}
