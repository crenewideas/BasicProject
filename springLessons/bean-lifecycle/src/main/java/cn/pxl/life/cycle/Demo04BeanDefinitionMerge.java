package cn.pxl.life.cycle;

import cn.pxl.entity.SubUser;
import cn.pxl.entity.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

//4.Spring BeanDefinition 合并阶段
//Return a merged BeanDefinition for the given bean name,
//merging a child bean definition with its parent if necessary.
//ConfigurableBeanFactory.getMergedBeanDefinition(); 唯一的实现：AbstractBeanFactory
public class Demo04BeanDefinitionMerge {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        xmlBeanDefinitionRegistry(defaultListableBeanFactory);
    }

    private static void xmlBeanDefinitionRegistry(DefaultListableBeanFactory defaultListableBeanFactory){
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String resource = "META_INF/xml-bean.xml";
        int definitionCount = xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        //RootBeanDefinition 不能 merge
        User user = defaultListableBeanFactory.getBean("user", User.class);
        //GenericBeanDefinition 可以 merge
        SubUser subUser = defaultListableBeanFactory.getBean("subUser", SubUser.class);
        System.out.println("user :" + user);
        System.out.println("subUser :" + subUser);
    }

}
