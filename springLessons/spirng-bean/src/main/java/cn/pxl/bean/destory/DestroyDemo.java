package cn.pxl.bean.destory;

import cn.pxl.factory.InitialUserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//销毁方法的执行顺序：
//1.PreDestroy注解实现的销毁方法
//2.实现DisposableBean接口的销毁方法
//3.自定义的销毁方法
@Configuration
public class DestroyDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DestroyDemo.class);
        applicationContext.refresh();
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory",destroyMethod = "destroyUserFactory")
    public InitialUserFactory userFactory(){
        return new InitialUserFactory();
    }

}
//PreDestroy注解实现的销毁方法
//实现DisposableBean接口的销毁方法
//自定义的销毁方法