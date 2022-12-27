package cn.pxl.algorithm.demo02DisjointSet;

import cn.pxl.algorithm.AbstractDisjointSet;

//基于 path compression 的并查集优化
public class MyUnionFindQUCR extends AbstractDisjointSet {
    //存放当前某一 index 元素的高度。
    private int[] ranks;

    public MyUnionFindQUCR(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            ranks[i] = 1;
        }
    }

    //find 寻找根节点 ； 其中 parents[v] = find(parents[v]) 意思是将 v 索引处的 值结点 改为根节点的值。
    //find 的执行结果，会导致 v 的所有父结点都指向 根节点，也就是将 v 路径进行了压缩。
    //降低树高，但花费的成本也较高。
    @Override
    public int find(int v){
        rangeCheck(v);
        if(parents[v] != v){
            parents[v] = find(parents[v]);
        }
        return parents[v];
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
