package cn.pxl.dependency.injection;

import cn.pxl.dependency.annotation.InjectUser;
import cn.pxl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;

//自定义注解实现自动依赖注入
public class InjectByMyOwnAnnotation {

    //自定义的注解
    @InjectUser
    private User user;

    @Autowired
    private User user2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectByMyOwnAnnotation.class);
        applicationContext.refresh();
        InjectByMyOwnAnnotation bean = applicationContext.getBean(InjectByMyOwnAnnotation.class);
        User user = bean.user;
        User user2 = bean.user2;
        System.out.println(user);
        System.out.println(user2);
        System.out.println("user == user2 ? " + (user == user2));
    }

    //注册AutowiredAnnotationBeanPostProcessor。
    @Bean(name = AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    public static AutowiredAnnotationBeanPostProcessor myAutowiredAnnotationBeanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        LinkedHashSet<Class<? extends Annotation>> annotationSet = new LinkedHashSet<>(Arrays.asList(Autowired.class, InjectUser.class, Resource.class));
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationTypes(annotationSet);
        return autowiredAnnotationBeanPostProcessor;
    }

    @Bean
    public User user(){
        User user = new User();
        user.setUserName("user");
        return user;
    }
}
