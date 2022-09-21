package cn.pxl.capture04.subsection01;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Cap4Demo02 {

    ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition = reentrantLock.newCondition();
    boolean hasValue = false;

    Runnable runnable01 = new Runnable(){
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                reentrantLock.lock();
                //没有值就生产,有值就等待。
                while (hasValue){
                    condition.await();
                }
                System.out.println("生产一个值");
                Thread.sleep(1000);
                hasValue = true;
                //通知消费者进行消费
                condition.signal();
                reentrantLock.unlock();
            }
        }
    };

    Runnable runnable02 = new Runnable(){
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                reentrantLock.lock();
                //有值就消费，没有值就等待生产后进行消费
                while (!hasValue){
                    //通知完毕，已消费，这时候进行等待生产。
                    condition.await();
                }
                System.out.println("消费一个值");
                Thread.sleep(1000);
                hasValue = false;
                //已经没有值，通知生产者进行生产。
                condition.signal();
                reentrantLock.unlock();
            }
        }
    };

    public Runnable getRunnable01(){
        return runnable01;
    }

    public Runnable getRunnable02(){
        return runnable02;
    }


}
