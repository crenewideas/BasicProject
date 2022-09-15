package cn.pxl.capture02.subsection01;

public class ThreadObj01 implements Runnable{

    private Demo01Synchronized demo01Synchronized;

    public ThreadObj01() {
    }

    public ThreadObj01(Demo01Synchronized demo01Synchronized) {
        this.demo01Synchronized = demo01Synchronized;
    }

    @Override
    public void run() {
        demo01Synchronized.doDemo();
    }
}
