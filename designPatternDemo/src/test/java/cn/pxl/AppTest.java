package cn.pxl;

import cn.pxl.createPattern.builder.AbstractPhoneBuilder;
import cn.pxl.createPattern.builder.Phone;
import cn.pxl.createPattern.builder.PhoneBuilder;
import cn.pxl.createPattern.factory.abstractMethod.instanceForFactory.FactoryOne;
import cn.pxl.createPattern.factory.abstractMethod.instanceForFactory.FactoryTwo;
import cn.pxl.structurePattern.adapter.AdaptPlayer;
import cn.pxl.structurePattern.adapter.ObjectTranslator;
import cn.pxl.structurePattern.adapter.OriginalPlayer;
import cn.pxl.structurePattern.bridge.impl.OfflineSale;
import cn.pxl.structurePattern.bridge.impl.PhoneImpl;
import cn.pxl.structurePattern.flywaitress.FlyWaitressFactory;
import cn.pxl.structurePattern.flywaitress.FlyWaitressImpl;
import cn.pxl.structurePattern.proxy.JdkProxy;
import cn.pxl.structurePattern.wrapper.Player;
import cn.pxl.structurePattern.wrapper.PlayerWrapper;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static cn.pxl.structurePattern.flywaitress.FlyWaitressFactory.getWaitress;

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

    @Test
    public void phoneTest(){
        PhoneBuilder phoneBuilder = new PhoneBuilder();
        AbstractPhoneBuilder abstractPhoneBuilder = phoneBuilder.phoneCpu("888").phoneMemory("16G");
        Phone phone = abstractPhoneBuilder.getPhone();
        System.out.println(phone);
    }

    //适配器模式：不改变原有的对象，添加适配功能。
    @Test
    public void adapterTest(){
        OriginalPlayer originalPlayer = new OriginalPlayer();
        ObjectTranslator objectTranslator = new ObjectTranslator();
        AdaptPlayer adaptPlayer = new AdaptPlayer(originalPlayer,objectTranslator);
        System.out.println(adaptPlayer.player());
    }

    // InputStreamReader 桥接模式
    @Test
    public void bridgeTest(){
        cn.pxl.structurePattern.bridge.Phone phone = new PhoneImpl(new OfflineSale("aaa", "bbb"));
        System.out.println(phone.getPhone());
    }

    @Test
    public void wrapperTest(){
        PlayerWrapper playerWrapper = new PlayerWrapper(new Player());
        playerWrapper.play();
    }


    @Test
    public void doProxy(){
//        JdkProxy jdkProxy = new JdkProxy();
//        Class<? extends JdkProxy> aClass = jdkProxy.getClass();
//        JdkProxy jdkProxyInstance = (JdkProxy)Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(),(proxy,method,args)->{
//            method.invoke(jdkProxy, args);
//            System.out.println("增强方法被调用");
//            return proxy;
//        });
//        jdkProxyInstance.doSome();
    }

    @Test
    public void doFlyTest(){
        getWaitress("1");
    }
}
