package cn.pxl.user.service;

import cn.pxl.feign.clients.UserClient;
import cn.pxl.feign.pojo.user.User;
import cn.pxl.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserClient {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }
}