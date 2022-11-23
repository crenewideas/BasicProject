package cn.pxl.dataStructure.demo06Set;

import cn.pxl.dataStructure.demo01.MyArrayList;
import cn.pxl.dataStructure.intf.MySetInterface;

public class MyListSet<E> implements MySetInterface<E> {

    private MyArrayList<E> elements;

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public boolean contains(E element) {
        return elements.contains(element);
    }

    @Override
    public void add(E element) {
        int indexOf = elements.indexOf(element);
        if(indexOf >= 0){
            return;
        }
        elements.add(element);
    }

    @Override
    public void remove(E element) {
        int indexOf = elements.indexOf(element);
        if(indexOf >= 0){
            elements.remove(indexOf);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        for (int i = 0; i < elements.size(); i++) {
            visitor.visit(elements.get(i));
        }
    }
}
