package cn.pxl.dependency.source;

import cn.pxl.entity.User;
import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;


//依赖来源测试类
//依赖注入时，有些非spring容器管理对象也会被当做依赖注入的来源。
@Data
public class DependencySourceDemo {

    //注入在 postProcessProperties 方法执行，早于 setter 注入，也早于 @PostConstruct。
    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @PostConstruct
    private void init(){
        System.out.println("applicationContext == beanFactory? " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getAutowireCapableBeanFactory ? " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("applicationContext == resourceLoader ? " + (applicationContext == resourceLoader));
        System.out.println("applicationContext == applicationEventPublisher ? " + (applicationContext == applicationEventPublisher));
    }

    //以上四个类型的对象，不可以被依赖查询，但可以被依赖注入。
    @PostConstruct
    private void initLookUp(){
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationContext.class);
        getBean(ApplicationEventPublisher.class);
    }


    private <T> T getBean(Class<T> tClass){
        try {
            return beanFactory.getBean(tClass);
        } catch (Exception e){
            System.out.println("当前class:" + tClass + "无法在Spring容器中查询到。");
            return null;
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);
        applicationContext.refresh();

    }

    @Bean
    public User user(){
        User user = new User();
        user.setUserName("annotationField");
        return user;
    }


}
