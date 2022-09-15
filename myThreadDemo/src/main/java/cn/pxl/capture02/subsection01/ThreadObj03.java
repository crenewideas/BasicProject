package cn.pxl.capture02.subsection01;

public class ThreadObj03 implements Runnable{

    private Demo01Synchronized demo01Synchronized;

    public ThreadObj03() {
    }

    public ThreadObj03(Demo01Synchronized demo01Synchronized) {
        this.demo01Synchronized = demo01Synchronized;
    }

    @Override
    public void run() {
        demo01Synchronized.doDemo02();
    }
}
