package cn.pxl.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//自定义事件
public class Demo07AsyncEventHandler {
    public static void main(String[] args) {
        //2.创建 current 应用上下文
        AnnotationConfigApplicationContext currentApplicationContext = new AnnotationConfigApplicationContext();
        currentApplicationContext.setId("current");
        currentApplicationContext.addApplicationListener(new MySpringEventListener());
        currentApplicationContext.refresh();
        ApplicationEventMulticaster multicaster = currentApplicationContext.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        //判断是否是 SimpleApplicationEventMulticaster
        if(multicaster instanceof SimpleApplicationEventMulticaster){
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) multicaster;
            ExecutorService executorService = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-thread-pool"));
            simpleApplicationEventMulticaster.setTaskExecutor(executorService);
            multicaster.addApplicationListener((event -> {
                if(!executorService.isShutdown()){
                    executorService.shutdown();
                }
            }));
        }
        currentApplicationContext.publishEvent(new MySpringEvent("Hello world ! "));
        currentApplicationContext.close();
    }

    //1.扩展 ApplicationEvent
    private static class MySpringEvent extends ApplicationEvent {

        public MySpringEvent(String messages) {
            super(messages);
        }

        @Override
        public Object getSource() {
            return super.getSource();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    //2.自定义 SpringEventListener
    private static class MySpringEventListener implements ApplicationListener<MySpringEvent>{
        @Override
        public void onApplicationEvent(MySpringEvent event) {
            System.out.printf("接收到自定义事件： %s \n",event);
        }
    }

}
