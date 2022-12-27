package cn.pxl.dependency.injection;

import cn.pxl.dependency.entity.UserHolder;
import cn.pxl.entity.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

// api的构造器方式注入。
public class AnnotationApiSetterInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册 myUser bean
        applicationContext.register(AnnotationApiSetterInjection.class);
        //注册 beanDefinition
        applicationContext.registerBeanDefinition("userHolder",createUserHolder());
        applicationContext.refresh();
        System.out.println(applicationContext.getBean("userHolder"));
    }

    // beanDefinition 中定义属性的关联关系。
    private static BeanDefinition createUserHolder(){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        beanDefinitionBuilder.addConstructorArgReference("myUser");
        return beanDefinitionBuilder.getBeanDefinition();
    }

    @Bean
    private User myUser(){
        User user = new User();
        user.setUserName("pxl");
        return user;
    }

}
