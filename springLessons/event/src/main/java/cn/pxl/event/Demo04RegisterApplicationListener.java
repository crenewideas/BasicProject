package cn.pxl.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.EventListener;

// Spring 事件发布器
// 方式一：ApplicationEventPublisher    依赖注入
// 方式二：ApplicationEventMulticaster  依赖注入和依赖查找
public class Demo04RegisterApplicationListener implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Demo04RegisterApplicationListener.class);
        //基于Spring接口，向 applicationContext 注册事件
        applicationContext.addApplicationListener((event)->{
            System.out.println("接收到spring相关事件：" + event);
        });
        applicationContext.refresh();
        applicationContext.close();

    }

    // 方式一：ApplicationEventPublisher    依赖注入
    //aware 回调 发布事件。
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello world from applicationEventPublisher !"){});
        //接收到spring相关事件：org.springframework.context.PayloadApplicationEvent : PayLoad事件
        applicationEventPublisher.publishEvent("Hello world from applicationEventPublisher !");

    }

}
