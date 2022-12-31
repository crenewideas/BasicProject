package cn.pxl.dependency.injection;

import cn.pxl.dependency.entity.UserHolder;
import cn.pxl.entity.User;
import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

// 延时注入
// 1.ObjectProvider
// 2.ObjectFactory
@Data
public class LazyAnnotationInjection {

    //将容器中的User类型的对象搜集到 ObjectProvider 容器中。
    @Autowired
    private ObjectProvider<User> objectProvider;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationInjection.class);
        applicationContext.refresh();
        LazyAnnotationInjection bean = applicationContext.getBean(LazyAnnotationInjection.class);
        ObjectProvider<User> objectProvider = bean.objectProvider;
        //可以对ObjectProvider 类型的对象进行集合操作。
        for (User user : objectProvider) {
            System.out.println(user);
        }
    }

    //User 是通过类型的方式在上下文中寻找。
    @Bean
    public UserHolder userHolder(User user1){
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user1);
        return userHolder;
    }

    @Bean
    public User user1(){
        User user = new User();
        user.setUserName("user1");
        return user;
    }

    @Bean
    public User user2(){
        User user = new User();
        user.setUserName("user2");
        return user;
    }

}
