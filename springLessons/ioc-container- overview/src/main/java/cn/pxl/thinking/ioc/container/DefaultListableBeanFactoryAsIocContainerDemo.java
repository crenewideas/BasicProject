package cn.pxl.thinking.ioc.container;

import cn.pxl.thinking.ioc.dependency.lookup.DependencyLookUp;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

//利用 DefaultListableBeanFactory 类作为底层ioc容器的实现。
public class DefaultListableBeanFactoryAsIocContainerDemo {
    public static void main(String[] args) {
        //新建一个ioc容器，这个ioc容器实现了注册接口，因此可以被注册器读取。
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        //DefaultListableBeanFactory 实现了 BeanDefinitionRegistry 接口，因此可以作为参数传入 XmlBeanDefinitionReader
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        // XML 配置文件的路径："META_INF/dependency-injection.xml"。
        String xmlLocation = "META_INF/dependency-injection.xml";
        //这个方法返回的是bean定义加载的数量。
        int count = xmlBeanDefinitionReader.loadBeanDefinitions(xmlLocation);
        System.out.println("bean定义加载的数量："+count + "个");
        //依赖查找集合对象
        DependencyLookUp.lookUpBean(defaultListableBeanFactory);
    }
}
