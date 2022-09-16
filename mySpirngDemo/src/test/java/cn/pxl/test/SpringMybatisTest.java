package cn.pxl.test;

import cn.pxl.entity.User;
import cn.pxl.mybatisBasic.MybatisUtils;
import cn.pxl.mybatisBasic.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

// java 单独整合 mybatis 的测试类
public class SpringMybatisTest {

    @Test
    public void testSelect(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beanConfig/capture02Basic.xml");
        UserMapper userMapper = (UserMapper) classPathXmlApplicationContext.getBean("userMapper");
        System.out.println(userMapper.getUser());
    }

}
