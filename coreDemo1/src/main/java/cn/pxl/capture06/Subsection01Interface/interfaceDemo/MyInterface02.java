package cn.pxl.capture06.Subsection01Interface.interfaceDemo;

public interface MyInterface02 {
    //这里与MyInterface接口有相同的方法。
    int getSize();
    default boolean isEmpty(){
        return getSize() <= 0;
    }
}
