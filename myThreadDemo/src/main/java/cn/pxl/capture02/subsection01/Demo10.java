package cn.pxl.capture02.subsection01;

import lombok.SneakyThrows;

public class Demo10 {

    public static int a = 10;

    //静态方法同步，锁对象是当前类的Class。
    @SneakyThrows
    public synchronized static void doDemo(){
        System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
        Thread.sleep(1000);
        System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
    }

    @SneakyThrows
    public void doNotStaticDemo(){
        synchronized(Demo10.class){
            System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
            Thread.sleep(1000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
        }
    }

}
