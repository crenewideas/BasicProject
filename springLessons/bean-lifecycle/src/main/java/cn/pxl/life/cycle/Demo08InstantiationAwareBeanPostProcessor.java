package cn.pxl.life.cycle;

import cn.pxl.entity.SubUser;
import cn.pxl.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

//6.Spring Bean 实例化后阶段
public class Demo08InstantiationAwareBeanPostProcessor {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
//        defaultListableBeanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
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

    //实例化后处理
    private static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            //如果为 subUser，则对 subUser 执行操作
            if(ObjectUtils.nullSafeEquals("subUser",beanName)){
                SubUser subUser = new SubUser();
                subUser.setAddress("aaaaaa");
                // false subUser 对像不允许属性赋值（配置元信息 -> 属性信息）
                return false;
            }
            //返回true，对像允许属性赋值。
            return true;
        }
    }

}
