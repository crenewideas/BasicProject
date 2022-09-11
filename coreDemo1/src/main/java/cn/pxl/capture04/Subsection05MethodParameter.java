package cn.pxl.capture04;

import cn.pxl.capture04.common.Employee;

import java.time.LocalDate;

//方法参数
public class Subsection05MethodParameter {
    public static void main(String[] args) {
        demo01();
    }

    //方法可以修改按引用传递的变量的值，但不能修改按值传递的变量的值
    //java语言总是采用按值调用！
    private static void demo01(){
        //引用类型
        String name = "10";
        //基本类型的变量
        int age = 10;
        //引用类型
        Employee pxl1 = new Employee("pxl1", 30, LocalDate.now());
        doChange(name,age,pxl1);
        System.out.println(name);
        System.out.println(age);
        System.out.println(pxl1);
    }

    //java语言总是采用按值传递！引用数据类型传递的是地址的值。
    private static void doChange(String name,int age,Employee oldPxl){
        //name，age，oldPxl 初始化为 入参的一个副本！也就是 操作形参 name ，age ，oldPxl 时，操作的是实际参数的副本，
        // 实际参数不会发生变化（对于引用数据类型来说，引用数据类型的地址不会发生变化，但引用类型对象状态可能发生变化，因为形参的副本与实惨的副本指向的是同一个对象）。
        name = "11";
        age = 22;
        oldPxl = new Employee("pxl2", 40, LocalDate.now());
    }
}
