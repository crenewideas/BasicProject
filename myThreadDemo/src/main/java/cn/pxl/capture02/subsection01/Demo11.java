package cn.pxl.capture02.subsection01;

import lombok.SneakyThrows;

public class Demo11 {

    public static int a = 10;

    //同步代码块，锁对象是 String 类型的对象，需要考虑字符串常量池的影响。因为池中的对象是独一份的。
    @SneakyThrows
    public void doStringDemo01(){
        //从常量池中获取
        String a = "a";
        synchronized(a){
            System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
            Thread.sleep(1000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
        }
    }

    @SneakyThrows
    public void doStringDemo02(){
        //从常量池中获取
        String a = "a";
        synchronized(a){
            System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
            Thread.sleep(1000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
        }
    }

}
