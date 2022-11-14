package cn.pxl.confing;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class MyAsyncConfig implements AsyncConfigurer {
    //定义线程池
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        threadPoolTaskExecutor.setCorePoolSize(10);
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(20);
        //线程队列最大线程数
        threadPoolTaskExecutor.setQueueCapacity(2000);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
