package cn.pxl.generic;

import java.util.ArrayList;
import java.util.Collection;

public class Demo01Generic {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("hello");
        collection.add("world");
        //编译时错误
        //collection.add(1);
        //泛型擦写
        Collection temp = collection;
        //泛型擦鞋的问题，任何泛型类型，都可以被擦除。泛型在运行时没有约束，因此可以擦除后操作。
        temp.add(1);
        System.out.println(temp);
    }
}
