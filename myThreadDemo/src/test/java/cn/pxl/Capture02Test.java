package cn.pxl;
import cn.pxl.capture02.subsection02.Demo04;
import cn.pxl.capture02.subsection02.Demo06;
import cn.pxl.capture02.subsection02.MainMethod;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Capture02Test
{
    @Test
    public void doDemo00(){

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //死锁的测试
    @Test
    @SneakyThrows
    public void doDemo04(){

        new Thread(Demo04::doLockOne).start();
        Thread.sleep(2000);
        new Thread(Demo04::doLockTwo).start();

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    @SneakyThrows
    public void doDemo06(){

//        new Thread(Demo06::doAdd).start();
//        new Thread(Demo06::doSubtract).start();
//        Thread.sleep(1000);
//        System.out.println(Demo06.getI());// 0，线程安全。

        new Thread(Demo06::doAddA).start();
        new Thread(Demo06::doSubtractA).start();
        Thread.sleep(2000);
        System.out.println(Demo06.getA());//

        new Thread(Demo06::doAddAnInt).start();
        new Thread(Demo06::doSubtractAnInt).start();
        Thread.sleep(2000);
        System.out.println(Demo06.getAnInt());//

        new Thread(Demo06::doAddAnVolatileInt).start();
        new Thread(Demo06::doSubtractAnVolatileInt).start();
        Thread.sleep(2000);
        System.out.println(Demo06.getAnVolatileInt());//

        new Thread(Demo06::doAddAtomic).start();
        new Thread(Demo06::doAddAtomic).start();
        Thread.sleep(2000);
        System.out.println(Demo06.getAtomicInt());// 0，线程安全。

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
