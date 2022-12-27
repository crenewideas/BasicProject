package cn.pxl.bean.name;

import cn.pxl.bean.definition.BeanDefinitionConstructDemo;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

//bean名称的生成规则
//1. DefaultBeanNameGenerator 类自动生成bean的名称
//2. AnnotationBeanNameGenerator 类自动生成bean的名称
public class NameGenerateDemo {

    public static void main(String[] args) {
        generateNameByDefaultBeanNameGenerator();
        generateNameByAnnotationBeanNameGenerator();
    }

    //1. DefaultBeanNameGenerator 类自动生成bean的名称
    private static void generateNameByDefaultBeanNameGenerator(){
        //获取spring默认生成bean名称的方式一:
        DefaultBeanNameGenerator defaultBeanNameGenerator = new DefaultBeanNameGenerator();
        //获取bean的定义
        BeanDefinition beanDefinition = BeanDefinitionConstructDemo.beanDefinitionBuilder();
        SimpleBeanDefinitionRegistry simpleBeanDefinitionRegistry = new SimpleBeanDefinitionRegistry();
        String generateBeanName = defaultBeanNameGenerator.generateBeanName(beanDefinition, simpleBeanDefinitionRegistry);
        System.out.println(generateBeanName);//cn.pxl.entity.User#0
    }

    //2. AnnotationBeanNameGenerator 类自动生成bean的名称
    private static void generateNameByAnnotationBeanNameGenerator(){
        AnnotationBeanNameGenerator annotationBeanNameGenerator = new AnnotationBeanNameGenerator();
        BeanDefinition beanDefinition = BeanDefinitionConstructDemo.abstractBeanDefinition();
        BeanDefinition annotationBeanDefinition = BeanDefinitionConstructDemo.generateAnnotationBeanDefinition();
        SimpleBeanDefinitionRegistry simpleBeanDefinitionRegistry = new SimpleBeanDefinitionRegistry();
        String generateBeanName = annotationBeanNameGenerator.generateBeanName(beanDefinition, simpleBeanDefinitionRegistry);
        String annotationGenerateBeanName = annotationBeanNameGenerator.generateBeanName(annotationBeanDefinition, simpleBeanDefinitionRegistry);
        //由于 beanDefinition：不属于 AnnotationBeanDefinition 类型，采用全类名截取首字母小写的方式返回了bean的名称。
        System.out.println(generateBeanName);// user
        //annotationGenerateBeanName：属于 AnnotationBeanDefinition 类型，采用全类名截取首字母小写的方式返回了bean的名称。
        System.out.println(annotationGenerateBeanName);// user

    }
}
