package cn.pxl.leetCode.demo01LinkedList;

public class Xlp206 {

    //翻转一个链表，方式一：递归调用。
    public ListNode reverseNode(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseNode(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    //翻转一个链表，方式二：非递归调用
    public ListNode reverseNode2(ListNode head){
        ListNode newHead = null;
        while (head != null){
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

}
