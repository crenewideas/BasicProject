package cn.pxl.capture03;

import org.omg.CORBA.ObjectHolder;

public class Demo01 {

    //
    static class Test{
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj =  new ObjectHolder();
        void oof(){
            ObjectHolder localObj =  new ObjectHolder();
            System.out.println("done!");
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //-Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
    public static void main(String[] args) {
        Test test = new Test();
        test.oof();
    }

}
