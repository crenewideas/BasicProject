package cn.pxl.capture04;

import cn.pxl.capture04.common.Employee;

import java.time.LocalDate;

//面向对象
public class Subsection01 {
    public static void main(String[] args) {
        demo02();
    }

    private static void demo01(){
        LocalDate nowDate = LocalDate.now();
        //访问器方法与更改器方法：plusDays 调用之后，没有更改 nowDate 的状态，而是返回了另外一个状态的新对象。
        //只访问对象，而不修改对象状态的方法也称为访问器方法
        //访问对象的同时，也修改了对象状态的方法称为更改器方法
        LocalDate localDate = nowDate.plusDays(1);

    }


    private static void demo02(){
        Employee pxl01 = new Employee("PXL01", 1000, LocalDate.of(2000, 12, 20));
        Employee pxl02 = new Employee("PXL02", 2000, LocalDate.of(2000, 12, 20));
        Employee pxl03 = new Employee("PXL03", 3000, LocalDate.of(2000, 12, 20));
        Employee[] employees = {pxl01,pxl02,pxl03};

        //raise everyone`s salary by 5 percent;raise 1000 salary for everyone
        for (Employee employee : employees) {
            //raiseSalary方法有两个参数，
            //  第一个参数是隐式参数：它的类型是 调用这个方法的对象的类型。
            //  第二个参数是显式餐素：位于调用方法后括号中的参数。
            //  隐式参数没有显示在声明的方法中，每一个方法中，关键字this代指隐式参数。
            employee.raiseSalary(1000);
        }

        for (Employee employee : employees) {
            //getSalary（）方法是访问器方法，由于访问的是实例字段的值，也称为字段访问器。
            System.out.println(employee.getSalary());
        }

    }



}
