package cn.pxl.capture02.subsection02;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

//long或者double类型，在32位电脑上，没有实现原子性，在 ++ 操作时，会被分为三个步骤操作，这三个步骤不互为原子性，会造成线程安全问题。

public class Demo06 {
    //原子性，不存在线程安全问题
    private volatile static long i = 0L;

    //不是原子性的，存在线程安全问题。
    private static long a = 0L;

    private static int anInt = 0;
    private volatile static int anVolatileInt = 0;

    //原子性的 int 类
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void doAddAtomic(){
        atomicInteger.addAndGet(1);
    }

    public static void doAdd(){
        for (int j = 0; j < 10000; j++) {
            i ++;
        }
    }

    public static void doSubtract(){
        for (int j = 0; j < 10000; j++) {
            Thread.yield();
            i --;
        }
    }

    public static void doAddA(){
        for (int j = 0; j < 10000; j++) {
            a ++;
        }
    }

    public static void doSubtractA(){
        for (int j = 0; j < 10000; j++) {
            a --;
        }
    }

    public static void doAddAnInt(){
        for (int j = 0; j < 10000; j++) {
            anInt ++;
        }
    }

    public static void doSubtractAnInt(){
        for (int j = 0; j < 10000; j++) {
            anInt --;
        }
    }

    public static void doAddAnVolatileInt(){
        for (int j = 0; j < 10000; j++) {
            anVolatileInt ++;
        }
    }

    public static void doSubtractAnVolatileInt(){
        for (int j = 0; j < 10000; j++) {
            anVolatileInt --;
        }
    }

    public static long getI(){
        return i;
    }

    public static long getA(){
        return a;
    }

    public static long getAnInt(){
        return anInt;
    }

    public static long getAnVolatileInt(){
        return anVolatileInt;
    }

    public static long getAtomicInt(){
        return atomicInteger.intValue();
    }
}
