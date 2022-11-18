package cn.pxl.dataStructure.demo05BinarySearchTree;

import cn.pxl.dataStructure.common.MyBinaryTree;
import cn.pxl.dataStructure.common.MyTree;
import cn.pxl.dataStructure.common.printer.BinaryTreeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Function;

//二叉搜索树
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBinarySearchTree2<E> extends MyBinaryTree<E> {

    private Comparator<E> comparator;

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
        int comp = 0;
        while (oneNode != null){
            comp = compareNodeElement(element, oneNode.element);
            parentNode = oneNode;
            if(comp < 0 ){
                oneNode = oneNode.leftNode;
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
        Node<E> node = node(element);
        remove(node);
    }

    //根据元素找到节点对象。
    private Node<E> node(E element){
        Node<E> rootNode = getRootNode();
        while (rootNode != null){
            int comp = compareNodeElement(rootNode.element, element);
            if(comp == 0) return rootNode;

            if(comp > 0){
                rootNode = rootNode.rightNode;
            } else {
                rootNode = rootNode.leftNode;
            }
        }
        return null;
    }

    private void remove(Node<E> node){
        if(node == null) return;
        size --;
        //删除度为2的节点
        if(node.hasTwoChild()){
            //Node<E> predecessorNode = predecessor(node);
            Node<E> postDecessorNode = postdecessor(node);
            //用后继节点的值，覆盖当前度为 2 的节点的值。
            node.element = postDecessorNode.element;
            //将node引用指向新的要删除的后继节点。要删除的后继节点的度一定为1或者0。
            node = postDecessorNode;
        }

        //到这里，node的度必定为0或者1。
        Node<E> replacementNode = node.leftNode != null ? node.leftNode : node.rightNode;
        if(replacementNode != null){
            //更改parent
            replacementNode.parentNode = node.parentNode;
            //更改parent的left或者right；
            if(node.parentNode == null){
                rootNode = replacementNode;
            }else if(node == node.parentNode.leftNode){
                node.parentNode.leftNode = replacementNode;
            }else {
                node.parentNode.rightNode = replacementNode;
            }

        //叶子节点
        }else {
            //叶子节点没有根节点
            if(node.parentNode == null){
                rootNode = null;
                return;
            }
            //叶子节点有根节点。
            if(node == node.parentNode.rightNode){
                node.parentNode.rightNode = null;
            }else {
                node.parentNode.leftNode = null;
            }
        }

    }

    @Override
    public boolean contains(E element) {
        Node<E> rootNode = getRootNode();
        while (rootNode != null){
            int comp = compareNodeElement(rootNode.element, element);
            if(comp == 0) return true;
            if(comp > 0){
                rootNode = rootNode.rightNode;
            } else {
                rootNode = rootNode.leftNode;
            }
        }
        return false;
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

}
