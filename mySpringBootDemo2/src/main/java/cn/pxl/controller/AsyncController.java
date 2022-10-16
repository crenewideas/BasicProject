package cn.pxl.controller;

import cn.pxl.async.intf.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    //开始异步生成报表
    //当前线程：http-nio-8081-exec-1执行主方法结束！
    //当前线程：ThreadPoolTaskExecutor-1正在生成报表，5秒后生成完成
    //异步生成报表结束

    @RequestMapping("/export")
    public String doExport(){
        //调用生成报表的异步方法
        asyncService.getGenerateReport();
        System.out.println("当前线程：" + Thread.currentThread().getName() + "执行主方法结束！");
        return "success";
    }

}
