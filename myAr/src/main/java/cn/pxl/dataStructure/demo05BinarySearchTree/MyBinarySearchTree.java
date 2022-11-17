package cn.pxl.dataStructure.demo05BinarySearchTree;

import cn.pxl.dataStructure.common.MyTree;
import cn.pxl.dataStructure.common.printer.BinaryTreeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.color.ProfileDataException;
import java.util.Comparator;

//二叉搜索树
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyBinarySearchTree<E> implements MyTree<E>, BinaryTreeInfo {

    private int size;

    private Node<E> rootNode;

    private Comparator<E> comparator;

    public MyBinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        //根节点：
        if(rootNode == null){
            rootNode = new Node<>(element,null);
            size ++;
            return;
        }
        //非根节点：
        Node<E> oneNode = rootNode;
        Node<E> parentNode = rootNode;
        E rootElement = oneNode.element;
        int comp = compareNodeElement(element, rootElement);
        while (oneNode != null){
            parentNode = oneNode;
            if(comp < 0 ){
                oneNode = oneNode .leftNode;
            }else if(comp > 0){
                oneNode = oneNode.rightNode;
            }else {
                oneNode = null;
            }
        }
        Node<E> newNode = new Node<>(element, parentNode);
        if(comp > 0){
            parentNode.rightNode = newNode;
        } else if(comp < 0 ){
            parentNode.leftNode = newNode;
        }else {
            //如果相同，进行覆盖，这是因为自定义对象可能存在比较相同，但其他属性不同的情况。
            parentNode.element = element;
        }
        size ++;
    }

    @Override
    public void remove(E element) {

    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    //判断是否是叶子节点：
    private boolean isLeafNode(Node<E> node){
        return node != null && node.leftNode == null && node.rightNode == null;
    }

    //比较节点中值和传入的值的大小
    // < 0  : element1 < element2
    // = 0  : element1 = element2
    // > 0  : element1 > element2
    private int compareNodeElement(E element1,E element2){
        if(comparator != null){
            return comparator.compare(element1,element2);
        }
        if(element1 instanceof Comparable){
            Comparable<E> comparableElement1 = (Comparable<E>)element1;
            return comparableElement1.compareTo(element2);
        }
        throw new RuntimeException("添加类型必须实现Comparable接口，或定义Comparator比较器");
    };

    private void nodeNotNullCheck(Node<E> node){
        if(node == null){
            throw new IllegalArgumentException("节点不能为空！");
        }
    }

    private void elementNotNullCheck(E element){
        if(element == null){
            throw new IllegalArgumentException("节点不能为空！");
        }
    }

    @Override
    public Object root() {
        return rootNode;
    }

    @Override
    public Object left(Object node) {
        return ((Node) node).leftNode;
    }

    @Override
    public Object right(Object node) {
        return ((Node) node).rightNode;
    }

    @Override
    public Object string(Object node) {
        return ((Node) node).element.toString();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private static class Node<E>{
        E element;
        Node<E> parentNode;
        Node<E> leftNode;
        Node<E> rightNode;
        public Node(E element, Node<E> parentNode) {
            this.element = element;
            this.parentNode = parentNode;
        }
    }

}
