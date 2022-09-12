package cn.pxl.capture05.Subsection01;

import cn.pxl.capture05.common.Employee;
import cn.pxl.capture05.common.Manager;

//public:对所有包下的所有类都可见
//private:对当前类可见
//protected:对当前类的子类 / 当前类所属的包下的所有类可见。
//缺省:当前类所属的包下的所有类可见。
public class Subsection01 {
    public static void main(String[] args) {
        demo01();
    }

    //一：继承
    private static void demo01(){
        Manager manager = new Manager(5000,1000);
        System.out.println(manager.getSalary());//子类重写父类的方法，返回薪资加奖金。
        System.out.println(manager.getSupperSalary());//子类调用父类，只返回基本薪资。
    }

    //二：多态
    private static void demo02(){
        //替换原则：程序中出现超类对象的的任何地方，都可以使用子类对象进行替换。
        //替换原则的另外一种表述形式是：程序中的超类对象的引用，可以指向任何其子类的对象。
        //在java语言中，对象变量是多态的，也就是对象变量遵循 替换原则。

        //父类引用的数组，其中可以引用子类的对象
        Employee[] employees = new Employee[2];

        //父类的对象
        Employee fat = new Employee(2000);
        //子类的对象
        Manager sub = new Manager(1000, 2000);

        employees[0] = fat;
        //父类的引用指向了子类的对象，是多态的体现。
        employees[1] = sub;

        //选择执行的方法的时候，需要区分是动态绑定还是静态绑定
        //静态绑定：调用的方法是被 private / static / final 修饰的方法。
        //  1.private 子类不能访问父类的 private方法，因此不会存在选择父类还是子类方法的问题，同一选择的都是父类的方法。
        //  2.static  静态方法，属于父类，这个方法是唯一的。
        //  3.final   final修饰的方法，是不能被覆盖的，因此不会存在选择父类还是子类方法的问题，同一选择的都是父类的方法。
        //            final修饰的类，是不能被继承的。如果类被声明为final，那么其中所有的方法也会被是为final修饰。
        //            final修饰的变量，是不能被修改的


    }

}
