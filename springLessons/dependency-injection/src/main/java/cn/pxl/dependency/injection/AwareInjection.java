package cn.pxl.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//Aware接口回调进行注入
public class AwareInjection implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //当前类就是一个Spring bean；
        context.register(AwareInjection.class);
        context.refresh();
        System.out.println("beanFactory == context.getBeanFactory() : " + (beanFactory == context.getBeanFactory()));
        System.out.println("applicationContext == context : " + (applicationContext == context));
    }


    //实现 BeanFactoryAware 接口,Spring会回调这个方法注入 beanFactory
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInjection.beanFactory = beanFactory;
    }

    //实现 ApplicationContextAware 接口,Spring会回调这个方法注入 applicationContext
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInjection.applicationContext = applicationContext;
    }
}
