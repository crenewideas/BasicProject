package cn.pxl.bean.initial;

import cn.pxl.factory.InitialUserFactory;
import cn.pxl.factory.UserFactorySpecialIntf;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

//执行顺序就是以下的顺序。
//初始化方式一：@PostConstruct 指定初始化方法
//初始化方式三：实现InitializingBean = "" 指定自己的初始化方法
//初始化方式二：initMethod = "" 指定自己的初始化方法

@Configuration
public class InitialBeanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext();
        annotationContext.register(InitialBeanDemo.class);
        //启动应用上下文
        annotationContext.refresh();

//        InitialUserFactory initialUserFactory = annotationContext.getBean(InitialUserFactory.class);
//        System.out.println(initialUserFactory);
        annotationContext.close();
    }

    //初始化方式二：initMethod = "" 指定自己的初始化方法
    @Bean(initMethod = "initUserFactory")
    @Lazy
    //非延时初始化，启动应用上下文后就会加载。
    //延时初始化，是一种按需初始化，通过依赖查找触发了初始化的过程。如果没有进行依赖查找，那么当前bean不会被初始化。
    public InitialUserFactory userFactory(){
        InitialUserFactory initialUserFactory = new InitialUserFactory();
        System.out.println("userFactory 已被创建完成！");
        return initialUserFactory;
    }

    //userFactory 已被创建完成！
    //PostConstruct方法，InitialUserFactory 初始化中
    //InitializingBean 的afterPropertiesSet 方法执行，InitialUserFactory初始化后
    //自定义初始化方法，InitialUserFactory 初始化中
}
