package cn.pxl.capture04;

//对象构造
public class Subsection06ObjectConstruct {

    public static void main(String[] args) {
        System.out.println(new ObjectConstruct());
    }


}

class ObjectConstruct{
    //默认字段初始化：如果构造器中没有显式为成员变量赋值，那么初始化后的对象会赋值为默认值：引用类型的默认值为null，int 为 0 ，boolean 为 false；
    private int age;
    private String name;
    //成员变量的赋值方式一：声明时赋值。
    private String friend = "myFriend";
    private String idNo;
    private static int objectNo;
    //成员变量的赋值方式二：构造器赋值。
    public ObjectConstruct(String name) {
        //this是隐式参数，指代想要构造的对象。
        this.name = name;
        //age没有显式赋值，那么会被初始化为默认值 0 ；
    }

    //构造器重载
    public ObjectConstruct() {
        //name没有显式赋值，那么会被初始化为默认值null；
        //age没有显式赋值，那么会被初始化为默认值 0 ；
        //friend已经赋值，不会初始化为默认值。
    }

    public ObjectConstruct(String name, int age, String friend) {
        //this()代表在一个构造器中调用另外一个构造器构造对象。
        this();
    }

    //成员变量的赋值方式三：初始化块；程序执行时，首先对声明变量赋默认值（有值的则不赋默认值），然后执行初始化块中的初始化，最后执行构造函数。
    {
        friend = "hello";
        idNo = "1111111";
    }

    //静态变量初始化逻辑，可以在静态代码块中进行。类加载时，会进行静态字段的初始化。初始化时也是有赋默认值的逻辑。
    static {
        objectNo = (int) (Math.random() * 10000);
    }


    @Override
    public String toString() {
        return "ObjectConstruct{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", friend='" + friend + '\'' +
                ", idNo='" + idNo + '\'' +
                '}';
    }
}

