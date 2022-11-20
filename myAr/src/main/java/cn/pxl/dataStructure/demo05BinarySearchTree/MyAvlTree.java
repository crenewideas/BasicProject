package cn.pxl.dataStructure.demo05BinarySearchTree;

import cn.pxl.dataStructure.common.MyAbstractBinaryTree;
import lombok.Data;

import java.util.Comparator;

// 艾薇儿树，也是二叉搜索树，因此直接继承。
// 特有的逻辑:1.添加方法，在添加的同时，还需保证树的平衡性。
// 需实现平衡调整逻辑，重写父类中给子类预留的方法。
public class MyAvlTree<E> extends MyBinarySearchTree2<E> {

    public MyAvlTree() {
    }

    public MyAvlTree(Comparator<E> comparator) {
        super.comparator = comparator;
    }

    //这个方法用于添加节点后，调整树到平衡状态。
    //通过新添加的节点，找到祖宗节点中失衡的节点，并进行调整。
    @Override
    protected void convertToBalanceTree(Node<E> node) {
        //更新整个树的高度
        updateHeight((AvlTreeNode<E>) rootNode);
        AvlTreeNode<E> avlTreeNode = (AvlTreeNode<E>) node;
        while ((avlTreeNode = (AvlTreeNode<E>)avlTreeNode.parentNode) != null){
            if(!isBalance(avlTreeNode)){
                //恢复平衡信息。
                reBalance(avlTreeNode);
                break;
            }
        }
    }

    private void reBalance(AvlTreeNode<E> grandParent) {
        AvlTreeNode<E> parentNode = grandParent.getParent();
        //判断 grandParent 是父结点的 哪棵子树
        Boolean isParentLeft = null;
        if(parentNode != null){
            isParentLeft = grandParent == parentNode.leftNode;
        }
        //获取当前 grandParent 节点的最高子树。这棵子树上，存放着新添加的子节点
        AvlTreeNode<E> parent = getHigherChildTree(grandParent);
        //获取 parent 节点的最高子树。这颗子树上，存放着新添加的子节点
        AvlTreeNode<E> childTree = getHigherChildTree(parent);
        //判断是 LL , LR , RL , RR。
        // 1. 第一个节点是 L还是R。
        boolean oneIsLeft = parent == grandParent.getLeftNode();
        //2.第二个节点是 L还是R。也就是新添加的节点是L还是R。
        boolean twoIsLeft = childTree == parent.getLeftNode();
        //LX
        if(oneIsLeft){
            //LR时，先将R左旋，变为LL。
            if(!twoIsLeft){
                //这段代码需要先画图，一步一步走。比如画一个根节点是 6 的二叉搜索树：23467（中序遍历），然后新增一个5节点。这时，6的平衡因子是2，而且是LR模式，先左旋为 LL。
                grandParent.leftNode = childTree;
                childTree.parentNode = grandParent;
                parent.rightNode = null;
                parent.parentNode = childTree;
                childTree.leftNode = parent;
                //重新获取LL，这两个节点（这里考虑到LR，转变成LL后，需要重新获取最新的LL信息）
                parent = getHigherChildTree(grandParent);
            }
            //统一处理LL。
            //LL进行右旋
            grandParent.leftNode = parent.rightNode;
            if(parent.rightNode != null){
                parent.rightNode.parentNode = grandParent;
            }
            parent.rightNode = grandParent;
            grandParent.parentNode = parent;
        }else {
            //RL时，先将L右旋，变为RR。
            if(twoIsLeft){
                grandParent.rightNode = childTree;
                childTree .parentNode = grandParent;
                parent.leftNode = null;
                parent.parentNode = childTree;
                childTree.rightNode = parent;
                //重新获取RR，这两个节点（这里考虑到RL，转变成RR后，需要重新获取最新的RR信息）
                parent = getHigherChildTree(grandParent);
            }
            //统一处理RR。
            grandParent.rightNode = parent.leftNode;
            if(parent.leftNode != null){
                parent.leftNode.parentNode = grandParent;
            }
            parent.leftNode = grandParent;
            grandParent.parentNode = parent;
        }
        //已完成子树的旋转，这时，需要将旋转后的树挂载到 parentNode 节点下。
        if(isParentLeft == null){
            rootNode = parent;
        }else if(isParentLeft){
            parentNode.leftNode = parent;
        } else {
            parentNode.rightNode = parent;
        }
        parent.parentNode = parentNode;
    }

    //返回更新后的节点高度。
    private int updateHeight(AvlTreeNode<E> rootNode){
        if(rootNode.isLeaf()) {
            rootNode.height = 1;
            return 1;
        }
        int leftHigh = 0;
        int rightHigh = 0;
        if(rootNode.leftNode != null){
             leftHigh = updateHeight((AvlTreeNode<E>) rootNode.leftNode);
        }
        if(rootNode.rightNode != null){
            rightHigh = updateHeight((AvlTreeNode<E>) rootNode.rightNode);
        }
        rootNode.height = (1 + Math.max(leftHigh,rightHigh));
        return rootNode.height;
    }

    //返回当前节点的最高的子树。
    private AvlTreeNode<E> getHigherChildTree(AvlTreeNode<E> avlTreeNode){
        AvlTreeNode<E> leftChildTree = avlTreeNode.getLeftNode();
        AvlTreeNode<E> rightChildTree = avlTreeNode.getRightNode();
        AvlTreeNode<E> oneNode;
        //失衡的子树，取左右子节点中高度最大的。
        if(leftChildTree == null){
            oneNode = rightChildTree;
        } else if(rightChildTree == null){
            oneNode = leftChildTree;
        }else {
            oneNode = leftChildTree.height > rightChildTree.height ? leftChildTree : rightChildTree;
        }
        return oneNode;
    }

    @Override
    protected Node<E> createNode(E element,Node<E> parentNode){
        return new AvlTreeNode<>(element, parentNode);
    }

    private boolean isBalance(AvlTreeNode<E> node){
        return Math.abs(node.balanceFactor()) <= 1;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private static class AvlTreeNode<E> extends MyAbstractBinaryTree.Node<E>{

        int height = 1;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public AvlTreeNode(E element, Node<E> parentNode) {
            super(element, parentNode,null,null);
        }

        public AvlTreeNode(E element, Node<E> parentNode, int height) {
            super(element, parentNode,null,null);
            this.height = height;
        }

        public int balanceFactor(){
            int leftHeight = leftNode == null ? 0 : ((AvlTreeNode<E>)leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AvlTreeNode<E>)rightNode).height;
            return leftHeight - rightHeight;
        }

        //更新当前节点自己的高度。
        public void updateHeight(){
            int leftHeight = leftNode == null ? 0 : ((AvlTreeNode<E>)leftNode).height;
            int rightHeight = rightNode == null ? 0 : ((AvlTreeNode<E>)rightNode).height;
            this.height = 1 + Math.max(leftHeight,rightHeight);
        }

        public AvlTreeNode<E> getParent(){
            return (AvlTreeNode<E>) parentNode;
        }

        public AvlTreeNode<E> getLeftNode(){
            return (AvlTreeNode<E>) leftNode;
        }

        public AvlTreeNode<E> getRightNode(){
            return (AvlTreeNode<E>) rightNode;

        }


    }
}
