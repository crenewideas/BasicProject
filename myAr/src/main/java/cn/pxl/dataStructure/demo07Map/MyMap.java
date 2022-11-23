package cn.pxl.dataStructure.demo07Map;

import cn.pxl.dataStructure.demo05BinarySearchTree.MyAvlTree;
import cn.pxl.dataStructure.intf.MyMapInterface;

import java.util.Comparator;

public class MyMap<K,V> implements MyMapInterface<K,V> {
    private final MyAvlTree<KeyAndValue<K,V>> elements;

    public MyMap() {
        elements = new MyAvlTree<>();
    }

    public MyMap(Comparator<K> comparator) {
        //        Comparator<KeyAndValue<K, V>> myMapComparator = (o1, o2) -> comparator.compare(o1.key, o2.key);
        Comparator<KeyAndValue<K, V>> myMapComparator = new Comparator<KeyAndValue<K, V>>() {
            @Override
            public int compare(KeyAndValue<K, V> o1, KeyAndValue<K, V> o2) {
                return comparator.compare(o1.key, o2.key);
            }
        };
        elements = new MyAvlTree<>(myMapComparator);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public V put(K key, V value) {
        KeyAndValue<K, V> keyAndValue = new KeyAndValue<>();
        keyAndValue.key = key;
        keyAndValue.value = value;
        elements.add(keyAndValue);
        return value;
    }

    @Override
    public V get(K key) {
        KeyAndValue<K, V> resultKeyValue = elements.interTraversal(keyAndValue -> {

            if (key == null) {
                if (keyAndValue.key == null) {
                    return keyAndValue;
                }
            } else {
                if (key.equals(keyAndValue.key)) {
                    return keyAndValue;
                }
            }
            return null;
        });
        return resultKeyValue == null ? null : resultKeyValue.value;
    }

    @Override
    public V remove(K key) {
        KeyAndValue<K, V> keyAndValue = getKeyAndValue(key);
        if(keyAndValue == null) return null;
        elements.remove(keyAndValue);
        return null;
    }

    private KeyAndValue<K,V> getKeyAndValue(K key) {
        return elements.interTraversal(keyAndValue -> {
            if (key == null) {
                if (keyAndValue.key == null) {
                    return keyAndValue;
                }
            } else {
                if (key.equals(keyAndValue.key)) {
                    return keyAndValue;
                }
            }
            return null;
        });
    }


    @Override
    public boolean containsKey(K key) {
        return getKeyAndValue(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        KeyAndValue<K, V> kvKeyAndValue = elements.interTraversal(keyAndValue -> {
            if (value == null) {
                if (keyAndValue.value == null) {
                    return keyAndValue;
                }
            } else {
                if (value.equals(keyAndValue.value)) {
                    return keyAndValue;
                }
            }
            return null;
        });
        return kvKeyAndValue != null;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        elements.interTraversal((element)->{
            visitor.visit(element.key, element.value);
        });
    }

    private static class KeyAndValue<K,V>{
        private K key;
        private V value;
    }

}
