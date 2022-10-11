package cn.pxl.myspringbootdemo;

import cn.pxl.beans.AnimalBean;
import cn.pxl.beans.DataBasePropertiesBean;
import cn.pxl.beans.DataBasePropertiesBeanByConfiAnno;
import cn.pxl.beans.UserBean;
import cn.pxl.configs.MyScanConfig;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class MySpringBootDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testComponentScan(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyScanConfig.class);
        UserBean userBean = context.getBean(UserBean.class);
        AnimalBean animalBean = userBean.getAnimalBean();
        System.out.println(animalBean.getAnimalName());
    }

    @Test
    public void testBusinessUser(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyScanConfig.class);
        context.close();
    }

    @Test
    public void testDataBasePropertiesBean(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyScanConfig.class);
        DataBasePropertiesBean dataBasePropertiesBean = (DataBasePropertiesBean) context.getBean("dataBasePropertiesBean");
        System.out.println("driver::" + dataBasePropertiesBean.getDriver());
        context.close();
    }

    @Test
    public void testDataBasePropertiesBeanByConfiAnno(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyScanConfig.class);
        DataBasePropertiesBeanByConfiAnno dataBasePropertiesBeanByConfiAnno = (DataBasePropertiesBeanByConfiAnno) context.getBean("dataBasePropertiesBeanByConfiAnno");
        System.out.println(dataBasePropertiesBeanByConfiAnno);
        context.close();
    }
}
