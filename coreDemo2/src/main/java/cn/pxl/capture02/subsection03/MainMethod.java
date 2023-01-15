package cn.pxl.capture02.subsection03;

import cn.pxl.capture02.common.ReadFile;
import cn.pxl.capture02.subsection03.entity.Person;

import java.io.*;

public class MainMethod {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //doReadResolve();
        //doCloneWithSerial();
    }

    public static void doReadResolve() throws IOException, ClassNotFoundException{
        //这里，利用单例模式创建了一个对象。将这个对象序列化为文件。
        ReadResolve instance = ReadResolve.getInstance();
        File fileByName = ReadFile.getFileByName("readResolve.dta");
        FileOutputStream fileOutputStream = new FileOutputStream(fileByName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(instance);

        //将序列化的单例对象反序列化后，发现已经不是原来的对象了。
        FileInputStream fileInputStream = new FileInputStream(fileByName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ReadResolve afterReadResolve = (ReadResolve)objectInputStream.readObject();
        System.out.println(instance == afterReadResolve);//false，单例对象已经改变。

        ReadResolve instance2 = (ReadResolve)afterReadResolve.readResolveMethod();
        System.out.println(instance2 == instance); //true , 返回了已经修正过的单例对象。
    }

    public static void doCloneWithSerial(){
        Person person = new Person();
        try {
            //利用序列化方式克隆一个新对象
            Person clonePerson = (Person)person.clone();
            System.out.println(person == clonePerson);  //false;Person不是单例的，克隆流新的对象。

            ReadResolve instance = ReadResolve.getInstance();
            ReadResolve cloneReadResolve = (ReadResolve)instance.clone();
            System.out.println(instance == cloneReadResolve); // false; ReadResolve 是单例的！但通过序列化克隆的方式，也可以产生新的对象
            cloneReadResolve = (ReadResolve) cloneReadResolve.readResolveMethod();//提供ReadResolve方法保证克隆的单例性
            System.out.println(cloneReadResolve == instance); //true;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
