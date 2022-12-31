package cn.pxl.config.metadata;

import cn.pxl.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.ObjectUtils;

//2,3 Spring Bean配置元信息
public class Demo01BeanConfigMetadata {
    public static void main(String[] args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("userName","pxllll");
        // BeanDefinition 的来源
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //附加属性，不影响bean的生成，实例化，属性赋值，初始化等阶段。
        beanDefinition.setAttribute("userName","pxl");
        //附加辅助bean来源标记。取时，通过 BeanMetadataElement.getSource();
        beanDefinition.setSource("source1");
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        //注册 beanDefinition 到容器中。
        defaultListableBeanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if(ObjectUtils.nullSafeEquals(beanName,"user")){
                    BeanDefinition bd = defaultListableBeanFactory.getBeanDefinition(beanName);
                    //从bean定义中取出 Attribute 值。
                    Object attributeUserName = bd.getAttribute("userName");
                    System.out.println("attribute User Name ：" + attributeUserName);
                    System.out.println("辅助信息source值，用于判断 beanDefinition 的来源 ：" + bd.getSource());//辅助信息source值，用于判断 beanDefinition 的来源 ：source1
                    User user = (User) bean;
                    // 取出 bd 中存放的 attribute ，并覆盖到原有的属性值。
                    user.setUserName((String) attributeUserName);
                    return user;
                }
                return bean;
            }
        });
        //这段代码先执行，回调方法后执行。
        defaultListableBeanFactory.registerBeanDefinition("user",beanDefinition);
        User bean = defaultListableBeanFactory.getBean(User.class);
        System.out.println(bean);
    }
}
