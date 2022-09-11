package cn.aaa.scan;

import org.springframework.context.annotation.*;

//配置类，相当于applicationContext.xml配置类。
@Configuration
//@Import(value = JdbcConfig.class)
@ComponentScan(value = "cn.pxl.config")
public class SpringConfiguration {

    @Override
    public String toString() {
        return "SpringConfiguration{}";
    }
}
