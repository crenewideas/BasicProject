package cn.aop.service.impl;

import cn.aop.service.UserService;
import org.springframework.stereotype.Service;

@Service("userServiceI")
public class UserServiceImpl implements UserService {
    @Override
    public void saveUser() {
        System.out.println("执行保存用户信息操作！");
    }
}
