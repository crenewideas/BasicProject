package cn.pxl.capture04;

import java.time.LocalDate;

//静态字段与静态方法
public class Subsection04Static {

    //一：静态字段或者说是类字段，这种字段只属于类，而不属于某一个类的对象。
    //corpId，   指明当前类的          公司编号，属于类。这个静态字段归所有该类的对象共享。
    //定义一个静态变量：
    private static String corpId = "8888";

    //二：oneEmpId， 指明某个具体的对象的   员工编号，属于某个具体的对象。
    private String oneEmpId;

    //三：定义一个静态常量：
    private static final double PI = Math.PI;

    //四：静态方法：指的是不在对象上执行的方法。或者说：静态方法是没有隐式参数的方法。静态方法的执行，与它是哪个对象调用毫无关系（所以静态方法一般用类调用）。
    //由于没有隐式参数，静态方法不能访问实例变量；静态方法可以访问类变量。
    public static String getCorpId(){
        //使用静态工厂方法创建对象
        LocalDate now = LocalDate.now();
        //访问类变量
        return corpId;
    }

    //静态方法使用的时机；
    //1.方法不需要访问对象的状态（实例变量）。（没有this隐式参数）
    //2.方法只访问静态字段（如 getCorpId()）。
    //3.使用静态工厂方法创建对象：如getCorpId()中的创建方式。


}
