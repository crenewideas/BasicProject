package cn.pxl;

import cn.pxl.aop.aspects.MyAspect;
import cn.pxl.aop.entity.TargetEntity;
import cn.pxl.beans.AnimalBean;
import cn.pxl.beans.UserBean;
import cn.pxl.configs.MyScanConfig;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"cn.pxl"})
@PropertySource(value = {"classpath:jdbc.properties"},ignoreResourceNotFound = true)
@EnableJpaRepositories(basePackages = "cn.pxl.jpa")
@EntityScan(basePackages = "cn.pxl.beans.jpabean")
public class MySpringBootDemoApplication {

//    @Bean
//    public MyAspect returnMyAspect(){
//        return new MyAspect();
//    }

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootDemoApplication.class, args);
    }

}
