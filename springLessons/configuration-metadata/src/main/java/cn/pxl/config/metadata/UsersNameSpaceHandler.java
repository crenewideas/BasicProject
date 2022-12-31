package cn.pxl.config.metadata;

import cn.pxl.entity.User;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

//自定义NamespaceHandler实现：命名空间绑定
public class UsersNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        //将User元素注册对应的 UserBeanDefinitionParser
        registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
    }
}
