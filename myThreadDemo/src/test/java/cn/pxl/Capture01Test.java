package cn.pxl;

import cn.pxl.capture02.subsection01.*;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Capture01Test
{
    @Test
    public void doDemo01(){
        Object02 object02 = new Object02();
        ThreadObj02 threadObj01 = new ThreadObj02(object02);
        ThreadObj02 threadObj02 = new ThreadObj02(object02);

        Thread thread = new Thread(threadObj01);
        Thread thread1 = new Thread(threadObj02);

        thread.start();
        thread1.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void doDemo02(){

        //当前要执行同步方法的对象，如果多个线程调用这同一个对象的同步方法，那么可以保证线程是安全的。
        Demo01Synchronized demo01Synchronized = new Demo01Synchronized();
        ThreadObj01 threadObj01 = new ThreadObj01(demo01Synchronized);
        ThreadObj01 threadObj02 = new ThreadObj01(demo01Synchronized);
        Thread thread = new Thread(threadObj01);
        Thread thread1 = new Thread(threadObj02);
        thread1.start();
        thread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void doDemo03(){

        Demo01Synchronized demo01Synchronized = new Demo01Synchronized();
        ThreadObj01 threadObj01 = new ThreadObj01(demo01Synchronized);
        ThreadObj03 threadObj03 = new ThreadObj03(demo01Synchronized);
        //threadObj01 调用的是 demo()方法
        Thread thread = new Thread(threadObj01);
        //threadObj03 调用的是 demo02方法
        Thread thread1 = new Thread(threadObj03);
        //二者方法不同，但锁住的是 this 对象 ， 也就是 demo01Synchronized 对象，所以也是同步的。
        //demo01Synchronized 对象使用了 synchronized 关键字声明的非静态方法，所以 demo01Synchronized 就是锁对象。
        thread1.start();
        thread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //脏读：读取实例变量时，值已经被其他线程修改
    @Test
    @SneakyThrows
    public void doDemo04(){
        Demo04 demo04 = new Demo04();
        Demo04SetValue demo04SetValue = new Demo04SetValue(demo04);//这个线程修改值
        Demo04GetValue demo04GetValue = new Demo04GetValue(demo04);//这个线程将读取到未被完全修改完的值（userName changed ,passWord not changed）。
        Thread thread = new Thread(demo04SetValue);
        Thread.sleep(100);
        Thread thread1 = new Thread(demo04GetValue);
        thread.start();
        thread1.start();//userName =aaa;passWord = B
        Thread.sleep(3000);
    }

    //解决脏读的方式：读取方法设置为同步方法。
    @Test
    @SneakyThrows
    public void doDemo042(){
        Demo04 demo04 = new Demo04();
        Demo04SetValue demo04SetValue = new Demo04SetValue(demo04);//这个线程修改值
        //这个线程中执行读取，也是同步等待的，不会出现脏读
        Demo04GetValueSynchronized demo04GetValue = new Demo04GetValueSynchronized(demo04);
        Thread thread = new Thread(demo04SetValue);
        Thread.sleep(100);
        Thread thread1 = new Thread(demo04GetValue);
        thread.start();
        thread1.start();//读取的是正确的 //userName =aaa;passWord = B
        Thread.sleep(6000);
    }

    //锁重入：一个线程得到了某个对象的锁，再次请求此对象的锁，也可以得到（因为没有被释放掉）。
    //同一个对象的同步方法内，调用另外一个同步方法， 另外一个同步方法也可以获取到锁。
    //理解：锁可重入就是某个线程可以多次获自己的锁对象。



    //锁可重入。子类同步方法调用父类的同步方法，也依然遵循锁重入原理，父类方法可以获取到锁对象。
    @Test
    @SneakyThrows
    public void doDemo05(){
        Demo05 demo05 = new Demo05();
        Demo05Thread demo05Thread = new Demo05Thread(demo05);
        new Thread(demo05Thread).start();
        //service1  获取到 this 锁
        //service2  获取到 this 锁 ，也可以获取到
        //service3  获取到 this 锁 ，也可以获取到
        Thread.sleep(6000);
    }

    //抛出异常后，锁对象会被释放掉。其他线程可以获取到锁对象。
    //suspend 方法 和 sleep 方法的调用，不会释放锁。
    @Test
    @SneakyThrows
    public void doDemo06(){
        Demo06 demo06 = new Demo06("a");
        Demo06Thread demo06Thread = new Demo06Thread(demo06);
        Demo06Thread demo06Thread2 = new Demo06Thread(demo06);
        new Thread(demo06Thread).start();
        new Thread(demo06Thread2).start();
        //service1  获取到 this 锁
        //service2  获取到 this 锁 ，也可以获取到
        //service3  获取到 this 锁 ，也可以获取到
        Thread.sleep(6000);
    }

    //重写方法，如果不使用 同步关键字，那么视为非同步方法使用。
    @Test
    @SneakyThrows
    public void doDemo07(){
        Demo07 demo07 = new Demo07("a");
        Demo07Thread demo07Thread = new Demo07Thread(demo07);
        Demo07Thread demo07Thread2 = new Demo07Thread(demo07);
        new Thread(demo07Thread).start();
        new Thread(demo07Thread2).start();
        Thread.sleep(6000);
    }

    //同步代码块，可以跳过线程安全的代码，只对非线程安全的代码加锁。
    @Test
    @SneakyThrows
    public void doDemo08(){
        Demo08 demo08 = new Demo08();
        Demo08Thread demo08Thread = new Demo08Thread(demo08);
        Demo08Thread demo08Thread2 = new Demo08Thread(demo08);
        new Thread(demo08Thread).start();
        new Thread(demo08Thread2).start();
        Thread.sleep(6000);
    }

    //不同线程访问同步代码块，只有一个线程会执行，其他的将同步阻塞。
    @Test
    @SneakyThrows
    public void doDemo09(){
        Demo09 demo08 = new Demo09();
        Demo09ThreadDemo1 demo08Thread = new Demo09ThreadDemo1(demo08);
        Demo09ThreadDemo2 demo08Thread2 = new Demo09ThreadDemo2(demo08);
        Demo09ThreadDemo3 demo08Thread3 = new Demo09ThreadDemo3(demo08);
        new Thread(demo08Thread).start();
        new Thread(demo08Thread2).start();
        new Thread(demo08Thread3).start();
        Thread.sleep(6000);
    }

    @Test
    @SneakyThrows
    //同步静态方法。
    public void doDemo10(){
        Demo10Thread demo10Thread = new Demo10Thread();
        Demo10Thread demo10Thread2 = new Demo10Thread();
        new Thread(demo10Thread).start();
        new Thread(demo10Thread2).start();
        //利用同步代码块，锁为当前类的Class对象，也一样会有同步的效果。
        new Thread(()->{
            new Demo10().doNotStaticDemo();
        }).start();
        //当前线程：Thread-0开始
        //当前线程：Thread-0结束
        //当前线程：Thread-1开始
        //当前线程：Thread-1结束
        Thread.sleep(6000);
    }

    //以类的Class对象作为锁对象时，多个线程调用《不同对象》的同步方法时，也会逐个执行。
    //无论是静态方法的同步还是同步代码块的同步， 只要锁对象为同一个类的Class，他们彼此就会同步执行。
    @Test
    @SneakyThrows
    //同步静态方法。
    public void doDemo10Tw0(){
        Demo10 demo10One = new Demo10();
        Demo10 demo10Two = new Demo10();
        //两个线程，分别调用了了两个不同对象的静态同步方法，由于静态方法锁的是类，所以两个线程会同步执行。
        new Thread(()->{
            demo10One.doNotStaticDemo();
        }).start();
        new Thread(()->{
            demo10Two.doNotStaticDemo();
        }).start();
        //当前线程：Thread-0开始
        //当前线程：Thread-0结束
        //当前线程：Thread-1开始
        //当前线程：Thread-1结束
        Thread.sleep(6000);
    }

    @Test
    @SneakyThrows
    //String 类型的锁，需警惕常量池的影响。
    public void doDemo11(){
        Demo11 demo11 = new Demo11();

        //两个线程，分别调用了相同对象的同步代码块，由于锁对象是String 类型，如果锁的是常量池中的相同字符串，那么会同步执行。
        new Thread(()->{
            demo11.doStringDemo01();
        }).start();
        new Thread(()->{
            demo11.doStringDemo02();
        }).start();
        //当前线程：Thread-0开始
        //当前线程：Thread-0结束
        //当前线程：Thread-1开始
        //当前线程：Thread-1结束
        Thread.sleep(6000);
    }
}
