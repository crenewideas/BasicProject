package cn.pxl.life.cycle;

import cn.pxl.entity.SubUser;
import cn.pxl.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.util.ObjectUtils;

//12.Spring Bean 初始化阶段
//* @PostConstruct
//* 实现InitializingBean接口的afterPropertiesSet()方法
//* 自定义初始化方法
public class Demo12BeanInstantiation {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        //添加CommonAnnotationBeanPostProcessor，解决@PostConstruct回调问题
        defaultListableBeanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        xmlBeanDefinitionRegistry(defaultListableBeanFactory);
    }

    private static void xmlBeanDefinitionRegistry(DefaultListableBeanFactory defaultListableBeanFactory){
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String resource = "META_INF/xml-bean.xml";
        int definitionCount = xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        User user = defaultListableBeanFactory.getBean("user", User.class);
        System.out.println("definition Count：" + definitionCount);
        System.out.println("user :" + user);

    }

}
