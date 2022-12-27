package cn.pxl.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//通过 ObjectProvider 进行依赖查找
//@Configuration 这个注解是非必须的
public class ObjectProviderDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();
        lookUpByObjectProvider(applicationContext);
        applicationContext.close();
    }
    //通过 ObjectProvider 进行依赖查找
    private static void lookUpByObjectProvider(AnnotationConfigApplicationContext applicationContext){
        ObjectProvider<String> stringObjectProvider = applicationContext.getBeanProvider(String.class);
        stringObjectProvider.stream().forEach(System.out::println);
        //hello World!
        //this is message!
    }

    @Bean
    @Primary
    public String helloWorld(){
        return "hello World!";
    }

    @Bean
    public String message(){
        return "this is message!";
    }

}
