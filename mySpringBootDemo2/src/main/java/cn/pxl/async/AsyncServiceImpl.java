package cn.pxl.async;

import cn.pxl.async.intf.AsyncService;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncServiceImpl implements AsyncService {

    //模拟生成报表的异步方法
    @SneakyThrows
    @Override
    //异步标识，方法异步调用
    @Async
    public void getGenerateReport() {
        System.out.println("开始异步生成报表");
        System.out.println("当前" + "线程：" + Thread.currentThread().getName() + "正在生成报表，5秒后生成完成");
        Thread.sleep(5000);
        System.out.println("异步生成报表结束");
    }
}
