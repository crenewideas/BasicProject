package cn.pxl.capture01.subsection04;

//守护线程，为其他java线程服务。GC垃圾回收线程就是守护线程。
public class Demo05Daemon {
    public static void main(String[] args) {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程即将消失。");
    }
}

//守护线程，当其他线程都没有了，守护线程也自动消失。
class DaemonThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            System.out.println(i);
        }
    }
}