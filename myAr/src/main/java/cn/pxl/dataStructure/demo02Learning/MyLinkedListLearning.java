package cn.pxl.dataStructure.demo02Learning;

import cn.pxl.dataStructure.common.AbstractList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class MyLinkedListLearning<E> extends AbstractList<E> {

    //当前链表的头结点指针。
    private Node<E> headNode;

    @Override
    public void clear() {
        headNode = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        Node<E> node = node(index);
        return node.element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        Node<E> newNode ;
        //头部添加元素，进行特殊处理。
        if(index == 0){
            newNode = new Node<>(null,element,headNode);
            headNode = newNode;
            size ++;
            return;
        }
        //尾部添加一个元素，进行特殊处理
        if(index == size){
            //拿到当前链表中的最后一个元素进行添加。
            Node<E> finalNode = node(index - 1);
            newNode = new Node<>(finalNode,element,null);
            finalNode.afterNode = newNode;
            size ++;
            return;
        }
        //非头非尾部元素的添加
        //获取(0 < index < size)处的节点信息。
        Node<E> currentNode = node(index);
        Node<E> privateNode = currentNode.beforeNode;
        newNode = new Node<>(privateNode,element,currentNode);
        privateNode.afterNode = newNode;
        privateNode.beforeNode = currentNode;
        size ++;
    }

    @Override
    public E remove(int index) {
        Node<E> currentNode = node(index);
        E oldElement;
        //头元素删除，并且size > 0(node方法中已经校验过了)
        if(index == 0){
            //唯一一个元素，即删除的是唯一的头结点。
            if(size == 1){
                oldElement = headNode.element;
                headNode = null;
                size --;
                return oldElement;
            //大于一个元素。
            } else {
                oldElement = headNode.element;
                Node<E> nextNode = headNode.afterNode;
                headNode = nextNode;
                headNode.beforeNode = null;
                size --;
                return oldElement;
            }
        //尾元素删除
        }else if(index == size -1){
            oldElement = currentNode.element;
            Node<E> previousNode = currentNode.beforeNode;
            previousNode.afterNode = null;
            currentNode.beforeNode = null;
            size --;
            return oldElement;
        }
        //中间元素删除
        oldElement = currentNode.element;
        Node<E> previousNode = currentNode.beforeNode;
        Node<E> nextNode = currentNode.afterNode;
        previousNode.afterNode = nextNode;
        nextNode.beforeNode = previousNode;
        currentNode.beforeNode = null;
        currentNode.afterNode = null;
        size --;
        return null;
    }

    @Override
    public int indexOf(E element) {
        Node<E> oneNode = headNode;
        if(element == null){
            for (int i = 0; i < size; i++) {
                if(oneNode.element == null){
                    return i;
                }
                oneNode = oneNode.afterNode;
            }
            return ELEMENT_NOT_FOUND;
        }
        for (int i = 0; i < size; i++) {
            if (element.equals(oneNode.element)) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    private Node<E> node(int index){
        rangeCheck(index);
        Node<E> oneNode = headNode;
        //head开始，取index处的节点对象。
        for (int i = 0; i < index; i++) {
            oneNode = oneNode.afterNode;
        }
        return oneNode;
    }


    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Node<E>{
        Node<E> beforeNode;
        E element;
        Node<E> afterNode;
    }

}
