package cn.pxl.config.metadata;

import cn.pxl.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import java.util.Map;

//10.基于Java注解装载Spring Ioc容器配置元信息
@ImportResource("META_INF/xml-bean.xml")
@Import(User.class)
public class Demo10AnnotatedConfigContainer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前类
        applicationContext.register(Demo10AnnotatedConfigContainer.class);
        //启动应用上下文
        applicationContext.refresh();
        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
        beansOfType.forEach((key,value)->{
            System.out.println(key + ":" + value);
            //cn.pxl.entity.User:User(userName=null, passWord=cn.pxl.entity.User, age=115)
            //xmlConstructorUser:User(userName=xmlConstructorUser, passWord=xmlConstructorUser, age=115)
            //第一个User，是 @Import 生成的
            //第二个User，是 @ImportResource 生成的。
        });
        //关闭应用上下文
        applicationContext.close();
    }
}
