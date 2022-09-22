package cn.pxl.capture03;

import cn.pxl.capture02.Demo02;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Subsection01 {

    //内存站位，一个对象占64K的内存大小
    static class OOMObject{
        public byte[] placeHolder = new byte[64 * 1024];
    }
    //-Xms100m -Xmx100m -XX:+UseSerialGC

    @Test
    @SneakyThrows
    public void fillHeap(){
        //局部变量，存在栈中。
        System.out.println("睡眠10秒钟");
        Thread.sleep(1000);
        ArrayList<OOMObject> oomObjects = new ArrayList<>();
        //以64k/50ms速度向堆中存放对象。
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(50);
            //每 new 一个新对象，就会向堆中存放该对象，大小是64k。
            oomObjects.add(new OOMObject());
        }
        System.out.println("睡眠3秒钟");
        Thread.sleep(3000);
        oomObjects = null;
        System.out.println("oomObjects对象已被清空，睡眠3秒");
        Thread.sleep(3000);
        System.gc();
    }


    //可以在jconsole页面线程栏查看当前线程的运行状态。
    @Test
    @SneakyThrows
    public void threadMonitor(){
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        //线程阻塞，等待输入
//        bufferedReader.readLine();
        String lockObj = new String("abc");
        //线程锁等待
        new Thread(()->{
            synchronized (lockObj){
                try{
                    this.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"testLockThread").start();
    }

    //死锁的检测：jconsole 页面线程栏可以检测死锁。
    @Test
    @SneakyThrows
    public void deathThreadMonitor(){
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        //线程阻塞，等待输入
//        bufferedReader.readLine();
        String lockA = new String("abc");
        String lockB = new String("bcd");
        //线程锁等待
        new Thread(()->{
            synchronized (lockA){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //2秒后threadA尝试获取lockB锁,此时lockB已经被threadB获取，threadA将等待。
                synchronized (lockB){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"threadA").start();

        Thread.sleep(500);

        new Thread(()->{
            synchronized (lockB){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //2秒后threadB尝试获取lockA锁，此时lockA锁已被threadA获取，将等待。
                synchronized (lockA){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"threadB").start();
        Thread.sleep(20000);
    }



}
