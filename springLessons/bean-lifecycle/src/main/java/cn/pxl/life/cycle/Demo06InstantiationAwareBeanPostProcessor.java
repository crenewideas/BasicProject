package cn.pxl.life.cycle;

import cn.pxl.annotation.Sub;
import cn.pxl.entity.SubUser;
import cn.pxl.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

//6.Spring Bean 实例化前阶段
public class Demo06InstantiationAwareBeanPostProcessor {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        xmlBeanDefinitionRegistry(defaultListableBeanFactory);
    }


    //子接口接口扩展父接口 BeanPostProcessor
    //InstantiationAwareBeanPostProcessor.postProcessBeforeInitialization
    private static void xmlBeanDefinitionRegistry(DefaultListableBeanFactory defaultListableBeanFactory){
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String resource = "META_INF/xml-bean.xml";
        int definitionCount = xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        //RootBeanDefinition 不能 merge
        User user = defaultListableBeanFactory.getBean("user", User.class);
        SubUser subUser = defaultListableBeanFactory.getBean("subUser", SubUser.class);
        System.out.println("definition Count：" + definitionCount);
        System.out.println("user :" + user);
        System.out.println("subUser :" + subUser);
        //subUser :SubUser{address='null'} User(userName=null, passWord=null, age=null)
        //在实例化之前，判断了 subUser ，并返回了空对象，因此原Bean被覆盖掉了
    }

    //postProcessBeforeInstantiation 作用：可以对实例化前的bean做处理。
    //相当于初始化前的拦截器
    private static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            //如果为 subUser，则对subUser 执行覆盖操作
            if(ObjectUtils.nullSafeEquals("subUser",beanName) && SubUser.class.equals(beanClass)){
                return new SubUser();
            }
            return null;
        }
    }

}
