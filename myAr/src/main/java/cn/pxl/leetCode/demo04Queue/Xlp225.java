package cn.pxl.leetCode.demo04Queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

//https://leetcode.cn/problems/implement-stack-using-queues/
//请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。

public class Xlp225 {

    //存放临时元素
    private Queue<Integer> oneQueue = new ConcurrentLinkedQueue<>();
    //存放持久元素
    private Queue<Integer> twoQueue = new ConcurrentLinkedQueue<>();

    public void push(int x) {
        twoQueue.add(x);
    }

    public int pop() {
         return popOrTop(0);
    }

    public int top() {
        return popOrTop(1);
    }

    public boolean empty() {
        return twoQueue.isEmpty();
    }

    private int popOrTop(int popOrTop){
        if(twoQueue == null){
            return -1;
        }
        Queue<Integer> notEmptyQueue = twoQueue;
        Queue<Integer> emptyQueue = oneQueue;

        Integer poll = notEmptyQueue.poll();
        while (!notEmptyQueue.isEmpty()){
            emptyQueue.add(poll);
            poll = notEmptyQueue.poll();
        }
        // top
        if(popOrTop == 1){
            emptyQueue.add(poll);
        }
        twoQueue = emptyQueue;
        oneQueue = notEmptyQueue;
        return poll;
    }
}
