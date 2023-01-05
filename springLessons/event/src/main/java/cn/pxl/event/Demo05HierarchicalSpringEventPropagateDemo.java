package cn.pxl.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.LinkedHashSet;
import java.util.Set;

//层次性 Spring 事件传播示例
public class Demo05HierarchicalSpringEventPropagateDemo {
    public static void main(String[] args) {
//        doDemo(MyListenerForContextRefreshedEvent.class);
//        doDemo(MyListenerForApplicationContextEvent.class);
        doDemo(MyListenerForApplicationContextEventDeduplication.class);


    }

    private static void doDemo(Class<? extends ApplicationListener<?>> myListenerClazz){
        //1.创建 parent 应用上下文
        AnnotationConfigApplicationContext parentApplicationContext = new AnnotationConfigApplicationContext();
        parentApplicationContext.setId("parent");
        //注册 MyListener 到 parentApplicationContext
        parentApplicationContext.register(myListenerClazz);
        //2.创建 current 应用上下文
        AnnotationConfigApplicationContext currentApplicationContext = new AnnotationConfigApplicationContext();
        currentApplicationContext.setId("current");
        //3. current -> parent
        currentApplicationContext.setParent(parentApplicationContext);
        //注册 MyListener 到 currentApplicationContext
        currentApplicationContext.register(myListenerClazz);
        parentApplicationContext.refresh();
        currentApplicationContext.refresh();
        parentApplicationContext.close();
        currentApplicationContext.close();
    }

    //接收到spring应用上下文[ ID : parent  ] 的相关事件   parentApplicationContext
    //接收到spring应用上下文[ ID : current ] 的相关事件  currentApplicationContext
    //接收到spring应用上下文[ ID : current ] 的相关事件  currentApplicationContext
    private static class MyListenerForContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.printf("接收到spring应用上下文[ ID : %s ] 的相关事件\n",event.getApplicationContext().getId());
        }
    }

    //接收到spring应用上下文[ ID : parent  ] 的相关事件
    //接收到spring应用上下文[ ID : current ] 的相关事件
    //接收到spring应用上下文[ ID : current ] 的相关事件
    //接收到spring应用上下文[ ID : parent  ] 的相关事件
    //接收到spring应用上下文[ ID : current ] 的相关事件  之所以 current 只有一次传播，是因为其父容器已经关闭了，不会传递到父容器中。
    //如果将 currentApplicationContext.close() 先调用，则会有两次 current 接收。
    private static class MyListenerForApplicationContextEvent implements ApplicationListener<ApplicationContextEvent> {
        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            System.out.printf("接收到spring应用上下文[ ID : %s ] 的相关事件\n",event.getApplicationContext().getId());
        }
    }

    //去除重复的层次性事件传播
    private static class MyListenerForApplicationContextEventDeduplication implements ApplicationListener<ApplicationContextEvent> {
        // 为了避免 MyListenerForApplicationContextEventDeduplication 注册两次，会有两个实例，这里需要用到静态字段。
        private static Set<ApplicationContextEvent> existEvent = new LinkedHashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if(existEvent.add(event)){
                System.out.printf("接收到spring应用上下文[ ID : %s ] 的相关事件\n",event.getApplicationContext().getId());
            }
        }
    }
}
