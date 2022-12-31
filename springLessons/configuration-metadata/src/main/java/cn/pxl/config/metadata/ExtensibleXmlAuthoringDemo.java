package cn.pxl.config.metadata;

import cn.pxl.entity.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

//Xml元素扩展示例
public class ExtensibleXmlAuthoringDemo {

    //TODO 解析XML时有bug
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        //加载自定义xml配置文件
        xmlBeanDefinitionReader.loadBeanDefinitions("META_INF/user-context.xml");
        User bean = defaultListableBeanFactory.getBean(User.class);
        System.out.println(bean);
    }

}
