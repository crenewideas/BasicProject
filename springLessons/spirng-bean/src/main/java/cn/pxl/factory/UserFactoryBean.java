package cn.pxl.factory;

import cn.pxl.entity.User;
import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        User user = User.createUser();
        user.setName("userFactoryBean");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
