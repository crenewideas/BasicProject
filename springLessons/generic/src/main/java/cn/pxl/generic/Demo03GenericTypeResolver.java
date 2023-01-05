package cn.pxl.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Demo03GenericTypeResolver {
    public static void main(String[] args) throws NoSuchMethodException {
        Method getStrMethod = Demo03GenericTypeResolver.class.getDeclaredMethod("getStr");
        // 生明类 Demo03GenericTypeResolver.class;返回某个类中某个方法的返回值类型。
        Class<?> genericType = GenericTypeResolver.resolveReturnType(getStrMethod, Demo03GenericTypeResolver.class);
        System.out.println("genericType : "+genericType); // genericType : class java.lang.String
        //常规类型不具备泛型参数类型，因此返回空。
        Class<?> argumentType = GenericTypeResolver.resolveReturnTypeArgument(getStrMethod, List.class);
        System.out.println("argumentType : "+argumentType);// argumentType : null

        Method getListMethod = Demo03GenericTypeResolver.class.getDeclaredMethod("getList");
        Class<?> getListReturnType = GenericTypeResolver.resolveReturnType(getListMethod, Demo03GenericTypeResolver.class);
        Class<?> getListArgumentTypeType = GenericTypeResolver.resolveReturnTypeArgument(getListMethod, List.class);
        System.out.println("getListReturnType : "+getListReturnType);   //getListReturnType : interface java.util.List
        //没有指定具体泛型的类型，因此也为空。
        System.out.println("getListArgumentTypeType : "+getListArgumentTypeType);//getListArgumentTypeType : null

        Method getStringListMethod = Demo03GenericTypeResolver.class.getDeclaredMethod("getStringList");
        Class<?> getStringListReturnType = GenericTypeResolver.resolveReturnType(getListMethod, Demo03GenericTypeResolver.class);
        Class<?> getStringListArgumentType = GenericTypeResolver.resolveReturnTypeArgument(getStringListMethod, List.class);
        System.out.println("getStringListReturnType : "+getStringListReturnType);   //getStringListReturnType : interface java.util.List
        //指定了具体泛型的类型，因此不为空。
        System.out.println("getStringListArgumentType : "+getStringListArgumentType);//getStringListArgumentType : class java.lang.String


        // TypeVariable 参数类型具体化。
        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringClass.class);
        System.out.println(typeVariableMap);


    }

    //常规类型不含泛型参数类型。
    private static String getStr(){
        return null;
    }

    //非常规类型可能会含有泛型参数类型。
    private static <E> List<E> getList(){
        return null;
    }

    //
    private static StringClass getStringList(){
        return null;
    }

    //非常规类型可能会含有泛型参数类型。这里StringClass指定了父类的泛型类型。
    public class StringClass extends ArrayList<String> {

    }
}
