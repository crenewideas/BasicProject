package cn.pxl.capture01.subsection04;

//暂停某一个线程，并不是打断某一个线程
//暂停后可以重新恢复运行

//当一个同步方法执行时，同步方法的线程被暂停，那么其他的线程如果调用了同一个同步方法，其他线程就会被阻塞。
public class Demo03SuspendAndResume {
    public static void main(String[] args) {

        SuspendAndResumeThread suspendAndResumeThread = new SuspendAndResumeThread();

        suspendAndResumeThread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        suspendAndResumeThread.suspend();
        //这里会被卡死。
        //原因：1.suspendAndResumeThread run中，System.out.println是同步方法，
        //这个同步方法在执行时，当前线程被暂停了，这时，主线程执行System.out.println这个方法时，也会阻塞。
        //因此 suspend() 到 resume() 之间，是不能用 System.out.println 方法的，否则主线程将永远卡到 System.out.println 方法中，再也执行不了 resume() 了。
        System.out.println("aaa");
        try {
            //System.out.println("线程被暂停1秒");
            Thread.sleep(1000);
            //System.out.println("线程暂停1秒结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        suspendAndResumeThread.resume();

    }

}

class SuspendAndResumeThread extends Thread{
    @Override
    public void run() {
        for (int s = 0; s < 10000; s++) {
            System.out.println(s);
        }
    }
}
