package cn.pxl.dataStructure.demo05BinarySearchTree;

import java.util.Comparator;

public class MyRBTree<E> extends MyBinarySearchTree2<E>{
    public static final boolean BLACK = true;
    public static final boolean RED = false;

    public MyRBTree() {
    }

    public MyRBTree(Comparator<E> comparator) {
        super(comparator);
    }

    //删除元素后，恢复整个树的平衡
    @Override
    protected void afterRemove(Node<E> node) {

    }

    //添加元素后，恢复整个树的平衡
    @Override
    protected void convertToBalanceTree(Node<E> node) {

    }

    //染色,传入染色节点及要染成的颜色。
    private RBNode<E> color(Node<E> node,boolean color){
        if(node == null) return null;
        RBNode<E> rbNode = (RBNode<E>) node;
        rbNode.color = color;
        return rbNode;
    }

    //对当前节点染色成红色
    private RBNode<E> red(Node<E> node){
        return color(node,RED);
    }

    //对当前节点染色成黑丝
    private RBNode<E> black(Node<E> node){
        return color(node,BLACK);
    }

    //返回当前节点的颜色
    private boolean colorOf(Node<E> node){
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    //当前节点是否是红色
    private boolean colorOfRed(Node<E> node){
        return node != null && ((RBNode<E>)node).color == RED;
    }


    //当前节点是否是黑
    private boolean colorOfBlack(Node<E> node){
        return node != null && ((RBNode<E>)node).color == BLACK;
    }



    private static class RBNode<E> extends Node<E>{
        boolean color;
        public RBNode(E element, Node<E> parentNode) {
            super(element, parentNode);
        }
        public RBNode(E element, Node<E> parentNode, Node<E> leftNode, Node<E> rightNode) {
            super(element, parentNode, leftNode, rightNode);
        }
    }
}
