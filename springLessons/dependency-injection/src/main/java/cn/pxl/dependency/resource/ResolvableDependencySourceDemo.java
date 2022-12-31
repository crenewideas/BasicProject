package cn.pxl.dependency.resource;

import cn.pxl.dependency.annotation.InjectUser;
import cn.pxl.dependency.injection.InjectByMyOwnAnnotation;
import cn.pxl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;

// 游离对象ResolvableDependency作为依赖来源
//ResolvableDependency只能用于类型方面的依赖注入，不能用于依赖查找。
public class ResolvableDependencySourceDemo {
    @Autowired
    private User user2;

    @Autowired
    private String helloWorld;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencySourceDemo.class);
        //定义一个回调，在refresh()方法执行时，注册游离对象。
        applicationContext.addBeanFactoryPostProcessor(beanFactory->{
            beanFactory.registerResolvableDependency(String.class,"Hello World!");
        });
        applicationContext.refresh();
        ResolvableDependencySourceDemo bean = applicationContext.getBean(ResolvableDependencySourceDemo.class);
        //游离对象 作为注入来源，注入了值。
        System.out.println(bean.helloWorld);
    }

    @Bean
    public User user(){
        User user = new User();
        user.setUserName("user");
        return user;
    }
}
