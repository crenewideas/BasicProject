package cn.pxl.life.cycle;

import cn.pxl.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.util.ObjectUtils;

//14.Spring Bean 初始化完成阶段，调用 SmartInitializingSingleton.afterSingletonsInstantiated 方法。这个方法主要在 ApplicationContext 中被调用！
public class Demo14InitializingComplete {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        defaultListableBeanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        xmlBeanDefinitionRegistry(defaultListableBeanFactory);
    }

    private static void xmlBeanDefinitionRegistry(DefaultListableBeanFactory defaultListableBeanFactory){
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String resource = "META_INF/xml-bean.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        User user = defaultListableBeanFactory.getBean("user", User.class);
        System.out.println(user);
    }

    //初始化后的处理
    private static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            System.out.println("初始化后方法：postProcessAfterInitialization执行！");
            User user;
            if(ObjectUtils.nullSafeEquals("user",beanName)){
                user = (User) bean;
                user.setAge(114);
                return user;
            }
            return bean;
        }
    }

}
