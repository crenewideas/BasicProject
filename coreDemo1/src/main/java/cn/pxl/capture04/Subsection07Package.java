package cn.pxl.capture04;
//静态导入，导入后，使用静态方法时，可以直接使用方法名，无需 类.方法名。
import cn.pxl.capture04.common.Employee;

import static java.lang.System.*;
public class Subsection07Package {
    public static void main(String[] args) {
        out.println("static import");
    }

    public static void demo01(){
        //一：PackageEntity 定义为缺省，只有common包下的类可以引用，这里不可以使用。
        //二：Employee 定义为public，所有包下的类都可以引用，这里可以使用。
        Employee employee = new Employee();

        //三：employee.testPackage() 定义为缺省，只有common包下的类可以引用，这里不可以使用。
        //四：employee.raiseSalary() 定义为public，所有包下的类都可以引用，这里可以使用。
        employee.raiseSalary(500);
    }
}
