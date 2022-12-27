package cn.pxl.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

//  依赖注入与依赖查找的来源不同：依赖查找时，会从容器中查找【自定义的bean或者是容器内建的bean】；
//                           依赖注入时，除了从容器中注入【自定义的bean或者是容器内建的bean】，也会注入内建的非bean等对象【容器内建依赖】。
@Data
@ToString
public class UserRepository {
    private Collection<User> users; //自定义的bean
    private BeanFactory beanFactory;//内建的非bean自动注入(非bean的含义是：自动注入的beanFactory并不在容器中；所以getBean通过byType的方式不能获取到BeanFactory)
    private ObjectFactory<ApplicationContext> objectFactory; // ObjectFactory 实现对象的延时注入。
}
