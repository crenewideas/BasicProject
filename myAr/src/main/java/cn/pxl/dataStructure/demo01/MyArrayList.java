package cn.pxl.dataStructure.demo01;

import cn.pxl.dataStructure.demo01.exc.MyException;
import cn.pxl.dataStructure.demo01.exc.MyExceptionCode;

import java.util.Arrays;

public class MyArrayList<E> implements MyArrayListInterface<E>{

    private static final int DEFAULT_SIZE = 10;

    private int size = 0;
    private Object[] data;

    public MyArrayList(){
        this.data = new Object[DEFAULT_SIZE];
    }

    public MyArrayList(int size){
        this.data = new Object[Math.max(size, DEFAULT_SIZE)];
    }


    @Override
    public void add(E element) {
        addCapacityIfNecessary();
        data[size] = element;
        size++;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        addCapacityIfNecessary();
        for (int i = size; i > index; i--) {
            data[i] = data[i-1];
        }
        data[index] = element;
        size ++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        if(index == size){
            throw new MyException(MyExceptionCode.A1001);
        }
        E e = get(index);
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i+1];
        }
        data[size-1] = null;
        size--;
        return e;
    }

    @Override
    public int length() {
        return data.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E get(int index) {
        Object datum = data[index];
        return (E) datum;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        if(index == size){
            throw new MyException(MyExceptionCode.A1001);
        }
        E oldData = (E)data[index];
        data[index] = element;
        return  oldData;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < data.length; i++) {
            E datum = (E)data[i];
            if (element.equals(datum)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return size ==0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    private boolean isCapacity(){
        return size >= data.length;
    }

    private void addCapacityIfNecessary(){
        if(isCapacity()){
            Object[] newData = new Object[data.length + (data.length >> 1)];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

    private void checkIndex(int index){
        if(index < 0 || index > size){
            throw new MyException(MyExceptionCode.A1001);
        }
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "size=" + size +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
