package cn.pxl.dependency.injection;

import cn.pxl.dependency.entity.UserHolder;
import cn.pxl.entity.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

//基于注解的setter注入
public class AnnotationSetterInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationSetterInjection.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean("userHolder"));
    }

    @Bean
    public UserHolder userHolder(User user1){
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user1);
        return userHolder;
    }

    @Bean
    public User user(){
        User user = new User();
        user.setUserName("annotationSetter");
        return user;
    }


}
