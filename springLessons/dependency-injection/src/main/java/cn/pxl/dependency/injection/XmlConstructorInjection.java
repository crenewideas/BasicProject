package cn.pxl.dependency.injection;

import cn.pxl.dependency.entity.UserHolder;
import cn.pxl.entity.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//基于xml的constructor注入
public class XmlConstructorInjection {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String resource = "META_INF/xml-constructor-dependency-injection.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        System.out.println(defaultListableBeanFactory.getBean("userHolder"));
    }

}
