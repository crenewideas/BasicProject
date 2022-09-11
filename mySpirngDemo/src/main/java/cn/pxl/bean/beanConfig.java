package cn.pxl.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
public class beanConfig {

    //这个注解：添加到方法上，作用是将当前方法到返回值添加到ioc容器中。
    @Bean(name = "dataSource",autowireCandidate = true)
    public DataSource createDataSource(){
        //创建一个数据源对象
        return new DriverManagerDataSource();
    }

    //参数中的 dataSource对象 会按类型注入到参数中。当 DataSource中 autowireCandidate = false：就不能按类型注入了。如果想要注入，那么可以用@Resource(name = "beanName")注入。
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
