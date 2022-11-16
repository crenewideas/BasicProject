package cn.pxl.leetCode.demo01LinkedList;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
    }
}