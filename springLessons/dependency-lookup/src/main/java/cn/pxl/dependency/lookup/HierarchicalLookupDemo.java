package cn.pxl.dependency.lookup;

import cn.pxl.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

//层次性依赖查找
public class HierarchicalLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();
        hierarchicalLookup(applicationContext);
    }
    //通过 Hierarchical 进行层级依赖查找（类似于双亲委派）
    private static void hierarchicalLookup(AnnotationConfigApplicationContext applicationContext){
        //ConfigurableListableBeanFactory ：是 HierarchicalBeanFactory的子接口，同时又是ListableBeanFactory的子接口
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        //获取父BeanFactory
        System.out.println("当前beanFactory的 parent：" + beanFactory.getParentBeanFactory());
        //设置父BeanFactory
        beanFactory.setParentBeanFactory(getParentBeanFactory());
        System.out.println("当前beanFactory的 parent：" + beanFactory.getParentBeanFactory());
        User user = (User) applicationContext.getBean("user");
        //这个user是父容器中的bean，并不是子容器中的bean。
        System.out.println(user);
    }

    private static BeanFactory getParentBeanFactory(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("META_INF/dependency-lookup.xml");
        return defaultListableBeanFactory;
    }

}
