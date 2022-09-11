package cn.aop.test;

import cn.aop.AopConfig;
import cn.aop.service.UserService;
import cn.aop.service.impl.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        //这里要用接口接收类型，不能用具体的实现接收，否则会报错：userServiceI是代理类型，而不是具体的某类的类型。
        UserService userServiceImpl = context.getBean("userServiceI", UserService.class);
        userServiceImpl.saveUser();
    }
}
