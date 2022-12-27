package cn.pxl.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class InitialUserFactory implements UserFactorySpecialIntf, InitializingBean, DisposableBean {


    // 基于 PostConstruct 的初始化
    @PostConstruct
    public void init(){
        System.out.println("PostConstruct方法，InitialUserFactory 初始化中");
    }

    public void initUserFactory(){
        System.out.println("自定义初始化方法，InitialUserFactory 初始化中");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean 的afterPropertiesSet 方法执行，InitialUserFactory初始化后");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("PreDestroy注解实现的销毁方法");
    }

    //实现DisposableBean接口的销毁方法
    @Override
    public void destroy() throws Exception {
        System.out.println("实现DisposableBean接口的销毁方法");
    }

    //自定义销毁方法
    public void destroyUserFactory(){
        System.out.println("自定义的销毁方法");
    }
}
