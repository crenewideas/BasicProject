package cn.pxl.life.cycle;

import cn.pxl.entity.SubUser;
import cn.pxl.entity.User;
import cn.pxl.entity.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

//6.Spring Bean 实例化前阶段
public class Demo07InstantiationStrategy {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        xmlBeanDefinitionRegistry(defaultListableBeanFactory);
    }

    private static void xmlBeanDefinitionRegistry(DefaultListableBeanFactory defaultListableBeanFactory){
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String[] resource = {"META_INF/xml-bean.xml","META_INF/constructor-xml-bean.xml"};
        int definitionCount = xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        //RootBeanDefinition 不能 merge
        User user = defaultListableBeanFactory.getBean("user", User.class);
        SubUser subUser = defaultListableBeanFactory.getBean("subUser", SubUser.class);
        UserHolder constructUser = defaultListableBeanFactory.getBean("constructUser", UserHolder.class);
        System.out.println("definition Count：" + definitionCount);
        System.out.println("user :" + user);
        System.out.println("subUser :" + subUser);
        System.out.println("constructUser :" + constructUser);
    }
}
