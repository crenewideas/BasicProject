package cn.pxl.factory;

import cn.pxl.entity.User;

//特殊的Bean创建方式：ServiceLoader 需要的接口
public interface UserFactorySpecialIntf {
    public default User createUser(){
        return User.createUser();
    }
}
