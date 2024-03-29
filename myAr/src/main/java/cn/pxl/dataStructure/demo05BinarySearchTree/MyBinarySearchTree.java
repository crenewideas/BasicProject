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
            if(comp == 0) return false;
            if(comp > 0){
                rootNode = rootNode.rightNode;
            } else {
                rootNode = rootNode.leftNode;
            }
        }
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

    //计算二叉树高度方式一：利用层序遍历计算二叉树的高度
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

    //计算二叉树高度方式二：利用递归方式计算二叉树的高度
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

    //计算二叉树的前驱节点
    private Node<E> predecessor(Node<E> node){
        if(node == null){
            return null;
        }
        //1.左子树存在时：前驱节点，是左子树中序遍历的最后一个元素的结果。
        //2.左子树不存在，并且父节点存在时：前驱节点，是父节点中的某个值。
        //3.左子树不存在，并且父结点也不存在，那么没有前驱节点。

        Node<E> previousNode = node;
        if (node.hasLeftChild()) {
            //找到左子树最右边的结果，作为当前节点的前驱节点
            while (node != null){
                previousNode = node;
                node = node.rightNode;
            }
            return previousNode;
        }
        if(node.hasParent()){
            while (node.hasParent() && node.equals(node.parentNode.leftNode)){
                node = node.parentNode;
            }
            return node.parentNode;
        }else {
            return null;
        }
    }


    //计算二叉树的后继节点
    private Node<E> postdecessor(Node<E> node){
        if(node == null){
            return null;
        }
        //1.右子树存在时：后继，右子树最左边的节点。
        //2.右子树不存在，则没有后继节点。

        if(node.hasRightChild()){
            Node<E> beginNode = node.rightNode;
            while (beginNode != null && beginNode.hasLeftChild()){
                beginNode = beginNode.leftNode;
            }
            return beginNode;
        } else {
            return null;
        }
    }

//    //判断当前树是否是完全二叉树：层序遍历方式一，复杂写法。
//    public boolean isCompleteBinaryTree(){
//        Queue<Node<E>> queue = new LinkedList<>();
//        if(rootNode == null) return false;
//        queue.add(rootNode);
//        boolean flag = false;
//        while (!queue.isEmpty()){
//            Node<E> pollNode = queue.poll();
//            //已经到了分界点，之后还有非叶子节点，那么 不是完全二叉树。
//            if(flag && !pollNode.isLeaf()){
//                return false;
//            }
//            //度为二，直接入队。
//            if(pollNode.hasTwoChild()){
//                queue.add(pollNode.leftNode);
//                queue.add(pollNode.rightNode);
//
//            //度为1的一种情况：当前节点只存在右节点，明显不是完全二叉树。
//            }else if(pollNode.hasRightChild()){
//                return false;
//
//            //当前节点只存在左节点，或者当前节点为叶子节点：这时，这个节点的接下来所有的节点的度都必须为0，否则就不是完全二叉树。
//            }else {
//                //只存在左子节点，左子节点入队。
//                if(pollNode.hasLeftChild()){
//                    queue.add(pollNode.leftNode);
//                }
//                flag = true;
//            }
//        }
//        return true;
//    }

    //完全二叉树的判断方式，简洁写法：
    public boolean isCompleteBinaryTree(){
        Queue<Node<E>> queue = new LinkedList<>();
        if(rootNode == null) return false;
        queue.add(rootNode);
        boolean flag = false;
        while (!queue.isEmpty()) {
            Node<E> pollNode = queue.poll();

            if(flag && !pollNode.isLeaf()){
                return false;
            }

            if(pollNode.leftNode != null){
                queue.add(pollNode.leftNode);

            //当前节点只有右子节点，不是完全二叉树
            }else if(pollNode.rightNode != null){
                return false;
            }

            if(pollNode.rightNode != null){
                queue.add(pollNode.rightNode);

            //当前节点只有左子节点，或当前节点是叶子节点
            }else {
                flag =true;
            }
        }
        return true;
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

        public boolean isLeaf(){
            return leftNode == null && rightNode == null;
        }

        public boolean hasTwoChild(){
            return leftNode != null && rightNode != null;
        }

        public boolean hasLeftChild(){
            return leftNode != null && rightNode == null;
        }

        public boolean hasRightChild(){
            return leftNode == null && rightNode != null;
        }

        public boolean hasParent(){return parentNode != null;}

    }

}
