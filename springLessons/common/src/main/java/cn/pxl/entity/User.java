package cn.pxl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements BeanNameAware, InitializingBean, SmartInitializingSingleton {
    private String userName;
    private transient String passWord;
    private Integer age;

    //依赖于注解驱动。CommonAnnotationBeanPostProcessor
    @PostConstruct
    private void initPostConstruct(){
        System.out.println(passWord + "@PostConstruct 方法被调用了！");
        this.age = 111;
    }

    //初始化完成阶段调用
    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("初始化完成阶段调用 afterSingletonsInstantiated 方法！");
        this.age = 115;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet 方法被调用了！");
        this.age = 112;
    }

    //自定义初始化方法
    public void init(){
        System.out.println("自定义的初始化方法被调用了！");
        this.age = 113;
    }

    @PreDestroy
    private void destroy(){
        System.out.println(passWord + "销毁方法被调用了！");
    }

    @Override
    public void setBeanName(String name) {
        //passWord存放当前bean的名称。
        this.passWord = name;
    }


}