package cn.pxl.dataStructure.demo07Map;

import cn.pxl.dataStructure.common.MyAbstractBinaryTree;
import cn.pxl.dataStructure.intf.MyMapInterface;

import java.util.Arrays;

public class MyHashMap<K,V> implements MyMapInterface<K,V> {

    public static final boolean BLACK = true;
    public static final boolean RED = false;
    public static final int DEFAULT_CAPACITY = 16;
    //存储键值对的个数。size 一般情况下 > table.length.
    private int size;
    //数组。数组中的每一个元素，存储的是某个红黑树根节点的地址值。
    private Node<K,V>[] table = new Node[DEFAULT_CAPACITY];

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if(size == 0) return;
        size = 0;
        Arrays.fill(table, null);
    }

    @Override
    public V put(K key, V value) {
        int index = getIndex(key);
        //当前index位置的桶
        Node<K, V> bucket = table[index];
        if(bucket == null){
            //当前桶位置为空，那么向这个位置添加元素。
            bucket = new Node<K,V>(key,value);
            table[index] = bucket;
            size ++;
        }
        V oldValue = null;
        //产生hash冲突，需要添加新的数据到对应位置。
        Node<K,V> currentNode = bucket.nextNode;
        //存放上一次遍历时的元素内容
        Node<K,V> previousNode ;
        do {
            //比较链表中对应位置的key是否相同
            K currentKey = currentNode.key;
            if(key == null){
                if(currentKey == null){
                    oldValue = currentNode.value;
                    currentNode.value = value;
                    return oldValue;
                }
            }else {
                if (key.equals(currentKey)) {
                    oldValue = currentNode.value;
                    currentNode.value = value;
                    return oldValue;
                }
            }
            previousNode = currentNode;
            currentNode = currentNode.nextNode;
        } while (currentNode != null);
        //这里说明桶中没有相同key的数据，需要新加数据到桶中。
        previousNode.nextNode = new Node<K, V>(key,value);
        return value;
    }

    @Override
    public V get(K key) {
        Node<K, V> bucket = table[getIndex(key)];
        if(bucket == null) return null;
        while (bucket != null){
            if(key == null){
                if(bucket.key == null){
                    return bucket.value;
                }
            }else {
                if(key.equals(bucket.key)){
                    return bucket.value;
                }
            }
            bucket = bucket.nextNode;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

    }

    //根据Key生成对应的索引，也就是桶下标
    private int getIndex(K k){
        if(k == null) return 0;
        int hashCode = k.hashCode();
        //二次hash，保证高位低位都能参与运算。
        hashCode = hashCode ^ hashCode >>> 16;
        return   hashCode & (table.length - 1);
    }

    //存储链表中的元素。
    private static class Node<K,V>{
        private K key;
        private V value;
        Node<K,V> nextNode;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
