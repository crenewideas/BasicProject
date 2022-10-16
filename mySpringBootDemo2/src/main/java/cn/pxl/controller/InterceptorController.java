package cn.pxl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/interceptor")
public class InterceptorController {

    @RequestMapping("/test")
    @ResponseBody
    public String doInterceptorTest(){
        System.out.println("执行业务方法");
        return "success";
    }

    //处理器之前的方法
    //执行业务方法
    //处理器之后的方法
    //处理器执行完成后的方法

}
