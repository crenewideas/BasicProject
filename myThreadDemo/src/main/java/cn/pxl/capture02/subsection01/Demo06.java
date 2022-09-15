package cn.pxl.capture02.subsection01;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class Demo06 {

    private String a;

    @SneakyThrows
    synchronized public void doDemo(){
        System.out.println("开始执行，休息两秒后抛出异常");
        Thread.sleep(2000);
        if("a".equals(a)){
            throw new RuntimeException("当前线程因异常中断！锁对象释放，其他线程可以获取当前锁对象。");
        }
        System.out.println("当前线程执行结束。");
    }
}
