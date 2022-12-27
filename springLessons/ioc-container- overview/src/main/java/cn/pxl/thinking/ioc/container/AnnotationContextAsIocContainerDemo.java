package cn.pxl.thinking.ioc.container;

import cn.pxl.entity.User;
import cn.pxl.thinking.ioc.dependency.lookup.DependencyLookUp;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// AnnotationConfigApplicationContext 类作为底层ioc容器的实现

@Configuration
public class AnnotationContextAsIocContainerDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        //将当前类
        annotationConfigApplicationContext.register(AnnotationContextAsIocContainerDemo.class);
        //启动应用上下文，不启动则会抛出异常：AnnotationConfigApplicationContext@1c655221 has not been refreshed yet
        annotationConfigApplicationContext.refresh();
        //对annotationConfigApplicationContext进行依赖查找
        DependencyLookUp.lookUpBean(annotationConfigApplicationContext);
        //关闭应用上下文
        annotationConfigApplicationContext.close();
    }

    @Bean
    public User user() {
        User user = new User();
        user.setUserName("pxl");
        user.setAge(11);
        user.setPassWord("pxw");
        return user;
    }
}
