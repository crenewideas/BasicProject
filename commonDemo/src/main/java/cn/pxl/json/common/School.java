package cn.pxl.json.common;

import java.io.Serializable;

public class School implements Serializable {
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "School{" +
                "teacherName='" + teacherName + '\'' +
                '}';
    }
}
