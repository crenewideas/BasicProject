package cn.pxl.capture02.subsection01;

import lombok.SneakyThrows;

public class Demo09 {
    int a =0;
    @SneakyThrows
    public void doDemo01() {
        System.out.println("当前线程是否持有指定类型锁对象" + Thread.holdsLock(Demo09.class));

        synchronized (this){
            System.out.println("当前线程是否持有指定类型锁对象" + Thread.holdsLock(Demo09.class));
            Thread.sleep(1000);
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" +a);
                a ++;
            }
        }
    }

    //不同线程调用synchronized (this)时，只有一个可以执行，其他的线程将阻塞。
    @SneakyThrows
    public void doDemo02() {
        synchronized (this){
            Thread.sleep(1000);
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" +a);
                a ++;
            }
        }
    }

    //不同线程调用synchronized (Demo09.class)时，只有一个可以执行，其他的线程将阻塞。
    //不同的锁，就是异步的。synchronized (Demo09.class) 和 synchronized (this) 互补影响。
    @SneakyThrows
    public void doDemo03() {
        synchronized (Demo09.class){
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" +a);
                a ++;
            }
        }
    }


}
