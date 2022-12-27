package cn.pxl.thinking.ioc.dependency.lookup;

import cn.pxl.annotation.Sub;
import cn.pxl.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

//依赖查找的三种方式：名称、类型和注解
public class DependencyLookUp {
    public static void main(String[] args) {
        //配置xml，启动上下文
        //根据bean名称查找，分为实时查找和延时查找。

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META_INF/dependency-look-up.xml");
//        lookUpBean(beanFactory);
//        lazyLookUpBean(beanFactory);
//        lookUpBeanByType(beanFactory);
//        lookUpBeansByType(beanFactory);
        lookUpBeanByAnnotation(beanFactory);

    }

    //通过名称实时查找
    public static void lookUpBean(BeanFactory beanFactory){
        User user = (User)beanFactory.getBean("user");
        System.out.println("通过名称实时查找：" +user);
    }

    //通过名称延时查找
    public static void lazyLookUpBean(BeanFactory beanFactory){
        ObjectFactory<User> userObjectFactory = (ObjectFactory<User>)beanFactory.getBean("objectFactoryCreatingFactoryBean");
        User user = userObjectFactory.getObject();
        System.out.println("通过名称延迟查找：" + user);
    }

    public static void lookUpBeanByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("通过类型实时查找单个实例：" +user);
    }

    public static void lookUpBeansByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("通过类型实时查找多个实例集合为：" + userMap);
        }
    }

    //通过注解查找bean
    public static void lookUpBeanByAnnotation(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> userMap = listableBeanFactory.getBeansWithAnnotation(Sub.class);
            System.out.println("通过注解实时查找多个实例集合为：" + userMap);
        }
    }

}
