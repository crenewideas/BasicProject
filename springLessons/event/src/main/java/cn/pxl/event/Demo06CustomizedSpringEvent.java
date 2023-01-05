package cn.pxl.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.LinkedHashSet;
import java.util.Set;

//自定义事件
public class Demo06CustomizedSpringEvent {
    public static void main(String[] args) {
        doDemo(MySpringEventListener.class);


    }

    private static void doDemo(Class<? extends ApplicationListener<?>> myListenerClazz){
        //2.创建 current 应用上下文
        AnnotationConfigApplicationContext currentApplicationContext = new AnnotationConfigApplicationContext();
        currentApplicationContext.setId("current");
        currentApplicationContext.addApplicationListener(new MySpringEventListener());
        currentApplicationContext.refresh();
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
