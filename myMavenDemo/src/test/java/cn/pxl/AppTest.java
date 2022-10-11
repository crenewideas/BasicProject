package cn.pxl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cn.pxl.capture03.Demo01HelloWorld;
import org.junit.Test;

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
        String helloWorldResult = Demo01HelloWorld.sayHelloWorld();
        assertEquals("Hello World!",helloWorldResult);
    }
}
