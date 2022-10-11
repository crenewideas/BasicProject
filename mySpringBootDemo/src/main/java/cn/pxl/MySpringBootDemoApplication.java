package cn.pxl;

import cn.pxl.beans.AnimalBean;
import cn.pxl.beans.UserBean;
import cn.pxl.configs.MyScanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:jdbc.properties"},ignoreResourceNotFound = true)
public class MySpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootDemoApplication.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyScanConfig.class);
        UserBean userBean = context.getBean(UserBean.class);
        AnimalBean animalBean = userBean.getAnimalBean();
        System.out.println(animalBean.getAnimalName());
    }

}
