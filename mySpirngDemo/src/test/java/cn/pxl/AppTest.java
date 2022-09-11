package cn.pxl;

import static org.junit.Assert.assertTrue;

import cn.pxl.bean.MyTestBean;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
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
}
