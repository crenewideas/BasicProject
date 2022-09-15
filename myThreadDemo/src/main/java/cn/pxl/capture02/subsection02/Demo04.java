package cn.pxl.capture02.subsection02;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

public class Demo04{

    private static final String lockOne = new String("a");
    private static final String lockTwo = new String("b");
    //doLockOne 占着 lockOne 去要 lockTwo。
    @SneakyThrows
    public static void doLockOne(){
        System.out.println("lockOne ->lockTwo");
        //死锁的情况：lockOne锁内，等待 lockTwo锁的释放
        synchronized (lockOne){
            System.out.println("lockOne sleep 2 sec");
            Thread.sleep(80000);
            synchronized (lockTwo){
                System.out.println("lockTwo sleep 2 sec");
                Thread.sleep(80000);
            }
        }

    }

    //doLockTwo 占着 lockTwo 去要 lockOne。
    @SneakyThrows
    public static void doLockTwo(){
        System.out.println("lockTwo -> lockOne");
        //死锁的情况：lockTwo锁内，等待 lockOne锁的释放.
        synchronized (lockTwo){
            System.out.println("lockTwo sleep 2 sec");
            Thread.sleep(8000);
            synchronized (lockOne){
                System.out.println("lockOne sleep 2 sec");
                Thread.sleep(8000);
            }
        }
    }

}
