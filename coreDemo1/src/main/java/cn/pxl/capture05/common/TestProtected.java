package cn.pxl.capture05.common;

public class TestProtected {
    public static void main(String[] args) {

    }

    public void demo01(){
        Employee employee = new Employee();
        //protected:对本包和所有的子类可见，这里属于同一个包，所以属性是可见的。
        employee.age=20;
        //protected:对本包和所有的子类可见，这里属于同一个包，所以属性是可见的。
        employee.testProtectMethod();
    }

}
