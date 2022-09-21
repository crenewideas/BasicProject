package cn.pxl.capture04.subsection01;

import java.util.concurrent.locks.ReentrantLock;

public class Cap4Demo01 {

    private ReentrantLock reentrantLock = new ReentrantLock();

    public void demoTest(){
        reentrantLock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("demoTest01 - ThreadName:" + Thread.currentThread().getName() + ":" + (i+1));
        }
        reentrantLock.unlock();
    }

    public void demoTest2(){
        reentrantLock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("demoTest02 - ThreadName:" + Thread.currentThread().getName() + ":" + (i+1));
        }
        reentrantLock.unlock();
    }

}
