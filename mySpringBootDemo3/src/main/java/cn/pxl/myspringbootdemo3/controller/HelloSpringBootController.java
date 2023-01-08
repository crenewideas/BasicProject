package cn.pxl.myspringbootdemo3.controller;

import cn.pxl.myspringbootdemo3.propertybean.UserBean;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Rest模式
@RestController
@RequestMapping("/hello")
public class HelloSpringBootController {

    @Autowired
    private Environment environment;

    @Autowired
    private UserBean userBean;

    //读取配置文件中指定名称的值
    //方式一：SPEL表达式获取
    @Value("${pxl.name}")
    private String pxlName;

    @GetMapping("/springboot")
    public String getById(){
        System.out.println("hello springboot ! ");
        System.out.println(pxlName);
        //方式二:environment.getProperty
        System.out.println(environment.getProperty("pxl.name"));
        return "hello springboot!";
    }

    @GetMapping("/userInfo")
    public String getUserInfo(){
//      System.out.println(userBean.getUserName());
        return userBean.toString();
    }
}
