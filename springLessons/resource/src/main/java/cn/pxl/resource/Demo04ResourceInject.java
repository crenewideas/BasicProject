package cn.pxl.resource;

import cn.pxl.entity.User;
import cn.pxl.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import javax.annotation.PostConstruct;

//注入 Resource
public class Demo04ResourceInject {

    //外部化配置，注入单个Resource
    @Value("classpath:/META-INF/default.properties")
    private Resource resource;

    //外部化配置，注入环境变量值。
    @Value("${user.dir}")
    private String dir;

    //外部化配置注入：注入集合
    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    @PostConstruct
    public void init(){
        System.out.println(ResourceUtils.getContent(resource,"UTF-8"));
        System.out.println(dir);
        System.out.println("----------------");
        for (Resource resource1 : resources) {
            System.out.println(ResourceUtils.getContent(resource1,"UTF-8"));
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Demo04ResourceInject.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBean("user"));
        applicationContext.close();
    }

    @Bean
    public User user(){
        User user = new User();
        user.setUserName("annotationConstructor");
        return user;
    }
}
