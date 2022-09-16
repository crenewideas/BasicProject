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
public class Capture03Test01
{
    // while 循环机制，来实现多个线程之间的通信。非常浪费cpu资源，而且不具有普适性，不能每一个通信都加一个while循环。
    @Test
    public void doDemo01(){

        //1.多线程共享对象
        Cap3Demo01 cap3Demo01 = new Cap3Demo01();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                cap3Demo01.addToList();
                System.out.println("添加了 ：" + (i +1) + "个元素");
                cap3Demo01.getSize();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();

        new Thread(()->{
            while (true){
                if(cap3Demo01.getSize() == 5){
                    System.out.println("线程ThreadA已经添加了5个元素，线程b即将推出！");
                    try {
                        throw new InterruptedException();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        },"ThreadB").start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 二：wait/notify 机制 拥有相同锁的线程，可以实现这个机制。
    @Test
    public void doDemo0201(){
        //没有持有锁，就调用wait，会抛出异常
        String a = "a";
        try {
            a.wait();//如果没有锁，那么会抛出 运行时异常。
            Thread.sleep(10000);//java.lang.IllegalMonitorStateException 非法对象监视器。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //需要等到执行notify()方法的线程执行完程序，退出 同步方法并释放锁对象之后，wait()才能重新拿到锁对象并继续执行。
    @Test
    public void doDemo02(){
        //syn上面
        //wait前
        //开始：wait time = 1663311069909
        //notify() 方法开始执行
        //notify() 方法执行结束
        //结束：wait time = 1663311071915
        //wait后

        String lock = new String();
        System.out.println("syn上面");
        //1.多线程共享对象
        new Thread(()->{
            System.out.println("wait前");
            synchronized (lock){
                System.out.println("开始：wait time = " + System.currentTimeMillis());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("结束：wait time = " + System.currentTimeMillis());
            }
            System.out.println("wait后");
        },"ThreadA").start();

        new Thread(()->{
            synchronized (lock){
                System.out.println("notify() 方法开始执行");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                lock.notify();
                System.out.println("notify() 方法执行结束");
            }
        },"ThreadB").start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //实现 size = 5 后退出线程
    @Test
    public void doDemo03(){

        //ThreadA等待其他线程唤醒！
        //已添加：1个数据
        //已添加：2个数据
        //已添加：3个数据
        //已添加：4个数据
        //已添加：5个数据
        //已添加：5个数据，开始唤醒
        //已添加：6个数据
        //已添加：7个数据
        //已添加：8个数据
        //已添加：9个数据
        //已添加：10个数据
        //唤醒线程已经执行完毕，等待线程：ThreadA已被唤醒！

        //1.多线程共享对象
        Cap3Demo01 cap3Demo01 = new Cap3Demo01();
        String lock = "";
        new Thread(()->{
            try {
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName() + "等待其他线程唤醒！");
                    lock.wait();
                    System.out.println("唤醒线程已经执行完毕，等待线程：" +Thread.currentThread().getName() + "已被唤醒！");
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"ThreadA").start();

        new Thread(()->{
            try {
                synchronized (lock){
                    for (int i = 0; i < 10; i++) {
                        cap3Demo01.addToList();
                        Thread.sleep(1000);
                        System.out.println("已添加："+cap3Demo01.getSize()+"个数据");
                        if(cap3Demo01.getSize() == 5){
                            System.out.println("已添加："+cap3Demo01.getSize()+"个数据，已通知其他线程唤醒；当前线程锁没有被释放");
                            lock.notify();//通知准备唤醒，但要等当前线程执行完，其他线程才能获取到锁对象。
                        }
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"ThreadB").start();
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // interrupt()方法 遇到 wait() 方法，wait()方法所在线程将会抛出 java.lang.InterruptedException 异常。
    @Test
    public void doDemo04(){
        String lock = "";
        //1.多线程共享对象
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println("当前线程已被 interruped 了，结束执行");
                    e.printStackTrace();
                }
            }
        }, "ThreadA");
        thread.start();
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            thread.interrupt();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // notify() 只能通知一个线程，多个wait状态的线程，只有一个会被通知到。唤醒顺序按照wait执行的先后顺序
    @Test
    public void doDemo05(){
        //已通知一个线程唤醒。其他线程将继续等待
        //当前线程：ThreadA已被唤醒！

        //1.多线程共享对象
        String lock = "";
        new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "已被唤醒！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();

        new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "已被唤醒！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadB").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            synchronized (lock){
                lock.notify();
                System.out.println("已通知一个线程唤醒。其他线程将继续等待");
            }
        },"ThreadC").start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // notifyAll() 通知所有wait状态的线程，他们都会被唤醒。具体的唤醒顺序由JVM的具体实现决定。
    @Test
    public void doDemo06(){

        //通知所有正在等待的线程唤醒。
        //当前线程：ThreadC已被唤醒！
        //当前线程：ThreadA已被唤醒！

        //1.多线程共享对象
        String lock = "";
        new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "已被唤醒！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();

        new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "已被唤醒！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadC").start();

        new Thread(()->{
            synchronized (lock){
                lock.notifyAll();
                System.out.println("通知所有正在等待的线程唤醒。");
            }
        },"ThreadB").start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // wait(long) 指定long毫秒后没被唤醒，也会自动唤醒。(持有锁之后，才会继续运行)
    @Test
    public void doDemo07(){

        //当前线程：ThreadA自动被唤醒

        //1.多线程共享对象
        String lock = "";
        new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait(1000);
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "自动被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //防止在 wait 之前通知的方式 ：增加一个 flag 判断，如果 notify 方法先执行，那么 wait 方法就不会进入
    @Test
    public void doDemo08(){

        //begin wait!
        //已通知wait方法
        //end wait!            这种情况是 notify 后执行。

        //或者：
        //已通知wait方法         这种情况是 notify 先执行。

        //1.多线程共享对象
        Cap3Demo02 cap3Demo02 = new Cap3Demo02("");
        new Thread(cap3Demo02.runnableOne,"ThreadA").start();
        new Thread(cap3Demo02.runnableTwo,"ThreadB").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doDemo00(){
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

    //生产者，消费者案例（单个生产者，单个消费者）
    @Test
    public void doDemo09(){
        //1.多线程共享对象
        String lock = "";
        Cap3Demo03 cap3Demo03 = new Cap3Demo03(lock);
        new Thread(cap3Demo03.producer1,"ThreadA").start();
        new Thread(cap3Demo03.consumer1,"ThreadB").start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
                                                                                                       //     wiat      wiat        wait        wait
    //生产者，消费者案例（多个生产者，多个消费者案列，可能出现假死。（生产者唤醒生产者，消费者唤醒消费者。生产者1 ——> 生产者2 ——> 消费者1 ——> 消费者2 ——> 生产者1 ——> 生产者2 假死））
    //上述情况中，所有的线程都变成等待状态了。
    //多个生产者，多个消费者案列，解决假死问题：notify 改为 notifyAll，通知所有线程都可以获取锁即可。
    @Test
    public void doDemo10(){
        //1.多线程共享对象
        String lock = "";
        Cap3Demo03 cap3Demo03 = new Cap3Demo03(lock);
        new Thread(cap3Demo03.producer1,"ThreadA").start();
        new Thread(cap3Demo03.producer2,"ThreadB").start();
        new Thread(cap3Demo03.consumer1,"ThreadC").start();
        new Thread(cap3Demo03.consumer2,"ThreadD").start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //使用notify()方法进行唤醒时,《一生产，多消费》 或者《一消费，多生产》都可能造成假死问题。
    //一生产多消费造成假死 ：生产者1 ——> 消费者1 ——> 消费者2 ——> 生产者1 ——> 消费者1 ——> 消费者2  此时，三个线程的状态都是wait状态。
    //解决方式是 调用 notifyAll方法，唤醒所有等待的线程。




    //管道流是比较特殊的流，可以用于线程之间的通信。
    //一个线程发送数据到输出管道，另外一个线程从输入管道读取数据。使用管道，可以实现不同线程之间的直接通信。
    //piped(管道)   可以进行线程通信的管道流类有四个：
    // 字符流：PipedInputStream、PipedOutputStream、
    // 字节流：PipedReader、PipedWriter

    //管道进行线程通信 ——> 字节流
    @Test
    public void doDemo12(){
        //1.多线程共享对象
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        //使得 输入管道流和输出管道流之间建立连接
        try {
            pipedInputStream.connect(pipedOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //1-1000 从 ThreadA 输出到管道中。
        new Thread(()->{
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "已将" + i + "输出到管道流中。");
                    pipedOutputStream.write(i);
                }
            }catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    //如果不最后close掉，那么最终会有个报错：IOException : Write end dead
                    pipedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("输出完毕");
        },"ThreadA").start();
        try {
            Thread.sleep(1000);
            byte[] bytes = new byte[20];
            int read = pipedInputStream.read(bytes);
            while (read != -1){
                System.out.println(Arrays.toString(bytes));
                read = pipedInputStream.read(bytes);
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pipedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //管道流是比较特殊的流，可以用于线程之间的通信。
    //管道进行线程通信 ——> 字符流
    @Test
    public void doDemo11() throws IOException, InterruptedException {
        PipedReader pipedReader = new PipedReader();
        PipedWriter pipedWriter = new PipedWriter();
        pipedReader.connect(pipedWriter);
        //新起线程 将字符送到 管道输出流中
        new Thread(()->{
            char a = '啊';
            char b = '波';
            char c = '次';
            char[] chars = {a,b,c};
            try {
                for (int i = 0; i < 40; i++) {
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "已第" + i + "次将[" +Arrays.toString(chars)+"]输出到管道流中。");
                    pipedWriter.write(chars);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pipedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();

        //当前线程从管道输入流中获取ThreadA的结果。
        Thread.sleep(2000);
        char[] chars = new char[5];
        int readCount = pipedReader.read(chars);
        while (readCount !=-1){
            System.out.println(chars);
            readCount = pipedReader.read(chars);
        }
        Thread.sleep(10000);

    }

    //实现双线程交替输出，协作完成任务
    @Test
    public void doDemo13(){
        //⭐️
        //🟥
        //⭐️
        //🟥
        //⭐️
        //🟥
        //⭐️
        //🟥
        //⭐️
        //🟥

        //1.多线程共享对象
        String lock = "";
        Cap3Demo04 cap3Demo04 = new Cap3Demo04(lock);
        new Thread(cap3Demo04.soutOne,"ThreadA").start();
        new Thread(cap3Demo04.soutTwo,"ThreadB").start();
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
