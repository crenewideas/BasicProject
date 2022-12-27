package cn.pxl.thinking.ioc.dependency.injeaction;

import cn.pxl.entity.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;


//依赖注入的方式
public class DependencyInjection {
    public static void main(String[] args) {
        //配置xml，启动上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META_INF/dependency-injection.xml");
        injectionBeansByName(beanFactory);
        injectionBeansByType(beanFactory);
        injectionBeanFactory(beanFactory);

    }

    //一：通过名称自动注入
    static void injectionBeansByName(BeanFactory beanFactory){
        UserRepository userRepository  = (UserRepository)beanFactory.getBean("userRepositoryByName");
        System.out.println("通过名称自动注入：" + userRepository);
    }

    //二：通过类型注入
    static void injectionBeansByType(BeanFactory beanFactory){
        UserRepository userRepository = (UserRepository)beanFactory.getBean("userRepositoryByType");
        System.out.println("通过类型自动注入：" + userRepository);
    }



    //三：根据类型自动注入内建 beanFactory 对象
    private static void injectionBeanFactory(BeanFactory beanFactory) {
        // 自动注入依赖来源一：自定义的bean：如 userRepository.getUsers();，属于ioc容器内
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepositoryInjectionBeanFactory");
        // 自动注入依赖来源二：内建的依赖：injectionBeanFactory，属于ioc容器之外。
        BeanFactory injectionBeanFactory = userRepository.getBeanFactory();
        // 自动注入依赖来源三：内建的bean，属于ioc容器内。
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("自动注入来源：内建Environment对象:"+environment);
        //自动注入的 BeanFactory 信息
        System.out.println("自动注入的 BeanFactory 信息：" + injectionBeanFactory);
        //判断自动注入的 BeanFactory 是否是当前的 beanFactory 对象 : false。为什么不会成立？
        whoIsContainer(beanFactory,injectionBeanFactory);
        //自动注入的 BeanFactory 的类型 : DefaultListableBeanFactory
        System.out.println("根据类型自动注入内建BeanFactory对象时：注入BeanFactory的类型：" + injectionBeanFactory.getClass());
        //延时注入 ObjectFactory
        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        ApplicationContext injectionApplicationContext = objectFactory.getObject();
        //classPathXmlApplicationContext
        System.out.println("ObjectFactory<AppletContext> : " + injectionApplicationContext);
        //true
        System.out.println("当前applicationContext 是否是自动注入的 ObjectFactory<AppletContext> 的属性：" + (beanFactory == injectionApplicationContext));
    }

    //谁才是IOC容器？
    private static void whoIsContainer(BeanFactory beanFactory,BeanFactory injectionBeanFactory){
        System.out.println("根据类型自动注入内建BeanFactory对象时：是否是当前的beanFactory对象：" + beanFactory.equals(injectionBeanFactory));
        //为什么是false?
        //ApplicationContext is BeanFactory but is different from injectionBeanFactory，they are two different object。
        // beanFactory 实际类型是 ApplicationContext ； injectionBeanFactory 实际类型是 DefaultListableBeanFactory。
        // AbstractRefreshableApplicationContext 的 beanFactory 属性。
    }
}
