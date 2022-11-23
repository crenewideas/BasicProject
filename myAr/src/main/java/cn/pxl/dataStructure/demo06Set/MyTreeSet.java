package cn.pxl.dataStructure.demo06Set;

import cn.pxl.dataStructure.demo05BinarySearchTree.MyAvlTree;
import cn.pxl.dataStructure.intf.MySetInterface;

public class MyTreeSet<E> implements MySetInterface<E> {

    private MyAvlTree<E> elements;

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
        elements.add(element);
    }

    @Override
    public void remove(E element) {
        elements.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        elements.interTraversal(elements.getRootNode(), visitor::visit);
    }
}
