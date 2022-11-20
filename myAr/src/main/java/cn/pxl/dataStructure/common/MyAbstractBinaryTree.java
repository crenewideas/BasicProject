package cn.pxl.dataStructure.common;

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

//抽象二叉树类，公共逻辑有：前序遍历方式，中序遍历方式，后续遍历方式，获取元素个数，是否是完全二叉树，树的高度算法等
@Data
public abstract class MyAbstractBinaryTree<E> implements MyTree<E> , BinaryTreeInfo {

    protected int size;

    protected Node<E> rootNode;

    protected Comparator<E> comparator;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        rootNode = null;
    }

    @Override
    public int size() {
        return size;
    }

    //比较节点中值和传入的值的大小
    // < 0  : element1 < element2
    // = 0  : element1 = element2
    // > 0  : element1 > element2
    protected int compareNodeElement(E element1, E element2){
        if(comparator != null){
            return comparator.compare(element1,element2);
        }
        if(element1 instanceof Comparable){
            Comparable<E> comparableElement1 = (Comparable<E>)element1;
            return comparableElement1.compareTo(element2);
        }
        throw new RuntimeException("添加类型必须实现Comparable接口，或定义Comparator比较器");
    };

    //判断是否是叶子节点：
    protected boolean isLeafNode(Node<E> node){
        return node != null && node.leftNode == null && node.rightNode == null;
    }

    protected void nodeNotNullCheck(Node<E> node){
        if(node == null){
            throw new IllegalArgumentException("节点不能为空！");
        }
    }

    protected void elementNotNullCheck(E element){
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
    public static int height(Node node){
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
    protected Node<E> predecessor(Node<E> node){
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
    protected Node<E> postdecessor(Node<E> node){
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
    protected boolean isCompleteBinaryTree(){
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
    @AllArgsConstructor
    @ToString
    protected static class Node<E>{
        public E element = null;
        public Node<E> parentNode = null;
        public Node<E> leftNode = null;
        public Node<E> rightNode = null;
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
