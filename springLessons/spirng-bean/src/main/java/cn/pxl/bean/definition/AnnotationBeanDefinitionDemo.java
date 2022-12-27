package cn.pxl.bean.definition;

import cn.pxl.entity.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

// Annotation Bean定义的几种方式：
//1.通过@Bean方式定义
//2.通过@Component方式定义
//3.通过@Import方式定义

//注册Bean定义的几种方式
//1.通过命名方式
//2.通过非命名方式
//3.通过配置类方式

//3.通过@Import方式定义
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        annotationConfigApplicationContextMethod();
    }

    public static void annotationConfigApplicationContextMethod() {
        //
        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext();
        //注册 Config；这个类代替了xml配置文件；会注册Config中所有的@Bean注解标注的类的对象。
        //annotationContext.register(Config.class);
        annotationContext.register(AnnotationBeanDefinitionDemo.class);
        //通过 命名方式注册 bean 定义
        userBeanDefinitionRegistry(annotationContext,"pxlngu1");
        //通过 非命名方式注册 bean 定义
        userBeanDefinitionRegistry(annotationContext);
        //注册完之后刷新，启动应用上下文
        annotationContext.refresh();

        //按照类型进行依赖查找
        Map<String, Config> configMap = annotationContext.getBeansOfType(Config.class);
        System.out.println("Config 的所有对象:"+configMap);
        Map<String, User> userMap = annotationContext.getBeansOfType(User.class);
        System.out.println("User 的所有对象:"+userMap);
        //在spring中不会重复注册已存在的bean。

    }

    //通过配置类方式进行注册
    //2.通过@Component方式定义
    @Component
    public static class Config {
        //注册方式一：@Bean 注解方式
        @Bean(name = {"userBean1","userBean2","userBean3"})
        public User user(){
            User user = new User();
            user.setAge(11);
            user.setName("pp");
            return user;
        }
    }

    public static void userBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry,String beanName){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name","hahah").addPropertyValue("age",111);
        if(StringUtils.hasText(beanName)){
            //1.通过命名方式注册bean定义
            beanDefinitionRegistry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        }else {
            //2.通过非命名方式注册bean定义
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),beanDefinitionRegistry);
        }
    }

    //1.通过命名方式注册bean定义
    public static void userBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry){
        userBeanDefinitionRegistry(beanDefinitionRegistry,null);
    }


}
