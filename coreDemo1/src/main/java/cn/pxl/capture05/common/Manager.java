package cn.pxl.capture05.common;

import lombok.*;

//子类拥有比超类更多的功能。
//@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Manager extends Employee{
    private int bonus;

    public Manager(int bonus,int salary){
        //一 调用父类的有参数构造函数，对父类的对象初始化。
        //二    Manager中的所有方法，不能直接访问Employee中的私有变量，因此，要通过父类构造器初始化父类中的私有字段。
        //      supper构造器语句必须是第一条语句。
        //三 如果子类构造器，没有显式的调用父类的构造器，那么默认会调用超类的无参构造器。如果父类没有无参构造器那么会出错。

        //四 this和super
        //this ：1.表示隐式参数的引用 2.表示调用当前类的其他构造函数
        //super：1.表示调用父类的方法 2.表示调用父类的构造函数
        //      调用构造器的语句，只能作为另一个构造器的第一条语句出现，因此 this 和 super 的构造器语句不能同时出现在同一个
        super(salary);
        //父类中age是protected权限，允许子类访问。同样的，子类也可以访问父类中受保护的方法
        age = 3;
        //为当前创建的对象的奖金赋值。
        this.bonus = bonus;
    }

    //重写父类的获取薪水的方法，经理获取薪水的方式是 基本薪资➕奖金。
    @Override
    public int getSalary() {
        //只有Employee自身的方法，可以访问私有的Salary字段。Manager的方法不能直接访问。
        //super不是一个对象的引用，而this是当前对象的引用。
        return super.getSalary() + this.getBonus();
    }

    //调用父类的获取 基本薪资的方法，获取基本薪资。
    public int getSupperSalary(){
        return super.getSalary();
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
