package cn.pxl.capture02.subsection03;

import java.io.*;

//可以利用序列化的方式对对象进行深拷贝。（地址发生变化，但属性的值不发生变化。）
public class CloneWithSerial implements Cloneable, Serializable {

    public Object clone() throws CloneNotSupportedException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);//将当前对象序列化到字节数组输出流中存储
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());//从字节输出流中获取到该对象的序列化信息
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            CloneNotSupportedException cloneNotSupportedException = new CloneNotSupportedException();
            cloneNotSupportedException.initCause(e);
            e.printStackTrace();
            throw cloneNotSupportedException;
        }
    }
}
