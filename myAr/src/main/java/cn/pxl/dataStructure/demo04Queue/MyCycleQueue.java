package cn.pxl.dataStructure.demo04Queue;

import java.util.Arrays;

public class MyCycleQueue<E> {
    private int size;
    private E[] elements;
    //存储队头元素的下标。
    private int front = 5;

    public MyCycleQueue() {
        elements = (E[])new Object[10];
    }

    int size(){
        return size;
    }

    boolean isEmpty(){
        return size == 0;
    }

    void clear(){
        elements = null;
    }

    E front(){
        return elements[front];
    }

    // index = (front + size) % length
    void enQueue(E element){
        addCapacityIfNecessary();
        int index = (front + size) % elements.length;
        elements[index] = element;
        size ++;
    }

    // 队尾元素的下标：index = (front + size -1) % length
    E deQueue(){
        if(size == 0){
            return null;
        }
        E oldElement = elements[front];
        elements[front] = null;
        //最后一个位置，下一个位置为 0 。
        front = (front + 1)%elements.length;
        size --;
        return oldElement;
    }

    private void addCapacityIfNecessary(){
        if(size == elements.length){
            System.out.println("扩容前初始容量为：" + size);
            int newCapacity = size + (size >> 1);
            E[] newElements = (E[])new Object[newCapacity];
            System.out.println("扩容后初始容量为：" + newCapacity);
            for (int i = 0; i < elements.length; i++) {
                newElements[i] = elements[(front + i)%elements.length];
            }
            elements = newElements;
            front = 0;
        }
    }

    @Override
    public String toString() {
        return "MyCycleQueue{" +
                "size=" + size +
                ", front=" + front +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }
}
