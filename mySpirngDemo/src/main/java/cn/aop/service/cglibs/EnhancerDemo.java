package cn.aop.service.cglibs;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EnhancerDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerDemo.class);
        enhancer.setCallback(new MethodInterceptorImpl());
        EnhancerDemo cglibEnhancer = (EnhancerDemo) enhancer.create();
        cglibEnhancer.sayHello();
    }

    public void sayHello(){
        System.out.println("hello cglib!");
    }

    private static class MethodInterceptorImpl implements MethodInterceptor{
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("前置方法");
            Object invoke = methodProxy.invokeSuper(o, objects);
            System.out.println("后置方法");
            return invoke;
        }
    }

}
