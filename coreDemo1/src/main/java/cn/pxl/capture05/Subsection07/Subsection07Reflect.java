package cn.pxl.capture05.Subsection07;

import java.lang.reflect.*;


//反射
public class Subsection07Reflect {
    public static void main(String[] args) {
        int[] a = {1,2,3,4};

        int[] ints = (int[]) demo02(a, 3);
        System.out.println(ints);
    }

    private static void demo01(){
        //获取Class对象的三种方式：
        ClassDemo classDemo = new ClassDemo("ZSF");
        //1.对象.getClass();
        Class<? extends ClassDemo> getClassClass = classDemo.getClass();
        //2.Class.forName(包名.类名);
        Class<?> forNameClass;
        try {
            forNameClass = Class.forName("cn.pxl.capture05.Subsection07.ClassDemo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("全类名解析失败！");
        }
        //3.T.class
        Class<ClassDemo> classDemoClass = ClassDemo.class;
        //虚拟机会为每一个类管理一个唯一的 Class ，因此 可以利用 == 来判断两个Class是否相同。
        System.out.println(getClassClass == classDemoClass);//true
        try {
            Constructor<ClassDemo> constructor = classDemoClass.getConstructor();
            ClassDemo classDemo1 = constructor.newInstance();
            classDemo1.setName("xlp");
            //分析类的能力：分析类的属性、方法、构造器
            Field[] declaredFields = classDemoClass.getDeclaredFields();
            Method[] declaredMethods = classDemoClass.getDeclaredMethods();
            for (Field declaredField : declaredFields) {
                //设置访问权限为可访问
                declaredField.setAccessible(true);
                //获取字段名称
                String name = declaredField.getName();
                System.out.println(name);
                //获取当前字段的类型
                Class<?> type = declaredField.getType();
                System.out.println(type);
                //访问权限
                int modifiers = declaredField.getModifiers();
                System.out.println(Modifier.toString(modifiers));
                //方法.对象，实现对象中方法的取值。
                Object o = declaredField.get(classDemo1);
                System.out.println(name + " - " + o);
                //通过反射方法为对象属性设置值
                declaredField.set(classDemo1,"xlp2");
                System.out.println(declaredField.get(classDemo1));
            }
            for (Method declaredMethod : declaredMethods) {
                //获取方法名称
                System.out.println(declaredMethod.getName());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.out.println("没有无参构造器");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //使用反射编写泛型数组代码
    private static Object demo02(Object a,int newLength){
        Class<?> aClass = a.getClass();
        //判断是否是数组类型。
        if (!aClass.isArray()) {
            return null;
        }
        //是数组类型，那么获取数组的引用数据类型
        Class<?> componentType = aClass.getComponentType();
        //获取数组的长度
        int length = Array.getLength(a);
        Object newInstanceArray = Array.newInstance(componentType, length);
        System.arraycopy(a,0,newInstanceArray,0,Math.min(length,newLength));
        return newInstanceArray;
    }
}
