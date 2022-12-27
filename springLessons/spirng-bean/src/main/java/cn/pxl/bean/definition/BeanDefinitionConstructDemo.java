package cn.pxl.bean.definition;

import cn.pxl.entity.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

// BeanDefinition构建
//1 通过 BeanDefinitionBuilder
//2 通过 AbstractBeanDefinition 以及派生类
public class BeanDefinitionConstructDemo {

    public static void main(String[] args) {
    }

    //1 通过 BeanDefinitionBuilder 获取bean定义。
    public static BeanDefinition beanDefinitionBuilder(){
        //指定对哪一个对象进行bean定义的创建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name","pxl").addPropertyValue("age",18);
        //通过builder获取实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // BeanDefinition 并非bean的终态，可以自定义修改
        return beanDefinition;
    }

    //2 通过 AbstractBeanDefinition 以及派生类
    public static BeanDefinition abstractBeanDefinition(){
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置Bean的类型
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("name","pxl");
        propertyValues.addPropertyValue("age",18);
        genericBeanDefinition.setPropertyValues(propertyValues);
        return genericBeanDefinition;
    }

    //2 通过 AbstractBeanDefinition 以及派生类
    public static BeanDefinition generateAnnotationBeanDefinition(){
        //设置Bean的类型
        GenericBeanDefinition genericBeanDefinition = new AnnotatedGenericBeanDefinition(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("name","pxl");
        propertyValues.addPropertyValue("age",18);
        genericBeanDefinition.setPropertyValues(propertyValues);
        return genericBeanDefinition;
    }

}
