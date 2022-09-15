package cn.pxl.capture02.subsection01;


import java.io.Serializable;

public class ThreadObj02 implements Runnable {
    private Object02 object02;

    public ThreadObj02(Object02 object02) {
        this.object02 = object02;
    }

    public ThreadObj02() {
    }

    @Override
    public void run() {
        object02.doDemo();
    }
}
