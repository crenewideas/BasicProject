package cn.pxl.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

//基于xml的setter注入
public class XmlSetterInjection {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String resource = "META_INF/xml-setter-dependency-injection.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        System.out.println(defaultListableBeanFactory.getBean("userHolder"));
    }
}
