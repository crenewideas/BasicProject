package cn.pxl.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class SpringAnnotationTest {
    public static void main(String[] args) {
        //1.创建容器
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("cn.pxl");
        //2.获取对象
        JdbcTemplate springConfiguration = (JdbcTemplate)annotationConfigApplicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        //3.输出结果
        System.out.println(springConfiguration.getDataSource());
//        JdbcTemplate jdbcTemp = annotationConfigApplicationContext.getBean("jdbcTemp", JdbcTemplate.class);
//        String sql = "insert into account (name,money) values (?,?)";
//        jdbcTemp.update(sql,"test",12345);

    }
}
