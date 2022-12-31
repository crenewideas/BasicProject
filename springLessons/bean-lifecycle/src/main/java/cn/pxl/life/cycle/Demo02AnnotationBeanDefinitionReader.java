package cn.pxl.life.cycle;

import cn.pxl.entity.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

//生命周期第二阶段：bean元信息解析阶段
public class Demo02AnnotationBeanDefinitionReader {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        readBeanMetadataByAnnotatedBeanDefinitionReader(defaultListableBeanFactory);
    }

    //元信息解析：通过注解 ： AnnotatedBeanDefinitionReader
    private static void readBeanMetadataByAnnotatedBeanDefinitionReader(DefaultListableBeanFactory beanDefinitionRegistry){
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanDefinitionRegistry);
        int beforeCount = beanDefinitionRegistry.getBeanDefinitionCount();
        System.out.println("before Bean Definition Count : " + beforeCount);
        //普通 class 作为 Component 注册到 ioc容器。
        beanDefinitionReader.register(Demo02AnnotationBeanDefinitionReader.class);
        int afterCount = beanDefinitionRegistry.getBeanDefinitionCount();
        System.out.println("after Bean Definition Count : " + afterCount);
        System.out.println("added Bean Definition Count : " + (afterCount - beforeCount));
        //Bean name 名称来源：BeanNameGenerator/AnnotatedBeanNameGenerator
        System.out.println(beanDefinitionRegistry.getBean("demo02AnnotationBeanDefinitionReader"));
    }

}
