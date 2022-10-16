package cn.pxl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication(scanBasePackages = {"cn.pxl"})
@PropertySource(value = {"classpath:jdbc.properties"},ignoreResourceNotFound = true)
@EntityScan(basePackages = "cn.pxl.beans.jpabean")
//@EnableWebSecurity
public class MySpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootDemoApplication.class, args);
    }


}
