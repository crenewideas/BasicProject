package cn.pxl.algorithm.demo02DisjointSet;

import cn.pxl.algorithm.AbstractDisjointSet;

//基于 size 的并查集优化
public class MyUnionFindQUS extends AbstractDisjointSet {
    //当前 index 元素的子节点个数。
    private int[] sizes;
    public MyUnionFindQUS(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            sizes[i] = 1;
        }
    }

    @Override
    public int find(int v){
        rangeCheck(v);
        while (v != parents[v]){
            v = parents[v];
        }
        return v;
    }

    @Override
    public void union(int v1,int v2){
        int p1 = find(v1);
        int p2 = find(v2);
        if(p1 == p2) return;
        if(sizes[p1] < sizes[p2]){
            parents[p1] = p2;
            sizes[p2] += sizes[p1];
        } else {
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        }
    }

}
