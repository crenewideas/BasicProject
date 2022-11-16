package cn.pxl.leetCode.demo01LinkedList;

public class Xlp237 {

    public void deleteNode(ListNode node){
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
