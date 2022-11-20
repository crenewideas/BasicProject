package cn.pxl.leetCode.demo05Tree;

import cn.pxl.leetCode.common.TreeNode;
import lombok.NoArgsConstructor;

//给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
//输入：root = [4,2,7,1,3,6,9]
//输出：[4,7,2,9,6,3,1]
@NoArgsConstructor
public class Xlp226 extends TreeNode {

    //思路一：
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        //如果是叶子节点，直接返回。
        if(root.left == null && root.right == null){
            return root;
        }
        TreeNode leftNode;
        TreeNode rightNode;
        TreeNode rootNode = new TreeNode();
        //非叶子节点，翻转左子树和右子树。
        if(root.left != null){
            rightNode =  invertTree(root.left);
            rootNode.right = rightNode;
        }
        if(root.right != null){
            leftNode = invertTree(root.right);
            rootNode.left = leftNode;
        }
        rootNode.val = root.val;
        return rootNode;
    }

    //思路2
    public TreeNode invertTree2(TreeNode root) {
        if(root == null){
            return null;
        }
        //如果是叶子节点，直接返回。
        if(root.left == null && root.right == null){
            return root;
        }
        TreeNode leftNode = root.left;
        root.left = invertTree2(root.right);
        root.right = invertTree2(leftNode);

        return root;
    }



}
