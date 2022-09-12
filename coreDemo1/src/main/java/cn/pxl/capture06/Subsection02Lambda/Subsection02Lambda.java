package cn.pxl.capture06.Subsection02Lambda;


import cn.pxl.capture06.common.Person;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Subsection02Lambda {
    public static void main(String[] args) {
        Person person = new Person();
        Person[] persons = {person};
        Arrays.sort(persons, Comparator.comparing(Person::getAge));
    }



}
