package cn.pxl.capture02.subsection01;

import lombok.SneakyThrows;

public class Demo08 {
    int a =0;
    @SneakyThrows
    public void doDemo() {
        System.out.println("开始执行无需同步的方法");
        Thread.sleep(3000);
        System.out.println("无需同步的方法已执行完毕");
        synchronized (this){
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" +a);
                a ++;
            }
        }
    }
}
