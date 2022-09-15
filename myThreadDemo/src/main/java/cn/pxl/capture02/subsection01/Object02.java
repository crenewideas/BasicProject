package cn.pxl.capture02.subsection01;

public class Object02 {
    public void doDemo(){

        System.out.println("thread name : " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread name : " + "end");

    }
}
