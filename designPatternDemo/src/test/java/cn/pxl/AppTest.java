package cn.pxl;

import cn.pxl.createPattern.factory.abstractMethod.instanceForFactory.FactoryOne;
import cn.pxl.createPattern.factory.abstractMethod.instanceForFactory.FactoryTwo;
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
        System.out.println("hello");
    }

    @Test
    public void factoryTest(){
        FactoryOne factoryOne = new FactoryOne();
        FactoryTwo factoryTwo = new FactoryTwo();
        System.out.println(factoryOne.newPhone());      //Mate50Phone
        System.out.println(factoryTwo.newComputer());   //MateBook01Computer
    }
}
