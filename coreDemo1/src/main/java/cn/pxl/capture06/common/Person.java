package cn.pxl.capture06.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person implements Comparable<Person>,Cloneable{
    private int age;

    //实现对象的比较，可以通过实现接口的方式。
    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.age,o.age);
    }
}
