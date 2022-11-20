package cn.pxl.dataStructure.demo05BinarySearchTree;

import cn.pxl.dataStructure.common.MyAbstractBinaryTree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

//二叉搜索树
@Data
@NoArgsConstructor
public class MyBinarySearchTree2<E> extends MyAbstractBinaryTree<E> {

    public MyBinarySearchTree2(Comparator<E> comparator) {
        super.comparator = comparator;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        //根节点：
        if(rootNode == null){
            rootNode = createNode(element,null);
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
        Node<E> newNode = createNode(element,parentNode);
        if(comp > 0){
            parentNode.rightNode = newNode;
        } else if(comp < 0 ){
            parentNode.leftNode = newNode;
        }else {
            //如果相同，进行覆盖，这是因为自定义对象可能存在比较相同，但其他属性不同的情况。
            parentNode.element = element;
        }
        size ++;
        //子类实现这个方法，调整树到平衡的状态。
        convertToBalanceTree(newNode);
    }

    // 留给子类实现调整树平衡的逻辑。二叉搜索树没有自平衡的功能，因此给空实现。
    // 入参：node 是新添加的元素。
    protected void convertToBalanceTree(Node<E> node){

    }

    //这个方法，子类可以重写，返回的就是子类的节点类型。
    protected Node<E> createNode(E element,Node<E> parentNode){
        return new Node<>(element, parentNode);
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
}
