package cn.pxl.capture01.subsection02;

public class Demo01UseThread {

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        Thread runnableTest = new Thread(new RunnableTest()) ;

        threadTest.start();//threadTest -->Thread-0
        runnableTest.start();//runnableTest -->Thread-1
        System.out.println("main thread success");

    }

    //创建线程的两种方式：一：继承Thread
    private static class ThreadTest extends Thread{
        @Override
        public void run() {
            System.out.println("threadTest -->"+Thread.currentThread().getName());
        }
    }

    //创建线程的两种方式：二：实现Runnable
    private static class RunnableTest implements Runnable{
        @Override
        public void run() {
            System.out.println("runnableTest -->"+Thread.currentThread().getName());
        }
    }
}
