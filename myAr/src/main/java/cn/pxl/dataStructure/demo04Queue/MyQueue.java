package cn.pxl.dataStructure.demo04Queue;

import cn.pxl.dataStructure.demo02Learning.MyLinkedListLearning;

public class MyQueue<E> {

    private MyLinkedListLearning<E> linkedList = new MyLinkedListLearning<>();

    int size(){
        return linkedList.size();
    }

    boolean isEmpty(){
        return linkedList.isEmpty();
    }

    void clear(){
        linkedList.clear();
    }

    E front(){
        return linkedList.get(0);
    }

    void enQueue(E element){
        linkedList.add(size(),element);
    }

    E deQueue(){
        return linkedList.remove(0);
    }



}
