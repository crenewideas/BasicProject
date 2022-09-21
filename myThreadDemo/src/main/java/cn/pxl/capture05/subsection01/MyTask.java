package cn.pxl.capture05.subsection01;

import lombok.SneakyThrows;

import java.util.Date;
import java.util.TimerTask;

public class MyTask extends TimerTask {
    @SneakyThrows
    @Override
    public void run() {
        System.out.println("当前任务执行了，时间是" + new Date(System.currentTimeMillis()));
        Thread.sleep(5000);
    }
}
