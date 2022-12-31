package cn.pxl.dependency.injection;

import cn.pxl.dependency.entity.UserHolder;
import cn.pxl.entity.User;
import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Collection;

// 注解驱动的依赖注入处理过程
@Data
public class LazyAnnotationInjectionResolution {

    @Autowired
    private User user; //   实时注入 + 类型依赖查找 。。。 这些信息封装到 DependencyDescriptor 中。
    @Autowired
    private Collection<User> users;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationInjectionResolution.class);
        applicationContext.refresh();

    }


    @Bean
    @Primary
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
