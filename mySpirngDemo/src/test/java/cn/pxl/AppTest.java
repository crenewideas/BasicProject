package cn.pxl;

import static org.junit.Assert.assertTrue;

import cn.aop.service.MyInvocationHandler;
import cn.aop.service.impl.UserServiceImpl;
import cn.pxl.bean.MyTestBean;
import cn.pxl.entity.User;
import cn.pxl.jdbc.UserService;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testSimpleLoad(){
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("beanConfig/capture02Basic.xml"));
        MyTestBean myTestBean = (MyTestBean)xmlBeanFactory.getBean("myTestBean");
        System.out.println(myTestBean.getTestStr());
    }



    @Test
    public void doProxyTest(){
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(new UserServiceImpl());
        cn.aop.service.UserService proxyObj = (cn.aop.service.UserService) myInvocationHandler.getProxyObj();
        proxyObj.saveUser();
    }

    @Test
    public void doJdbcTest(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beanConfig/capture02Basic.xml");
        UserService userService = (UserService) classPathXmlApplicationContext.getBean("userService");
        User user = new User("4", "e", "e", "e", "e", "e");
        userService.save(user);
        System.out.println(userService.selectAll());
    }

}
