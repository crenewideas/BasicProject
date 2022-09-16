package cn.pxl.mybatisBasic;

import cn.pxl.entity.User;

import java.util.List;

public interface UserMapper {
    public void insertUser(User user);
    public List<User> getUser();
}
