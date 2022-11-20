//package cn.pxl.leetCode.demo05Tree;
//
//import cn.pxl.leetCode.common.TreeNode;
//
////450. 删除二叉搜索树中的节点
////给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的key对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
////一般来说，删除节点可分为两个步骤：
////首先找到需要删除的节点；
////如果找到了，删除它。
//public class Xlp450 extends TreeNode{
//
//    public TreeNode deleteNode(TreeNode root, int key) {
//        //寻找树中为key的节点
//        TreeNode currentNode = null;
//        //当前节点的父节点
//        TreeNode previousNode = null;
//        while (root != null){
//            if(root.val == key){
//                currentNode = root;
//                break;
//            }
//            if(root.val < key){
//                previousNode = root;
//                root = root.right;
//            }else {
//                previousNode = root;
//                root = root.left;
//            }
//        }
//
//        //删除节点不存在，或者删除节点是根节点时。
//        if(currentNode == null || previousNode == null){
//            return null;
//        }
//
//        //找到了要删除的前节点的位置，删除这个节点。
//        //1.当前要删除的节点度为2。
//        if(currentNode.left != null && currentNode.right != null){
//
//        }
//        //到这里，都是度为1和度为0的节点。
//    }
//
//    //寻找当前节点的前驱节点
//    private TreeNode pre(TreeNode currentNode){
//        TreeNode leftNode = currentNode.left;
//        //当前节点没有左子树，返回null。
//        if(leftNode == null){
//            return null;
//        }
//        while (leftNode.right != null){
//            leftNode = leftNode.right;
//        }
//
//    }
//}
//
