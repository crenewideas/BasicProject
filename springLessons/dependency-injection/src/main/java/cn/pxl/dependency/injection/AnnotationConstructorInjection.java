package cn.pxl.dependency.injection;

import cn.pxl.dependency.entity.UserHolder;
import cn.pxl.entity.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

//基于注解的constructor注入
public class AnnotationConstructorInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationConstructorInjection.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean("userHolder"));
    }

    @Bean
    public UserHolder userHolder(User user1){
        return new UserHolder(user1);
    }

    @Bean
    public User user(){
        User user = new User();
        user.setUserName("annotationConstructor");
        return user;
    }

}
