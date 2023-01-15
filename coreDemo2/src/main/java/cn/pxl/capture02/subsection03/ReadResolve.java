package cn.pxl.capture02.subsection03;

import java.io.*;
import java.sql.SQLOutput;

public class ReadResolve extends CloneWithSerial implements Serializable {

    private String name;
    private static ReadResolve readResolve;
    private ReadResolve(String name){
        this.name = name;
    }

    public static ReadResolve getInstance(){
        if(readResolve == null){
            synchronized (ReadResolve.class){
                if(readResolve == null){
                    readResolve = new ReadResolve("彭笑良");
                }
            }
        }
        return readResolve;
    }

    public void writeObject(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(name);
    }

    public void readObject(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        objectInput.readObject();
    }

    //这个方法，在反序列化之后调用，用于修正因为反序列化导致的单例模式创建了多个对象的问题。
    protected Object readResolveMethod(){
        if("彭笑良".equals(name)) return getInstance();
        throw new RuntimeException("反序列化失败！");
    }

}
