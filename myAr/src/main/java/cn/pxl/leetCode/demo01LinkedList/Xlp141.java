package cn.pxl.leetCode.demo01LinkedList;

public class Xlp141 {

    public boolean hasCycle(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        ListNode slowPoint = head;
        ListNode fastPoint = head.next;

        while (fastPoint != null && fastPoint.next != null){
            if(slowPoint == fastPoint){
                return true;
            }
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next.next;
        }
        return false;
    }

}
