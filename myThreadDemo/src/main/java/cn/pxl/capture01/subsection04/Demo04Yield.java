package cn.pxl.capture01.subsection04;
// 让当前线程放弃cpu的资源(时间片)。 Thread.yield();
// 高优先级的线程更容易被执行。优先级可以被子类继承。
public class Demo04Yield {
    public static void main(String[] args) {
        YieldThread yieldThread = new YieldThread();
        PriorityThread priorityThread = new PriorityThread();
        yieldThread.setPriority(1);
        //高优先级的线程更容易被执行。
        priorityThread.setPriority(10);
        yieldThread.start();
        priorityThread.start();
    }
}

class YieldThread extends Thread{
    @Override
    public void run() {
        //将cpu资源让给其他资源，会导致运行的时间变慢。
        for (int i = 0; i < 200; i++) {
            //让当前线程放弃cpu的资源。
            //Thread.yield();
            //当重新获取cpu资源后，再执行下边的输出操作。
            System.out.println(i);
        }
    }
}

//线程优先级
class PriorityThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
        Thread.yield();
        System.out.println(i);
    }
}

}

