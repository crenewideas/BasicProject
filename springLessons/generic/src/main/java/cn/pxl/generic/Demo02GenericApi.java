package cn.pxl.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Demo02GenericApi {
    public static void main(String[] args) {
        Class intClass = int.class;
        Class objectArrayClass = Object[].class;
        Class rowClass = String.class;
        Class genericClass = ArrayList.class;
        //class java.util.ArrayList
        System.out.println(genericClass);
        ParameterizedType genericSuperClass = (ParameterizedType)ArrayList.class.getGenericSuperclass();
        //java.util.AbstractList<E>
        System.out.println(genericSuperClass);
        //class java.util.AbstractList
        System.out.println(genericSuperClass.getRawType());
        //E
        Type[] actualTypeArguments = genericSuperClass.getActualTypeArguments();
        Stream.of(actualTypeArguments).forEach(System.out::println);
    }
}
