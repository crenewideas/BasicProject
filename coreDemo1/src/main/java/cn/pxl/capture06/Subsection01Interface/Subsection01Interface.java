package cn.pxl.capture06.Subsection01Interface;

import cn.pxl.capture06.Subsection01Interface.interfaceDemo.MyInterface;
import cn.pxl.capture06.Subsection01Interface.interfaceDemo.MyInterface02;

//接口冲突的解决方式:
//1.类中方法优先。
//2.如果是一个接口中的默认方法，与另外一个接口中的方法（无论是否有默认）同名，同参数类型，那么就必须在子类中覆盖这个方法，以解决冲突。
//3.如果两个接口中定义了同样的非 默认方法 的抽象方法，那么是不冲突的，可以不用覆盖。
public abstract class Subsection01Interface implements MyInterface, MyInterface02 {
    public static void main(String[] args) {

    }

    public static void demo01(){
    }

    @Override
    public int getA() {
        return 0;
    }

    @Override
    public int compareToOther(Object element) {
        return MyInterface.super.compareToOther(element);
    }


    @Override
    public boolean isEmpty() {
        return MyInterface.super.isEmpty();
    }
}
