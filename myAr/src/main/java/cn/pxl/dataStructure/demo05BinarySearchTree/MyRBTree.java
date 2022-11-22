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

    @Override
    protected Node<E> createNode(E element, Node<E> parentNode) {
        return new RBNode<E>(element,parentNode);
    }

    //添加元素后，恢复整个树的平衡
    //1.元素只可能添加到4阶B树的叶子节点。
    //2.4阶B树的叶子节点的情况只可能有以下四种情况：RBR、RB、BR、B。（中间节点一定是黑色，左右红色节点会和中间的黑色节点合并形成一个B树节点）。
    //3.对于B这种情况，直接添加，并且将节点染色为R即可（节点没有上溢，添加后也才2个节点）
    //4.对于RB、BR来说，可以进行旋转，并将中间节点染色为B，左右节点染色为R。（节点没有上溢，添加后也才3个节点）
    //5.对于RBR来说，B节点上溢，左右分为两棵子树，B节点上溢后，按新添加的节点处理。

    //TODO 生成了树，但是有问题，需要后续排查。
    @Override
    protected void convertToBalanceTree(Node<E> node) {
        if(node == null) return;
        RBNode<E> currentNode = color(node,BLACK);
        if(rootNode == null) {
            //根节点染色为黑色
            rootNode = currentNode;
            return;
        }
        //当前节点父节点是空的，只需要保证当前节点是黑色即可，上方已经赋值为黑色，所以直接返回。
        if(currentNode.parentNode == null){
            return;
        }
        //当前节点的父节点是黑色，说明是B树中的 B 这种情况 ， 或者是 RB、BR 这两种情况中，向B节点添加元素的情况。
        if (colorOfBlack(currentNode.parentNode)) {
            //父结点为黑色，父结点的子节点染色为红色，即可满足红黑树的性质，黑色与当前红色可以合并为一个B树节点。
            color(currentNode,RED);
            return;
        }
        //这里，一定是添加节点的父结点是红色的情况。需要区分是 RBR，还是RB、BR。区分的依据是：uncle 节点是否为黑色。如果uncle节点为黑色，那么属于RB、BR的情况（uncle节点为 null 当然是黑色。）
        //uncle节点是红色，属于RBR情况，需要处理上溢节点。
        if (colorOfBlack(currentNode.uncle())) {
            //到这里，分为四种子情况 ：  1:  B      2:  B       3:  B           4:  B
            //                         R          R                 R              R
            //                       C              C            C                     C
            //其中，1和2需要右旋；3和4需要左旋。

            //afterRotateNode 也就是旋转后的树的根节点（或者说是B树节点的BLACK节点）
            RBNode<E> afterRotateNode;
            //当前B树节点的父节点是不是左子节点。为空表示当前B树节点没有父节点。
            Boolean currentSubTreeIsLeft = currentNode.parentNode.parentNode.parentNode== null ? null : currentNode.parentNode.parentNode.isLeftChild();
            //当前B树的root节点（或Black节点）。
            Node<E> grandNode =  currentNode.parentNode.parentNode;
            //需要左旋 ，对应情况 3 和 4。
            if (currentNode.parentNode.isRightChild()) {
                if(currentNode.isLeftChild()){
                    //需要右旋转
                    afterRotateNode = rotateRight(currentNode.parentNode,currentNode);
                    afterRotateNode.parentNode = grandNode;
                    grandNode.rightNode = afterRotateNode;
                    afterRotateNode.rightNode.parentNode = currentNode;
                }
                afterRotateNode = rotateLeft(grandNode,currentNode);

            //需要右旋，对应情况 1 和 2。
            } else {
                if(currentNode.isRightChild()){
                    //需要左旋转
                    afterRotateNode = rotateLeft(currentNode.parentNode,currentNode);
                    afterRotateNode.parentNode = grandNode;
                    grandNode.leftNode = afterRotateNode;
                    afterRotateNode.leftNode.parentNode = currentNode;
                }
                afterRotateNode = rotateRight(grandNode,currentNode);
            }

            if (currentSubTreeIsLeft == null) {
                //这里是这棵B树节点原本就没有父节点的情况。
                afterRotateNode.parentNode = null;

            }else if(currentSubTreeIsLeft){
                //当前B树节点是父节点的左子树
                grandNode.parentNode.leftNode = afterRotateNode;
                afterRotateNode.parentNode = grandNode.parentNode;
            }else {
                //当前B树节点是父节点的右子树
                grandNode.parentNode.rightNode = afterRotateNode;
                afterRotateNode.parentNode = grandNode.parentNode;
            }
            grandNode.parentNode = afterRotateNode;
        }else {
            //这里是RBR情况。B节点直接上溢，左右R节点变成B节点即可。
            black(currentNode.parentNode);
            black(currentNode.uncle());
            //将B节点上溢。也就是当前B节点作为参数进行递归即可。
            convertToBalanceTree(currentNode.parentNode.parentNode);
        }
    }

    private RBNode<E> rotateLeft(Node<E> grandParentNode,Node<E> currentNode){
        Node<E> parentNode = grandParentNode.rightNode;
        grandParentNode.rightNode = parentNode.leftNode;
        if(parentNode.leftNode != null) parentNode.leftNode.parentNode = grandParentNode;
        parentNode.leftNode = grandParentNode;
        //染色
        //祖父节点变成了B树的左边节点，需要染成红色
        red(grandParentNode);
        //父结点变成了B树的中间节点，需要染成黑色
        black(parentNode);
        //当前节点变成了B树的右边节点，需要染成红色
        red(currentNode);
        return (RBNode<E>) parentNode;
    }

    private RBNode<E> rotateRight(Node<E> grandParentNode,Node<E> currentNode){
        Node<E> parentNode = grandParentNode.leftNode;
        grandParentNode.leftNode = parentNode.rightNode;
        if(parentNode.rightNode != null) parentNode.rightNode.parentNode = grandParentNode;
        parentNode.rightNode = grandParentNode;
        //染色
        //祖父节点变成了右边节点，需要染成红色
        red(grandParentNode);
        //父结点变成了中间节点，需要染成黑色
        black(parentNode);
        //当前节点变成了B树的右边节点，需要染成红色
        red(currentNode);
        return (RBNode<E>) parentNode;
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

    //对当前节点染色成黑色
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

    //当前节点是否是黑，空节点也是黑色（叶子结点视为黑色）
    private boolean colorOfBlack(Node<E> node){
        return node == null || ((RBNode<E>)node).color == BLACK;
    }


    @Override
    public Object string(Object node) {
        RBNode<E> rbNode = (RBNode<E>) node;
        String str = "";
        if(rbNode.color == BLACK) str ="B_";
        return str + rbNode.element.toString();
    }

    private static class RBNode<E> extends Node<E>{
        boolean color = BLACK;
        public RBNode(E element, Node<E> parentNode) {
            super(element, parentNode);
        }
        public RBNode(E element, Node<E> parentNode, Node<E> leftNode, Node<E> rightNode) {
            super(element, parentNode, leftNode, rightNode);
        }

        @Override
        public String toString() {
            String str = "";
            if(color == BLACK) str ="B_";
            return str + element.toString();
        }
    }
}
