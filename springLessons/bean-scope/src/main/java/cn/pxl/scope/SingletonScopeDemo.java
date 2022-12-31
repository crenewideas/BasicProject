package cn.pxl.scope;

import cn.pxl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

public class SingletonScopeDemo {

    //集合类型bean的注入，单例对像只会存在一份相同的bean，原型对戏那个会存在一份不同于其他地方的bean（会新生成一个对象）。
    @Autowired
    private Map<String,User> users;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) //无论是依赖查找还是依赖注入，都是同一个对象
    public static User singletonUser(){
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //无论是依赖查找还是依赖注入，都是不同的对象
    public static User prototypeUser(){
        return createUser();
    }

    private static User createUser(){
        User user = new User();
        user.setUserName(String.valueOf(System.currentTimeMillis()));
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SingletonScopeDemo.class);
        applicationContext.refresh();
        Object singletonUser1 = applicationContext.getBean("singletonUser");
        Object singletonUser2 = applicationContext.getBean("singletonUser");
        Object prototypeUser1 = applicationContext.getBean("prototypeUser");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object prototypeUser2 = applicationContext.getBean("prototypeUser");
        System.out.println("singletonUser1 : "+singletonUser1);
        System.out.println("singletonUser2 : "+singletonUser2);
        System.out.println("prototypeUser1 : "+prototypeUser1);
        System.out.println("prototypeUser2 : "+prototypeUser2);
        System.out.println("singletonUser1 == singletonUser2 : " + (singletonUser1 == singletonUser2));//true
        System.out.println("prototypeUser1 == prototypeUser2 : " + (prototypeUser1 == prototypeUser2));//false
        applicationContext.close();
    }



}
