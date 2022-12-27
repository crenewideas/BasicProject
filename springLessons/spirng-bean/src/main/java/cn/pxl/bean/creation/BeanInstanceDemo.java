package cn.pxl.bean.creation;

import cn.pxl.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//实例化 Bean 的几种方式：常规方式有三种：
//一：静态工厂方法
//二：Bean 工厂方法
//三：FactoryBean


public class BeanInstanceDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-creation.xml");
        staticMethod(beanFactory);
        beanFactoryCreateBean(beanFactory);
        factoryBeanCreateBean(beanFactory);
    }

    //一：静态工厂方法实例化Bean
    public static void staticMethod(BeanFactory beanFactory){
        User bean = (User)beanFactory.getBean("beanByStaticMethod");
        System.out.println(bean);
    }

    //二：Bean 工厂方法实例化Bean
    public static void beanFactoryCreateBean(BeanFactory beanFactory){
        User bean = (User)beanFactory.getBean("beanByUserFactory");
        bean.setName("beanFactoryCreate");
        System.out.println(bean);
    }

    //三：FactoryBean 实例化Bean
    public static void factoryBeanCreateBean(BeanFactory beanFactory) {
//        FactoryBean<User> userByFactoryBean = (FactoryBean<User>)beanFactory.getBean("$userByFactoryBean");
        User user = (User)beanFactory.getBean("userByFactoryBean");
        System.out.println(user);
    }


}
