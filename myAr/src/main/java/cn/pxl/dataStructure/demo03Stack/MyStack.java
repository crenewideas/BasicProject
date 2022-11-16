package cn.pxl.dataStructure.demo03Stack;

import cn.pxl.dataStructure.demo01Learning.MyArrayListLearning;
import cn.pxl.dataStructure.intf.MyStackInterface;

public class MyStack<E> implements MyStackInterface<E> {

    private MyArrayListLearning<E> stackData;

    public MyStack() {
        stackData = new MyArrayListLearning<>();
    }

    @Override
    public E pop() {
        if (elementCount() == 0) {
            return null;
        }
        return stackData.remove(elementCount() -1);
    }

    @Override
    public void push(E element) {
        stackData.add(element);
    }

    @Override
    public E top() {
        if(elementCount() <= 0){
            return null;
        }
        return stackData.get(elementCount() -1);
    }

    @Override
    public void clear() {
        stackData.clear();
    }

    @Override
    public int elementCount() {
        return stackData.size();
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "stackData=" + stackData.toString() +
                '}';
    }
}
