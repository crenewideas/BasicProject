package cn.pxl.capture02.subsection03;

import cn.pxl.capture02.common.ReadFile;
import cn.pxl.capture02.subsection03.entity.Person;

import java.awt.geom.Point2D;
import java.io.*;

public class Transient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        writeTransientObject();
        readTransientObject();
    }


    //将 LabeledPoint 序列化为文件存储。
    private static void writeTransientObject() throws IOException, ClassNotFoundException {
        Point2D.Double aDouble = new Point2D.Double();
        aDouble.setLocation(3,4);
        LabeledPoint lable = new LabeledPoint("lable", aDouble);
        FileOutputStream fileOutputStream = new FileOutputStream(ReadFile.getFileByName("transient.dta"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        lable.writeObject(objectOutputStream);
        lable.writeExternal(objectOutputStream);
        objectOutputStream.close();
    }

    //读取序列化的文件，生成 LabeledPoint 对象。
    private static void readTransientObject() throws IOException, ClassNotFoundException {
        File fileByName = ReadFile.getFileByName("transient.dta");
        FileInputStream fileInputStream = new FileInputStream(fileByName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        LabeledPoint labeledPoint = new LabeledPoint();
        labeledPoint.readObject(objectInputStream);
        labeledPoint.readExternal(objectInputStream);
        System.out.println(labeledPoint);
    }

    //
    static class LabeledPoint extends Person implements Serializable ,Externalizable{
        private String lable;
        //Point2D.Double 是不可序列化的类；在写出时，可以写出点的横纵坐标。
        private transient Point2D.Double point;

        public LabeledPoint() {
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            lable = (String) objectInputStream.readObject();
            double x = objectInputStream.readDouble();
            double y = objectInputStream.readDouble();
            point = new Point2D.Double(x,y);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
            objectOutputStream.writeObject(lable);
            objectOutputStream.writeDouble(point.x);
            objectOutputStream.writeDouble(point.y);
        }

        public LabeledPoint(String lable, Point2D.Double point) {
            super("sanguo","44");
            this.lable = lable;
            this.point = point;
        }

        public String getLable() {
            return lable;
        }

        public void setLable(String lable) {
            this.lable = lable;
        }

        public Point2D.Double getPoint() {
            return point;
        }

        public void setPoint(Point2D.Double point) {
            this.point = point;
        }

        @Override
        public String toString() {
            return "LabeledPoint{" +
                    "name='" + super.getName() + '\'' +
                    ",age='" + super.getAge() + '\'' +
                    ",lable='" + lable + '\'' +
                    ", point=" + point +
                    '}';
        }

        //写出序列化的父类到文件中
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(super.getName());
            out.writeObject(super.getAge());
        }

        //从文件中读取父类的数据
        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            in.readObject();
            in.readObject();
        }
    }
}
