package cn.pxl.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

// @EventListener
@EnableAsync
public class Demo03AnnotatedEventListener {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Demo03AnnotatedEventListener.class);
        //方式一：基于Spring接口，向 applicationContext 注册事件
        applicationContext.addApplicationListener((event)->{
            System.out.println("接收到spring相关事件：" + event);
        });
        applicationContext.refresh();
        applicationContext.start();
        applicationContext.close();
    }

//    @EventListener
//    public void onApplicationEvent(ApplicationEvent event){
//        System.out.println("@EventListener --> 接收到 Spring 事件 ：" + event);
//    }

    //方式二：基于@EventListener，向 applicationContext 注册事件
    @EventListener
    @Order(1)
    public void contextRefreshedEvent1(ContextRefreshedEvent event){
        System.out.println("@EventListener1 --> 接收到 Spring Refresh 事件 ：" + event);
    }

    @EventListener
    @Order(2)
    public void contextRefreshedEvent2(ContextRefreshedEvent event){
        System.out.println("@EventListener2 --> 接收到 Spring Refresh 事件 ：" + event);
    }

    @EventListener
    @Async
    public void contextRefreshedEventAsync(ContextRefreshedEvent event){
        System.out.println("@EventListener --> 异步接收到 Spring Refresh 事件 ：" + event);
    }

    @EventListener
    public void contextStartedEvent(ContextStartedEvent event){
        System.out.println("@EventListener --> 接收到 Spring Started 事件 ：" + event);
    }

    @EventListener
    public void contextClosedEvent(ContextClosedEvent event){
        System.out.println("@EventListener --> 接收到 Spring Closed 事件 ：" + event);
    }

    //还有一种事件是 ：contextStoppedEvent 应用上下文停止事件。
}
