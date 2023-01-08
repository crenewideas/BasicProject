package cn.pxl.myspringbootdemo3;

import cn.pxl.myspringbootdemo3.dao.mapper.PlusUserMapper;
import cn.pxl.myspringbootdemo3.dao.mapper.UserMapper;
import cn.pxl.myspringbootdemo3.entity.common.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MySpringBootDemo3ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PlusUserMapper plusUserMapper;

    @Test
    public void insertUser(){
        User user = new User();
        user.setName("pxl");
        user.setAge(22);
        int count = userMapper.insertUser(user);
        System.out.println(count);
    }

    @Test
    public void selectUser(){
        User user = userMapper.getUserById(2);
        System.out.println(user);
    }

    @Test
    public void selectUserByPlus(){
        User user = plusUserMapper.selectById(2);
        System.out.println(user);
    }

}
