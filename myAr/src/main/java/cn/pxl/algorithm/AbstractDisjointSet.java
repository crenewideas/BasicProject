package cn.pxl.algorithm;

import lombok.Data;

@Data
public abstract class AbstractDisjointSet {
    protected int[] parents;

    public AbstractDisjointSet(int capacity) {
        if(capacity <= 0) throw new RuntimeException("capacity must >= 1");
        this.parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    public abstract int find(int v);

    public boolean isSame(int v1,int v2){
        return find(v1) == find(v2);
    };

    public abstract void union(int v1,int v2);

    protected void rangeCheck(int v){
        if(v <= 0 || v > parents.length) throw new IllegalArgumentException("v is out of bounds");
    }

}
