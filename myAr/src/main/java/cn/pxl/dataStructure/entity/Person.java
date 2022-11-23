package cn.pxl.dataStructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person implements Comparable<Person>{
    private int age;

    @Override
    public int compareTo(Person otherPerson) {
        return age - otherPerson.age;
    }

}
