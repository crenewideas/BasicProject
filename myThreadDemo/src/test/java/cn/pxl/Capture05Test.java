package cn.pxl;

import cn.pxl.capture05.subsection01.MyTask;
import cn.pxl.capture05.subsection01.MyTask2;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Capture05Test {

    //定时任务基本逻辑
    @SneakyThrows
    @Test
    public void demo01(){
        long nowTime = System.currentTimeMillis();
        System.out.println("当前时间是：" + new Date(nowTime));
        long scheduleTime = nowTime + 3000L;
        Date scheduleDate = new Date(scheduleTime);
        System.out.println("计划时间是：" + scheduleDate);
        MyTask myTask = new MyTask();
        // 这里会新启动一个线程。
        Timer timer = new Timer();
        timer.schedule(myTask,scheduleDate);
        Thread.sleep(4000);
        timer.cancel();
        Thread.sleep(10000);
    }

    //一个 timer 多个 task 时，多个task运行在同一个线程中，他们会顺序执行。
    @SneakyThrows
    @Test
    public void demo02(){
        //当前时间是：Wed Sep 21 11:31:19 CST 2022
        //计划时间是：Wed Sep 21 11:31:22 CST 2022
        //当前任务执行了，时间是Wed Sep 21 11:31:22 CST 2022
        //当前任务执行了，时间是Wed Sep 21 11:31:27 CST 2022 (由于myTask执行了5秒才结束，myTask2开始的时间并不是4秒后，而是5秒后。)
        long nowTime = System.currentTimeMillis();
        System.out.println("当前时间是：" + new Date(nowTime));
        long scheduleTime = nowTime + 3000L;
        long scheduleTime2 = scheduleTime + 4000L;
        Date scheduleDate = new Date(scheduleTime);
        Date scheduleDate2 = new Date(scheduleTime2);
        System.out.println("计划时间是：" + scheduleDate);
        MyTask myTask = new MyTask();
        MyTask myTask2 = new MyTask();
        // 这里会新启动一个线程。
        Timer timer = new Timer();
        timer.schedule(myTask,scheduleDate);
        timer.schedule(myTask2,scheduleDate2);
        Thread.sleep(9000);
        timer.cancel();
        Thread.sleep(10000);
    }


    //指定日期之后，按指定的时间间隔周期，无线循环执行某任务
    @SneakyThrows
    @Test
    public void demo03(){
        //当前任务执行了，时间是Wed Sep 21 11:37:46 CST 2022
        //当前任务执行了，时间是Wed Sep 21 11:37:48 CST 2022
        //当前任务执行了，时间是Wed Sep 21 11:37:50 CST 2022
        //当前任务执行了，时间是Wed Sep 21 11:37:52 CST 2022
        //当前任务执行了，时间是Wed Sep 21 11:37:54 CST 2022

        Timer timer = new Timer();
        MyTask2 myTask2 = new MyTask2();
        timer.schedule(myTask2,new Date(System.currentTimeMillis() + 1000),2000);
        Thread.sleep(10000);
    }

    //TimerTask 中的cancel方法，用于将当前任务从队列中移除。
    @SneakyThrows
    @Test
    public void demo04(){
        //当前Task已被移除
        //虽然是定时执行，每2秒执行一次，但由于被移除了，所以只会执行一次。
        Timer timer = new Timer();
        MyTask2 myTask2 = new MyTask2();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前Task已被移除");
                this.cancel();
            }
        },new Date(System.currentTimeMillis() + 1000),2000);
        Thread.sleep(10000);
    }

    //Timer 中的cancel方法，取消全部任务。
    @SneakyThrows
    @Test
    public void demo05(){
        Timer timer = new Timer();
        TimerTask t1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("t1");
            }
        };
        TimerTask t2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("t2");
            }
        };
        timer.schedule(t1,new Date(System.currentTimeMillis() + 1000),300);
        timer.schedule(t2,new Date(System.currentTimeMillis() + 1000),300);
        Thread.sleep(3000);
        //当前timer中所有的Task都会被移除
        timer.cancel();
    }
}
