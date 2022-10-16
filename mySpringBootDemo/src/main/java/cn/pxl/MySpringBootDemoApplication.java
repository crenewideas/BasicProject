package cn.pxl;

import cn.pxl.aop.aspects.MyAspect;
import cn.pxl.aop.entity.TargetEntity;
import cn.pxl.beans.AnimalBean;
import cn.pxl.beans.UserBean;
import cn.pxl.configs.MyScanConfig;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"cn.pxl.controllers"})
@PropertySource(value = {"classpath:jdbc.properties"},ignoreResourceNotFound = true)
//@EnableJpaRepositories(basePackages = "cn.pxl.jpa")
@EntityScan(basePackages = "cn.pxl.beans.jpabean")
//@EnableMongoRepositories("cn.pxl.mongodb.service.intf")
public class MySpringBootDemoApplication {

//    @Bean
//    public MyAspect returnMyAspect(){
//        return new MyAspect();
//    }

    @Autowired
    private RedisTemplate redisTemplate = null;

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootDemoApplication.class, args);
    }

    @PostConstruct
    public void init(){
        initRedisTemplate();
    }

    //设置redisTemplate的序列化器
    private void initRedisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

}
