package cn.pxl.bean.creation;

import cn.pxl.entity.User;
import cn.pxl.factory.UserFactory;
import cn.pxl.factory.UserFactorySpecialIntf;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

//实例化 Bean 的几种方式：特殊方式有三种：
//一：ServiceLoader
//二：AutowireCapableBeanFactory
//三：


public class BeanSpecialInstanceDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/bean-special-creation.xml");
        serviceLoader(applicationContext);
        //通过 ApplicationContext 获取 AutowireCapableBeanFactory
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory(autowireCapableBeanFactory);
    }

    //一：ServiceLoader实例化Bean
    private static void serviceLoader(BeanFactory beanFactory) {
        //getBean 方法获取 ServiceLoaderFactoryBean 类型的实例，其实是获取到的包装的类实例ServiceLoader
        ServiceLoader<UserFactorySpecialIntf> userFactoryServiceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        Iterator<UserFactorySpecialIntf> iterator = userFactoryServiceLoader.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().createUser());
        }
    }

    //测试ServiceLoader的使用
    public static void serviceLoaderTest(){
        //读取    META-INF/services/cn.pxl.factory.UserFactorySpecialIntf 配置文件中的所有实现类，封装到load中。
        ServiceLoader<UserFactorySpecialIntf> load = ServiceLoader.load(UserFactorySpecialIntf.class, Thread.currentThread().getContextClassLoader());
        Iterator<UserFactorySpecialIntf> iterator = load.iterator();
        //遍历所有的 实现类。
        while (iterator.hasNext()){
            UserFactorySpecialIntf next = iterator.next();
            User user = next.createUser();
            System.out.println(user);
        }
    }

    //二：AutowireCapableBeanFactory 例化Bean
    public static void autowireCapableBeanFactory(AutowireCapableBeanFactory beanFactory){
        //创建一个 UserFactory.class ; 事先ioc容器中没有 UserFactory 类型的实例
        UserFactory bean = beanFactory.createBean(UserFactory.class);
        System.out.println(bean.createUser());
    }

    //三：FactoryBean 实例化Bean
    public static void factoryBeanCreateBean(BeanFactory beanFactory) {
//        FactoryBean<User> userByFactoryBean = (FactoryBean<User>)beanFactory.getBean("$userByFactoryBean");
        User user = (User)beanFactory.getBean("userByFactoryBean");
        System.out.println(user);
    }


}
