package cn.pxl.capture02;

import java.util.ArrayList;

public class Demo01 {
    //-Xloggc:C:\Users\ligj\Downloads\gc.log
    //-verbose:gc -Xms20M -Xmx10M -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:SurvivorRatio=8
    //java用于垃圾收集时的信息打印-verbose:gc
    //java的垃圾回收器在内存使用达到-Xms值的时候才会开始回收；这里Xms比Xmx的值大，因此不会进行垃圾回收。
    //Java虚拟机(JVM)的最大内存分配池可以用-Xmx表示。
    //Java堆内存溢出异常测试
    public static void main(String[] args) {
        Runnable runnable = new Runnable(){
            @Override
            public void run() {

            }
        };
        ArrayList<Runnable> runnables = new ArrayList<>();
        while (true){
            runnables.add(runnable);
        }
    }
}
