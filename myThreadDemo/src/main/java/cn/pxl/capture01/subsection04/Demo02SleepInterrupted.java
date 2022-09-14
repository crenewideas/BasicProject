package cn.pxl.capture01.subsection04;

//总结：interrupt()方法 与 sleep() 方法相遇，sleep()方法就会抛出InterruptedException。无论谁先谁后。
public class Demo02SleepInterrupted {
    public static void main(String[] args) {
        MyThreadSleep myThreadSleep = new MyThreadSleep();
        myThreadSleep.start();
        //1.myThreadSleep先休眠，在休眠过程中打断。
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThreadSleep.interrupt();

        //2.先打断 myThreadSleep ，再休眠，同样会抛出 InterruptedException 。

    }
}

class MyThreadSleep extends Thread{
    @Override
    public void run() {
        try {
            //2.先打断 myThreadSleep 再休眠的方式：
            // for (int i = 0; i < 10000; i++) {
            //     System.out.println(i);
            // }
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("当前线程正在sleep，被其他线程打断；或者当前线程在没有sleep时已经被打断。");
            e.printStackTrace();
        }
    }
}