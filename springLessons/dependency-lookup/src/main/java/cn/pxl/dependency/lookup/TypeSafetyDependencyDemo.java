package cn.pxl.dependency.lookup;

import cn.pxl.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class TypeSafetyDependencyDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyDemo.class);
        applicationContext.refresh();
        beanFactoryGetBean(applicationContext);
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        objectFactoryGetObject(beanProvider);
        listableBeanFactoryTest(applicationContext);
        applicationContext.close();
    }

    // 单一类型查找
    // 容器中没有对应的bean时，会报错：NoSuchBeanDefinitionException。该方法是不安全的方法
    private static void beanFactoryGetBean(BeanFactory beanFactory){
        print("beanFactory.getBean",()->beanFactory.getBean(User.class));
    }

    // 单一类型查找
    // ObjectFactory.getObject方法也会在没有bean 时抛出：NoSuchBeanDefinitionException。该方法是不安全的方法
    private static void objectFactoryGetObject(ObjectFactory objectFactory){
        print("objectFactory.getObject",()->objectFactory.getObject());
    }

    // 单一类型查找
    // objectProvider.getIfAvailable 方法在没有bean时，不会抛出异常。该方法是安全的方法
    private static void objectProvider(ObjectProvider objectProvider){
        print("objectProvider.getIfAvailable",()->objectProvider.getIfAvailable());
    }

    // 集合类型查找，都是相对安全的方法
    // listableBeanFactory.getBeansOfType 方法在没有bean时，不会抛出异常。该方法是安全的方法
    private static void listableBeanFactoryTest(ListableBeanFactory listableBeanFactory){
        print("listableBeanFactory.getBeansOfType",()->listableBeanFactory.getBeansOfType(User.class));
    }

    private static void print(String message,Runnable runnable){
        try {
            System.out.println("message from : " + message);
            runnable.run();
        }catch (Exception e){
            System.out.println(e);
        }
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
