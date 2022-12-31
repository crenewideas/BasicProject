package cn.pxl.dependency.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;

// 外部化配置作为依赖来源
@Configuration
@PropertySource(value = "META_INF/external.properties")
public class ExternalDependencySourceDemo {

    @Value("${path.resource}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalDependencySourceDemo.class);
        applicationContext.refresh();
        ExternalDependencySourceDemo bean = applicationContext.getBean(ExternalDependencySourceDemo.class);
        System.out.println(bean.resource);
    }
}
