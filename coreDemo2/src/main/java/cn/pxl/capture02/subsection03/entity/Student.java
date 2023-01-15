package cn.pxl.capture02.subsection03.entity;

import java.io.Serializable;

public class Student extends Person implements Serializable {
    private String className;
    private Cat oneCat;

    public Cat getOneCat() {
        return oneCat;
    }

    public void setOneCat(Cat oneCat) {
        this.oneCat = oneCat;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Student{" +
                "className='" + className + '\'' +
                '}';
    }

    public Student(String name, String age, String className) {
        super(name, age);
        this.className = className;
    }
}
