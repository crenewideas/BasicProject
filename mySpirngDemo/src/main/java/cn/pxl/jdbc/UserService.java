package cn.pxl.jdbc;

import cn.pxl.entity.User;

import java.util.List;

public interface UserService {
    public void save(User user);
    public List<User> selectAll();
}
