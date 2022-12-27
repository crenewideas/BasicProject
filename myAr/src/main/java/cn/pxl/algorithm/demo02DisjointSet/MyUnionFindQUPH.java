package cn.pxl.algorithm.demo02DisjointSet;

import cn.pxl.algorithm.AbstractDisjointSet;

//基于 path halving 的并查集优化（路径上每隔一个元素就指向其祖父节点）
public class MyUnionFindQUPH extends AbstractDisjointSet {
    //存放当前某一 index 元素的高度。
    private int[] ranks;

    public MyUnionFindQUPH(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            ranks[i] = 1;
        }
    }

    //find 寻找根节点 ； 其中 v = parents[v] 意思是将 v 索引处的值 改为祖父节点的值,同时，会跳过 v 与 其祖父节点中间的值。
    @Override
    public int find(int v){
        rangeCheck(v);
        while (v != parents[v]){
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }

    @Override
    public void union(int v1,int v2){
        int p1 = find(v1);
        int p2 = find(v2);
        if(p1 == p2) return;
        if(ranks[p1] < ranks[p2]){
            parents[p1] = p2;
        } else if(ranks[p2] < ranks[p1]){
            parents[p2] = p1;
        } else {
            parents[p2] = p1;
            ranks[p1] += 1;
        }
    }

}
