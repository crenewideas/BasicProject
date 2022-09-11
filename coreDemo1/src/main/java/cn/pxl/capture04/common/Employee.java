package cn.pxl.capture04.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private String name;
    private int salary;
    private LocalDate hireDate;

    //方法，用于操作对象以及存取他们的实例字段，这里是为实例字段存入新的值。
    //方法可以访问调用这个方法对象的私有数据。
    //方法可以访问所属类型任何对象的私有字段。
    public void raiseSalary(int raiseSalary){
        this.salary = this.salary + raiseSalary;
    }

    //缺省的方法，只能被当前包下的类调用。
    void testPackage(){
        System.out.println("test success");
    }
}
