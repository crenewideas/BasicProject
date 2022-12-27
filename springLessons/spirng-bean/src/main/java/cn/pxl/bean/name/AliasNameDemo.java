package cn.pxl.bean.name;

import cn.pxl.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//bean的别名
public class AliasNameDemo {
    public static void main(String[] args) {
        //META-INF/bean-definition.xml
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-definition.xml");
        //通过别名获取
        User aliasUser1 = (User) beanFactory.getBean("aliasUser1");
        User aliasUser2 = (User) beanFactory.getBean("aliasUser2");
        User user = (User) beanFactory.getBean("user");
        System.out.println("aliasUser1与aliasUser2是否相等:"+(aliasUser1 == aliasUser2));
        System.out.println("aliasUser1与user是否相等:"+(aliasUser1 == user));
    }
}
