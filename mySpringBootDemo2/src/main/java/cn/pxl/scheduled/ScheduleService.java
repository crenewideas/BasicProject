package cn.pxl.scheduled;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    int count1 = 1;
    int count2 = 2;

    //上一个任务开始到下一个任务开始的间隔
    //每十秒钟执行一次
    @Scheduled(fixedRate = 10000)
    //异步线程执行
    @Async
    public void doJob1(){
        System.out.println("每隔10秒钟执行一次！当前执行了第：" +count1 + "次");
        count1++;
    }

    //                秒 分 时 天 月 星期 年
    @Scheduled(cron = "0 0 0 ? * WED")//每周三 0时0分0秒执行
    //异步线程执行
    @Async
    public void doJob2(){
        System.out.println("每隔5秒钟执行一次！当前执行了第：" +count2 + "次");
        count2++;
    }

}
