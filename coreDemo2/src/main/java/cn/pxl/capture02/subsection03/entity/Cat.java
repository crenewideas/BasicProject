package cn.pxl.capture02.subsection03.entity;

import java.io.Serializable;

public class Cat implements Serializable {
    private String catName;

    @Override
    public String toString() {
        return "Cat{" +
                "catName='" + catName + '\'' +
                '}';
    }

    public String getCatName() {

        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
