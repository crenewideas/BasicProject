package cn.pxl.myspringbootdemo3.propertybean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//用于封装 application.properties 中的一组配置。
@Component
@ConfigurationProperties(prefix = "user")
@ToString
//@Data必须要写，否则值注入不进去。
@Data
public class UserBean {
    private String userName;
    private String passWord;
}
