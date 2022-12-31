package cn.pxl.life.cycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

//3.Spring Bean 注册阶段
//注册接口为：BeanDefinitionRegistry 对应的唯一默认实现为：DefaultListableBeanFactory
public class Demo03BeanDefinitionRegistry {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        beanDefinitionRegistry(defaultListableBeanFactory);
    }

    private static void beanDefinitionRegistry(DefaultListableBeanFactory defaultListableBeanFactory){

    }

}
