package cn.pxl;
import cn.pxl.capture03.subsection01.Cap3Demo01;
import cn.pxl.capture03.subsection01.Cap3Demo02;
import cn.pxl.capture03.subsection01.Cap3Demo03;
import cn.pxl.capture03.subsection01.Cap3Demo04;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class Capture03Test02
{

    // join() 方法，可以使当前线程无限期阻塞，等待另一个线程的执行完毕，再继续进行剩下的工作。
    @Test
    public void doDemo01(){

        //ThreadA 睡眠5秒
        //main线程在等待A线程的执行
        //A线程已执行完毕，main线程执行结束

        //1.多线程共享对象
        Thread thread = new Thread(() -> {
            System.out.println("ThreadA 睡眠5秒");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadA");
        thread.start();
        try {
            Thread.sleep(500);
            System.out.println("main线程在等待A线程的执行");
            thread.join();
            System.out.println("A线程已执行完毕，main线程执行结束");
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //测试join方法被打断
    @Test
    public void doDemo02(){
        Thread thread01 = new Thread(() -> {
            Thread thread02 = new Thread(() -> {
                System.out.println("ThreadB 睡眠5秒");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"ThreadB");
            thread02.start();
            try {
                //thread01 在等待 thread02 的执行完毕，但thread01被主线程提前打断了
                thread02.join();// java.lang.InterruptedException。被main线程打断了。
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadA");
        thread01.start();
        try {
            //主线程在程序执行四秒后，打断了 thread01 。 此时的 thread01 还在等待 thread02 的执行完毕。
            Thread.sleep(4000);
            thread01.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //join(long)方法，最多等待其他线程 long 毫秒，如果还没结束，当前线程就会变成可运行状态，获取到cpu的时间片后继续执行。
    @Test
    public void doDemo03(){
        Thread thread01 = new Thread(() -> {
            try {
                System.out.println("ThreadA 线程睡眠4秒钟");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadA");
        thread01.start();
        try {
            //等待3秒，没执行完主线程将继续获取锁并执行。
            thread01.join(3000);
            System.out.println("3秒后，ThreadA线程没有执行完毕，主线程也不继续等待，将继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void doDemo0000(){
        //1.多线程共享对象
        String lock = "";
        new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();

        new Thread(()->{
            synchronized (lock){
                lock.notifyAll();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "");
            }
        },"ThreadB").start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doDemo000(){
        //1.多线程共享对象
        String lock = "";
        new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();

        new Thread(()->{
            synchronized (lock){
                lock.notifyAll();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "");
            }
        },"ThreadB").start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
