package cn.pxl.convertion;

import cn.pxl.convertion.entity.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// 将 Properties 转换为 String
public class Demo04PropertyToString {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/property-editor-context.xml");
        applicationContext.refresh();
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
        applicationContext.close();
    }


}

