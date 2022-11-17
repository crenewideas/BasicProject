package cn.pxl.dataStructure.demo05BinarySearchTree;

import cn.pxl.dataStructure.common.MyTree;
import cn.pxl.dataStructure.common.printer.BinaryTreeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.color.ProfileDataException;
import java.util.ArrayList;
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

    //前序遍历树
    public void preorderTraversal(Node<E> rootNode, Consumer<E> consumer){
        if(rootNode == null) return;
        //回调函数，传什么，就对当前节点执行什么操作。
        consumer.accept(rootNode.element);
        //前序遍历左子树
        preorderTraversal(rootNode.leftNode,consumer);
        //前序遍历右子树
        preorderTraversal(rootNode.rightNode,consumer);
    }

    //中序遍历树
    public void interTraversal(Node<E> rootNode,Consumer<E> consumer){
        if(rootNode == null) return ;
        //1.遍历左子树。
        interTraversal(rootNode.leftNode,consumer);
        //2.回调函数执行。
        consumer.accept(rootNode.element);
        //3.遍历右子树。
        interTraversal(rootNode.rightNode,consumer);
    }

    //后序遍历树，用 Function 函数接口，返回值代表是否需要继续遍历，来实现特定条件下停止继续遍历的功能。
    //这种方式，可以实现要停止之后的递归调用都不会执行，效率比较高。
    public boolean postTraversal(Node<E> rootNode, Function<E,Boolean> function){
        //当叶子节点遍历完成，要遍历其子节点，这时，子节点 == null ,返回 false ，表示继续遍历其他节点。
        if(rootNode == null) return false;
        //1.遍历左子树。
        boolean stopIfNecessary;
        stopIfNecessary = postTraversal(rootNode.leftNode, function);
        if(stopIfNecessary) return true;
        //2.遍历右子树。
        stopIfNecessary = postTraversal(rootNode.rightNode,function);
        if(stopIfNecessary) return true;
        //3.回调函数执行。
        return function.apply(rootNode.element);
    }

    //层序遍历树,利用队列，出栈时，就将左右子树的节点添加到队列中。
    //层序遍历的用处：1.计算二叉树的高度。见 levelOrderTraversalHeight()
    public void levelOrderTraversal(Node<E> rootNode,Consumer<E> consumer){
        Queue<Node<E>> oneQueue = new ConcurrentLinkedQueue<>();
        oneQueue.add(rootNode);
        while (!oneQueue.isEmpty()){
            Node<E> pollNode = oneQueue.poll();
            //回调函数执行。
            consumer.accept(pollNode.element);
            if(pollNode.leftNode != null){
                oneQueue.add(pollNode.leftNode);
            }
            if(pollNode.rightNode != null){
                oneQueue.add(pollNode.rightNode);
            }
        }
    }

    //利用层序遍历计算二叉树的高度
    public int levelOrderTraversalHeight(){
        Queue<Node<E>> oneQueue = new LinkedList<>();
        oneQueue.add(rootNode);
        //当前层弹出了多少个元素。
        int count = 0 ;
        //当前层元素的总个数。
        int queueElementSize = 1;
        //当前层的高度。
        int height = 0;
        while (!oneQueue.isEmpty()){
            Node<E> pollNode = oneQueue.poll();
            //每弹出一个元素，当前层的元素个数就 - 1；
            queueElementSize --;
            if(pollNode.leftNode != null){
                oneQueue.add(pollNode.leftNode);
            }
            if(pollNode.rightNode != null){
                oneQueue.add(pollNode.rightNode);
            }
            //当前层弹出了 queueElementSize 个元素时，说明当前层元素已经弹出完毕，那么高度就 + 1。同时将下一层的所有元素个数置为队列中的元素个数。
            if(queueElementSize == 0){
                queueElementSize = oneQueue.size();
                height ++;
            }
        }
        return height;
    }

    //利用层序遍历计算二叉树的高度
    public int height(Node<E> node){
        //如果是叶子结点 的左右子节点，则返回0。
        if(node == null) return 0;
        //计算当前节点的左子树高度
        int leftHeight = height(node.leftNode);
        //计算当前节点的右子树高度
        int rightHeight = height(node.rightNode);
        //当前节点的左右子树的高度都计算后，取二者的较大值 + 1，作为当前节点的高度。
        int max = Math.max(leftHeight, rightHeight);
        return max +1;
    }

    //打印当前二叉树
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        toString(rootNode,stringBuilder,"");
        return stringBuilder.toString();
    }

    private void toString(Node<E> node,StringBuilder stringBuilder,String prefix){
        if(node == null) return ;
        stringBuilder.append(prefix ).append(node.element).append("\n");
        toString(node.leftNode,stringBuilder,prefix + "【L】");
        toString(node.rightNode,stringBuilder,prefix + "【R】");
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
