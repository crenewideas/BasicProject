package cn.pxl.capture02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class Demo01 {

    //-Xloggc:C:\Users\ligj\Downloads\gc.log
    //-verbose:gc -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:SurvivorRatio=8
    //java用于垃圾收集时的信息打印-verbose:gc
    //java的垃圾回收器在内存使用达到-Xms值的时候才会开始回收；这里Xms比Xmx的值大，因此不会进行垃圾回收。
    //Java虚拟机(JVM)的最大内存分配池可以用-Xmx表示。
    //Java堆内存溢出异常测试
    @Test
    public void heapOOM(){
        //执行结果:
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
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

    //虚拟机栈和本地方法栈内存溢出
    // -Xss:描述虚拟机栈的大小
    // -Xoss:描述本地方法栈的大小
    //-verbose:gc -Xss384k -XX:+PrintGCDetails -XX:SurvivorRatio=8
    @Test
    public void stackOOM(){
        //结果：
        //java.lang.StackOverflowError
        Demo02.addOne();
    }

    //java 7 + 中，将原本属于方法区中的常量池移到了堆中，可以向常量池中一直添加常量，达到堆的最大容量时将会报堆空间不足的异常。
    //虚拟机栈和本地方法栈内存溢出
    // -Xmx:描述堆的内存大小
    // -Xms:描述最小垃圾回收的内存大小
    //-verbose:gc -Xms2M -Xmx2M -XX:+PrintGCDetails -XX:SurvivorRatio=8
    @Test
    public void stackContentOOM(){
        //结果：
        //java.lang.StackOverflowError
        int i = 0;
        HashSet<String> hashSet = new HashSet<>();
        while (true){
            //intern:如果字面量不存在，那么就向常量池中添加。
            String intern = String.valueOf(i++).intern();
            hashSet.add(intern);
        }
    }
}
