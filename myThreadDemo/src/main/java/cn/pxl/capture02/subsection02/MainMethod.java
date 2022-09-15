package cn.pxl.capture02.subsection02;

import lombok.SneakyThrows;

public class MainMethod {
    @SneakyThrows
    public static void main(String[] args) {
        Demo05 demo05 = new Demo05();
//        new Thread(demo05::doDemoWithVolatile).start();
//        Thread.sleep(1000);
//        demo05.setStopFlag(true);
//        System.out.println("已更新停止标识为true");

        //开始执行死循环
        //已更新停止标识为true

        //一：尽管 已经更新了 stopFlag = true 但是线程中的死循环仍然没有停止。
        //二：原因：stopFlag = true 会存在于 线程私有堆栈和公共堆栈 两个内存空间各一份，线程访问的是私有堆栈的stopFlag；其他线程更改的是公共堆栈的 stopFlag 。
        //三：volatile关键字的作用：强制让线程访问公共堆栈的变量。也就是所谓的可见性。（B线程修改的属性，A线程可以马上看到。如当前的例子：main修改的stopFlag，新线程能立马看到）。

        new Thread(demo05::doDemoWithVolatile).start();
        Thread.sleep(1000);
        demo05.setIsStopFlag(true);
        System.out.println("已更新停止标识为true");
        //开始执行死循环
        //已更新停止标识为true
        //死循环执行结束
        //成功结束。

        new Thread(demo05::doDemoWithoutVolatileAndWithSynchronized).start();
        Thread.sleep(1000);
        demo05.setStopFlag(true);
        System.out.println("已更新停止标识为true");
        //开始执行死循环
        //已更新停止标识为true
        //死循环执行结束
        //成功结束。
    }


}
