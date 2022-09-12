package cn.pxl.capture06.Subsection01Interface.interfaceDemo;

//接口
public interface MyInterface<E> {
    //一：接口中一定不会存在实例字段。
    //接口中的字段，默认是被 public static final 修饰的，这三个修饰符在接口中被省略了。
    int a = 20;//完整的形式：public static final a = 20;

    //二：接口中的方法，默认是被 public 修饰的。
    int getA();//完整的形式：public int getA(); 实现类的权限修饰符，不能比接口中的修饰符低级，也就是，实现类中的修饰符只能是 public。

    //三：接口中允许存在静态方法。可以通过 接口.静态方法 的形式调用。
    static int getIntRandom(){
        return (int) (Math.random() * 100);
    }

    //四：接口中允许定义默认方法。
    default int compareToOther(E element){
        return 0;
    }
    //默认方法可以调用其他方法
    int getSize();
    default boolean isEmpty(){
        return getSize() <= 0;
    }
}
