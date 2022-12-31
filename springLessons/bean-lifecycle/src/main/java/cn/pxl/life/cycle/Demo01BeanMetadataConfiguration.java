package cn.pxl.life.cycle;

import cn.pxl.entity.User;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

//生命周期第一阶段：bean元信息配置阶段
//生命周期第二阶段：bean元信息解析阶段
public class Demo01BeanMetadataConfiguration {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        readBeanMetadataConfigurationByProperties(defaultListableBeanFactory);
    }


    //1.通过xml方式读取bean元信息，并通过XmlBeanDefinitionReader解析
    private static void readBeanMetadataConfigurationByXml(DefaultListableBeanFactory beanDefinitionRegistry){
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanDefinitionRegistry);
        String resource = "META_INF/xml-bean.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
    }

    //2.通过properties方式读取bean元信息，并通过PropertiesBeanDefinitionReader解析
    private static void readBeanMetadataConfigurationByProperties(DefaultListableBeanFactory beanDefinitionRegistry){
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanDefinitionRegistry);
        String resource = "META_INF/user.properties";
        int i = beanDefinitionReader.loadBeanDefinitions(resource);
        System.out.println("已加载的bean Definition 的数量：" + i);//已加载的bean Definition 的数量：1
        User user = beanDefinitionRegistry.getBean("user",User.class);
        System.out.println(user);//User(userName=pxlngu, passWord=user, age=22)
    }
}
