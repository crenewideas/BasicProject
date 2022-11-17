package cn.pxl.dataStructure.demo02LinkedList;

import cn.pxl.dataStructure.demo01.exc.MyException;
import cn.pxl.dataStructure.demo01.exc.MyExceptionCode;
import cn.pxl.dataStructure.intf.MyArrayListInterface;

public class MyLinkedList<E> implements MyArrayListInterface<E> {

    private Node headNode;
    private int size = 0;

    public MyLinkedList() {
        headNode = null;
    }

    @Override
    public void add(E element) {
        Node node = new Node();
        if(headNode == null){
            node.data = element;
            node.beforeNode = null;
            node.afterNode = null;
            headNode = node;
        } else {
            Node oneAfterAdvanceNode = headNode;
            Node oneAfterNode = headNode.afterNode;
            while (oneAfterNode != null){
                oneAfterAdvanceNode = oneAfterNode;
                oneAfterNode = oneAfterNode.afterNode;
            }
            node.data = element;
            node.beforeNode = oneAfterAdvanceNode;
            oneAfterAdvanceNode.afterNode = node;
            node.afterNode = null;
        }
        size ++;
    }

    @Override
    public void add(int index, E element) {

        Node node = new Node();
        node.data = element;
        checkIndex(index);
        if(size <= 0){
            headNode = node;
            size ++;
            return;
        }
        //向链表头部插入元素
        if(index == 0){
            headNode.beforeNode = node;
            node.afterNode = headNode;
            node.beforeNode = null;
            headNode = node;
            size ++;
            return;

        //向链表尾部插入元素。
        }else if(index == size){
            Node lastNode = getLastNode();
            //为空说明头结点为空，index = 0 ，返回当前创建的节点。
            if(lastNode == null){
                headNode = node;
                return;
            }
            lastNode.afterNode = node;
            node.beforeNode = lastNode;
            node.afterNode = null;
            size ++;
            return;
        }
        //这里说明是向链表中插入中间元素。
        Node nextNode = headNode;
        for (int i = 0; i < index -1; i++) {
            nextNode = nextNode.afterNode;
        }
        Node nextNextNode = nextNode.afterNode;
        nextNode.afterNode = node;
        node.beforeNode = nextNode;
        node.afterNode = nextNextNode;
        nextNextNode.beforeNode = node;
        size ++;
    }

    @Override
    public E remove(int index) {
        if(index == size){

        }
        return null;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        if(index == size){
            return null;
        }
        Node indexNode = headNode;
        for (int i = 0; i < index; i++) {
            indexNode = indexNode.afterNode;
        }
        return indexNode.data;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        if(index == size){
            return null;
        }
        Node beforeNode = headNode;
        for (int i = 0; i < index ; i++) {
            beforeNode = beforeNode.afterNode;
        }
        E oldData = beforeNode.data;
        beforeNode.data = element;
        return oldData;
    }

    @Override
    public int indexOf(E element) {
        return -1;
    }

    @Override
    public void clear() {
        headNode = null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        if (isEmpty()) {
            return false;
        }
        Node oneNode = headNode;
        for (int i = 0; i < size; i++) {
            if (element.equals(oneNode.data)) {
                return true;
            }
            oneNode = oneNode.afterNode;
        }
        return false;
    }


    private void checkIndex(int index){
        if(index < 0 || index > size){
            throw new MyException(MyExceptionCode.A1001);
        }
    }

    //通过头节点找尾节点。
    private Node getLastNode(){
        switch (size){
            case 0:
                return null;
            case 1:
                return headNode;
            default:
                Node lastAdvanceNode = null;
                Node lastNode = headNode;
                while (lastNode != null){
                    lastAdvanceNode = lastNode;
                    lastNode = lastNode.afterNode;
                }
                return lastAdvanceNode;
        }
    }

    private class Node{
        private Node beforeNode;
        private E data;
        private Node afterNode;

        public Node getBeforeNode() {
            return beforeNode;
        }

        public void setBeforeNode(Node beforeNode) {
            this.beforeNode = beforeNode;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getAfterNode() {
            return afterNode;
        }

        public void setAfterNode(Node afterNode) {
            this.afterNode = afterNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "beforeNode=" + beforeNode +
                    ", data=" + data +
                    ", afterNode=" + afterNode +
                    '}';
        }
    }
}
