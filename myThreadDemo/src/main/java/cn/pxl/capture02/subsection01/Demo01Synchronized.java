package cn.pxl.capture02.subsection01;

import lombok.SneakyThrows;

public class Demo01Synchronized {
    //实例变量
    private int i = 100;
    //同步方法，锁只针对多个线程调用 <同一个对象> 的 <doDemo> 时才有效。
    @SneakyThrows
    //将当前对象作为锁。
    public synchronized void doDemo(){
        while (i > 0){
            System.out.println(Thread.currentThread().getName() + "-->" + i);
            System.out.println("哈哈");
            Thread.sleep(100);
            i --;
            Thread.yield();
        }
    }


    //由于 synchronized 是对象锁，锁住的是对象，所以 多线程执行 <同一个对象> 的 doDemo 和 doDemo02 两个方法时，也是按顺序执行的。
    @SneakyThrows
    public synchronized void doDemo02(){
        while (i > 0){
            System.out.println(Thread.currentThread().getName() + "-->" + i);
            System.out.println("哈哈");
            Thread.sleep(100);
            i --;
            Thread.yield();
        }
    }
}
