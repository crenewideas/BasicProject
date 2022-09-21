package cn.pxl;
import cn.pxl.capture03.subsection01.Cap3Demo01;
import cn.pxl.capture03.subsection01.Cap3Demo02;
import cn.pxl.capture03.subsection01.Cap3Demo03;
import cn.pxl.capture03.subsection01.Cap3Demo04;
import cn.pxl.entity.User;
import org.apache.log4j.helpers.ThreadLocalMap;
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

    // join(long)与sleep(long)的区别，前者内部用wait(long)实现，所以在等待时会释放锁，而sleep(long)则不会释放锁。
    @Test
    public void doDemo04() throws InterruptedException {

        //B线程开始等待A线程执行完毕，这时B线程不会占用锁对象，C线程可以获取到锁。
        //当前线程：ThreadA睡眠3秒
        //B线程已等待A线程执行完毕，B线程执行完毕。
        //线程C在线程B处于join()状态时也可以获取到 lock 进行继续执行
        //线程C执行完毕

        //1.多线程共享对象
        String lock = "";
        String lock2 = "CCC";
        Thread threadA = new Thread(() -> {
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠3秒");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadA");

        Thread threadB = new Thread(() -> {
            //TODO 这里如果是lock锁，达不到join后 C 线程拿到锁继续执行的效果；只有 threadA 当做锁对象才会有 join 后C继续执行的效果，这是为什么。
            synchronized (threadA) {
                System.out.println("B线程开始等待A线程执行完毕，这时B线程不会占用锁对象，C线程可以获取到锁。");
                threadA.start();
                try {
                    //B线程开始等待A线程的执行完毕，调用join后会立即释放锁。
                    //更直白的话：线程B抢到 threadA 的锁，然后快速释放。
                    threadA.join(2000);
                    //2s后，threadB 线程将重新获取 threadA 的锁对象，获取到了再继续向下执行。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B线程已等待A线程执行完毕，B线程执行完毕。");
            }
        }, "ThreadB");
        threadB.start();

        //睡眠，目的是让C线程在B线程之后执行。
        Thread.sleep(500);

        Thread threadC = new Thread(() -> {
            synchronized (threadA) {
                System.out.println("线程C在线程B处于join()状态时也可以获取到 lock 进行继续执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程C执行完毕");
            }
        }, "ThreadC");
        threadC.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ThreadLocal 类
    // ThreadLocal 类的作用是将数据存放到当前线程对象的实例变量Map中。ThreadLocal本身并不存放数据，也不管理数据。
    // Thread类中的Map以 threadLocal对象为key，一个key对应一个value;确切的说是存放到了 ThreadLocalMap 中的 Entry 实例变量中。
    @Test
    public void doDemo05(){

        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        //判断当前线程的Map中有没有存放 key 为 stringThreadLocal 的值。
        if (stringThreadLocal.get() == null) {
            System.out.println("当前线程中没有值，添加一个值");
            stringThreadLocal.set("添加的值");
        }
        System.out.println(stringThreadLocal.get());
        System.out.println(stringThreadLocal.get());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //验证隔离性
    @Test
    public void doDemo06(){
        //当前线程：ThreadA存入aaa
        //当前线程：ThreadB：获取对象 aaa 的值为：null

        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        new Thread(()->{
            System.out.println("当前线程：" + Thread.currentThread().getName() + "存入aaa");
            stringThreadLocal.set("aaa");
        },"ThreadA").start();

        new Thread(()->{
           System.out.println("当前线程：" + Thread.currentThread().getName() + "：获取对象 aaa 的值为：" + stringThreadLocal.get());
        },"ThreadB").start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //验证继承性 ThreadLocal 写入的值不具有继承性。
    @Test
    public void doDemo07(){
        //当前线程：main存入aaa
        //当前线程：ThreadA取出aaa：null
        //ThreadA线程由main线程创建，可以说main线程是父线程，ThreadA线程是子线程。父线程中创建的值，子线程也获取不到。
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        System.out.println("当前线程：" + Thread.currentThread().getName() + "存入aaa");
        stringThreadLocal.set("aaa");
        new Thread(()->{
            System.out.println("当前线程：" + Thread.currentThread().getName() + "取出aaa：" + stringThreadLocal.get());
        },"ThreadA").start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //验证继承性 InheritableThreadLocal 写入的值具有继承性。
    @Test
    public void doDemo08(){
        //当前线程：main存入aaa
        //当前线程：ThreadA取出aaa：aaa
        //ThreadA线程由main线程创建，可以说main线程是父线程，ThreadA线程是子线程。父线程中用InheritableThreadLocal创建的值，子线程也可以获取到。
        InheritableThreadLocal<String> stringInheritableThreadLocal = new InheritableThreadLocal<>();
        System.out.println("当前线程：" + Thread.currentThread().getName() + "存入aaa");
        stringInheritableThreadLocal.set("aaa");
        new Thread(()->{
            System.out.println("当前线程：" + Thread.currentThread().getName() + "取出aaa：" + stringInheritableThreadLocal.get());
        },"ThreadA").start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //验证继承性 InheritableThreadLocal 写入的值具有继承性。
    //继承的实现 方式是 Thread 类中存在两个属性，分别表示 当前线程的变量 以及 继承来的变量。
    //ThreadLocal.ThreadLocalMap threadLocals = null;
    //ThreadLocal.ThreadLocalMap inheritableThreadLocals = null;
    //父线程创建子线程时，会将父线程中的 threadLocals 拷贝一份，并分配给子线程的 inheritableThreadLocals 中。
    //由于是拷贝过来的，所以创建好子线程类的对象之后，这个值就不会变了，即便父类的线程变量改变，子类的值也不会改变。
    @Test
    public void doDemo09(){
        //当前线程：main存入aaa
        //当前线程：ThreadA取出aaa：aaa
        //当前线程：ThreadA取出aaa：aaa
        //当前线程：ThreadA取出aaa：aaa
        //当前线程：ThreadA取出aaa：aaa
        //当前线程：ThreadA取出aaa：aaa
        //当前线程：ThreadA取出aaa：aaa
        //当前线程：ThreadA取出aaa：aaa
        //当前线程：ThreadA取出aaa：aaa
        //当前线程：ThreadA取出aaa：aaa
        //当前线程：ThreadA取出aaa：aaa
        //ThreadA线程由main线程创建，可以说main线程是父线程，ThreadA线程是子线程。父线程中用InheritableThreadLocal创建的值，子线程也可以获取到。
        InheritableThreadLocal<String> stringInheritableThreadLocal = new InheritableThreadLocal<>();
        System.out.println("当前线程：" + Thread.currentThread().getName() + "存入aaa");
        stringInheritableThreadLocal.set("aaa");
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "取出aaa：" + stringInheritableThreadLocal.get());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();
        //修改main线程 stringInheritableThreadLocal 变量值为 "bbb"，并不会影响 ThreadA 线程中继承的变量的值。
        try {
            Thread.sleep(2500);
            stringInheritableThreadLocal.set("bbb");
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //子线程改了值，不会影响主线程的值。
    @Test
    public void doDemo10(){
        //当前线程：main存入aaa
        //ThreadA线程由main线程创建，可以说main线程是父线程，ThreadA线程是子线程。父线程中用InheritableThreadLocal创建的值，子线程也可以获取到。
        InheritableThreadLocal<String> stringInheritableThreadLocal = new InheritableThreadLocal<>();
        System.out.println("当前线程：" + Thread.currentThread().getName() + "存入stringInheritableThreadLocal的值：aaa");
        stringInheritableThreadLocal.set("aaa");
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "取出stringInheritableThreadLocal：" + stringInheritableThreadLocal.get());
                if(i == 5){
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "将stringInheritableThreadLocal值修改为：" + "bbb");
                    stringInheritableThreadLocal.set("bbb");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();
        //修改main线程 stringInheritableThreadLocal 变量值为 "bbb"，并不会影响 ThreadA 线程中继承的变量的值。
        try {
            Thread.sleep(2500);
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "取出stringInheritableThreadLocal值为：" + stringInheritableThreadLocal.get());
            }
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //执行结果：
        //当前线程：main存入stringInheritableThreadLocal的值：aaa
        //当前线程：ThreadA取出stringInheritableThreadLocal：aaa
        //当前线程：ThreadA取出stringInheritableThreadLocal：aaa
        //当前线程：ThreadA取出stringInheritableThreadLocal：aaa
        //当前线程：ThreadA取出stringInheritableThreadLocal：aaa
        //当前线程：ThreadA取出stringInheritableThreadLocal：aaa
        //当前线程：ThreadA取出stringInheritableThreadLocal：aaa
        //当前线程：ThreadA将stringInheritableThreadLocal值修改为：bbb
        //当前线程：main取出stringInheritableThreadLocal值为：aaa
        //当前线程：ThreadA取出stringInheritableThreadLocal：bbb
        //当前线程：main取出stringInheritableThreadLocal值为：aaa
        //当前线程：ThreadA取出stringInheritableThreadLocal：bbb
        //当前线程：main取出stringInheritableThreadLocal值为：aaa
        //当前线程：ThreadA取出stringInheritableThreadLocal：bbb
        //当前线程：main取出stringInheritableThreadLocal值为：aaa
        //当前线程：ThreadA取出stringInheritableThreadLocal：bbb
        //当前线程：main取出stringInheritableThreadLocal值为：aaa
        //当前线程：main取出stringInheritableThreadLocal值为：aaa
        //当前线程：main取出stringInheritableThreadLocal值为：aaa
        //当前线程：main取出stringInheritableThreadLocal值为：aaa
        //当前线程：main取出stringInheritableThreadLocal值为：aaa
        //当前线程：main取出stringInheritableThreadLocal值为：aaa
    }


    @Test
    public void doDemo11(){

        InheritableThreadLocal<User> stringInheritableThreadLocal = new InheritableThreadLocal<>();
        System.out.println("当前线程：" + Thread.currentThread().getName() + "存入User name = PXL");
        stringInheritableThreadLocal.set(new User("PXL"));
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "取出User name = " + stringInheritableThreadLocal.get());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();
        //修改main线程 stringInheritableThreadLocal 变量值为 "bbb"，并不会影响 ThreadA 线程中继承的变量的值。
        try {
            Thread.sleep(2500);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "修改User name = LLT" );
            stringInheritableThreadLocal.get().setName("LLT");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //运行结果为：
        //当前线程：main存入User name = PXL
        //当前线程：ThreadA取出User name = User(name=PXL)
        //当前线程：ThreadA取出User name = User(name=PXL)
        //当前线程：ThreadA取出User name = User(name=PXL)
        //当前线程：ThreadA取出User name = User(name=PXL)
        //当前线程：ThreadA取出User name = User(name=PXL)
        //当前线程：main修改User name = LLT
        //当前线程：ThreadA取出User name = User(name=LLT)
        //当前线程：ThreadA取出User name = User(name=LLT)
        //当前线程：ThreadA取出User name = User(name=LLT)
        //当前线程：ThreadA取出User name = User(name=LLT)
        //当前线程：ThreadA取出User name = User(name=LLT)
    }

    @Test
    public void doDemo12(){

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
