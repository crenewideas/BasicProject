package cn.pxl.test;

import cn.pxl.entity.User;
import cn.pxl.mybatisBasic.MybatisUtils;
import cn.pxl.mybatisBasic.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

// java 单独整合 mybatis 的测试类
public class MybatisTest {

    private static final SqlSessionFactory SQL_SESSION_FACTORY;

    static {
        SQL_SESSION_FACTORY = MybatisUtils.getSqlSessionFactory();
    }

    @Test
    public void testSelect(){
        SqlSession sqlSession = SQL_SESSION_FACTORY.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.getUser();
        System.out.println(user);
        sqlSession.close();
    }


}
