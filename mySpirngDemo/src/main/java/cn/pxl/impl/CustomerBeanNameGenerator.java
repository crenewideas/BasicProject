package cn.pxl.impl;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

//参考BeanNameGenerator代码。
public class CustomerBeanNameGenerator implements BeanNameGenerator {

    private static final String COMPONENT_ANNOTATION_CLASSNAME = "org.springframework.stereotype.Component";
    @Override
    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
        //判断bean定义类是否是注解定义类
        if(beanDefinition instanceof AnnotatedBeanDefinition){
            AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) beanDefinition;
            //获取定义元信息
            AnnotationMetadata metadata = annotatedBeanDefinition.getMetadata();
            //获取定义注解信息
            Set<String> annotationTypes = metadata.getAnnotationTypes();
            for (String annotationType : annotationTypes) {
                Map<String, Object> annotationAttributesMap = metadata.getAnnotationAttributes(annotationType, false);
                //得到注解属性
                AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationAttributesMap);
                //判断annotationAttributes是否是null，及是否是@Component及衍生。
                if (attributes != null) {
                    //if(annotationType.equals(COMPONENT_ANNOTATION_CLASSNAME) || )
                }
            }

        }
        return null;
    }

}
