package cn.pxl.capture02.subsection03;

import cn.pxl.capture02.common.ReadFile;
import cn.pxl.capture02.subsection03.entity.Cat;
import cn.pxl.capture02.subsection03.entity.Student;

import java.io.*;

public class ObjectStream {

    public static void main(String[] args) {
        try {
            objectStreamWriter();
        } catch (IOException e) {
            System.out.println("failed");
        }
    }

    private static void objectStreamWriter() throws IOException {
        FileOutputStream person = new FileOutputStream(ReadFile.getFileByName("Person"));
        PrintStream printStream = new PrintStream(person);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(printStream);
        Student student1 = new Student("zhangsss1", "32", "Libyan1");
        Student student2 = new Student("zhangsss2", "33", "Libyan2");
        Cat cat = new Cat();
        cat.setCatName("miao");
        student1.setOneCat(cat);
        student2.setOneCat(cat);
        //两个学生，公有一只猫咪，在序列化时，第一次遇到这只猫，会保存对象数据到输出流；第二次再遇到这只猫，那么不会保存这个对象到输出流，只会写出"与之前保存的序列号为X的猫的对象相同。"
        objectOutputStream.writeObject(student1);
        objectOutputStream.writeObject(student2);
        objectOutputStream.close();
    }

}
