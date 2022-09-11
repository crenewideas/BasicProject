package cn.pxl.capture05.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private int salary;
    //只允许相同包下的子类访问。
    protected int age;

    public Employee(int salary) {
        this.salary = salary;
    }

    protected void testProtectMethod(){
        System.out.println("aaa");
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
