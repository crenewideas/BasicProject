package cn.pxl.jdbc;

import cn.pxl.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional(propagation = Propagation.REQUIRED)
    //在不加 Transactional 注解时，业务异常后不会回滚库操作。
    public void save(User user);
    public List<User> selectAll();
}
