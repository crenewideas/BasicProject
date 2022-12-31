package cn.pxl.dependency.injection;

import cn.pxl.dependency.entity.UserHolder;
import cn.pxl.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

//基于注解的字段注入
@Data
public class AnnotationFieldInjection {

    // 配置类也是 spring 的 Bean ，因此也会注入。
    // Autowired 会忽略掉静态字段，因此不能声明为静态。
    @Autowired
    private UserHolder userHolder;

    // ByType
    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationFieldInjection.class);
        applicationContext.refresh();
        AnnotationFieldInjection annotationFieldInjection = (AnnotationFieldInjection) applicationContext.getBean("annotationFieldInjection");
        UserHolder userHolder = annotationFieldInjection.getUserHolder();
        UserHolder userHolder2 = annotationFieldInjection.getUserHolder2();
        System.out.println(userHolder);
        System.out.println(userHolder2);
        System.out.println("UserHolder2 = UserHolder :" + (userHolder2 == userHolder));//UserHolder2 = UserHolder :true
    }

    //User 是通过类型的方式在上下文中寻找。
    @Bean
    public UserHolder userHolder(User user1){
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user1);
        return userHolder;
    }

    @Bean
    public User user(){
        User user = new User();
        user.setUserName("annotationField");
        return user;
    }


}
