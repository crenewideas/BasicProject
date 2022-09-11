package cn.pxl.capture04.common;

//如果某个类中所有的方法都不会改变对象状态，那么这样的类就是不可变的类，可以用final修饰不可变类的字段（如这里的 String 是不可变类，那么可以用final修饰）。
//可变类的字段，用final修饰可能会造成混乱。
public class FinalField {
    //可以将实例字段定义为final，这样的字段必须在构造对象时进行初始化。
    private final String name;

    public FinalField() {
        //final实例变量，必须在构造对象是进行初始化
        name = "aaa";
    }

    public FinalField(String name) {
        this.name = name;
    }
}
