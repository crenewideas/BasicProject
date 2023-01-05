package cn.pxl.generic;

import org.springframework.core.ResolvableType;

import java.util.HashMap;
import java.util.List;

// ResolvableType 用于替代 GenericTypeResolver 或者 GenericCollectionTypeResolver
public class Demo04ResolvableType {

    private HashMap<Integer, List<String>> myMap;

    public static void main(String[] args) throws NoSuchFieldException {
//        doDemo01();
        doDemo02();
    }

    private static void doDemo01() throws NoSuchFieldException {
        ResolvableType t = ResolvableType.forField(Demo04ResolvableType.class.getDeclaredField("myMap"));
        ResolvableType superType = t.getSuperType();
        ResolvableType superSuperType = superType.getSuperType();
        System.out.println(superType); //AbstractMap<Integer,List<String>>
        System.out.println(superSuperType); //java.lang.Object
        System.out.println(t.asMap()); // Map<Integer, List<String>>
        System.out.println(t.getGeneric(0).resolve()); // Integer
        System.out.println(t.getGeneric(1).resolve()); // List
        System.out.println(t.getGeneric(1)); // List<String>;
        System.out.println(t.resolveGeneric(1, 0)); // String
    }

    private static void doDemo02(){
        ResolvableType resolvableType = ResolvableType.forClass(Demo03GenericTypeResolver.StringClass.class);
        ResolvableType superType = resolvableType.getSuperType();
        System.out.println("superType : " + superType);//superType : java.util.ArrayList<java.lang.String>
        ResolvableType superSuperType = superType.getSuperType();
        System.out.println("superSuperType : " + superSuperType);//superSuperType : java.util.AbstractList<java.lang.String>
        ResolvableType resolvableType2 = resolvableType.asCollection();
        System.out.println("resolvableType2 : " + resolvableType2);//resolvableType2 : java.util.Collection<java.lang.String>
        Class<?> resolveGenericClass = resolvableType2.resolveGeneric(0);
        System.out.println("resolveGenericClass : " + resolveGenericClass );//resolveGenericClass : class java.lang.String
    }

}
