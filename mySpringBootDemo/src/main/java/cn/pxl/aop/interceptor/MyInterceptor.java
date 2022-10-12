package cn.pxl.aop.interceptor;

import cn.pxl.aop.invocation.MyInvocation;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationHandler;

public class MyInterceptor implements InterceptorInterface{


    @Override
    public boolean before() {
        System.out.println("...before");
        return true;
    }

    @Override
    public boolean useAround() {
        System.out.println("...useAround");
        return true;
    }

    @Override
    public void after() {
        System.out.println("...after");
    }

    @SneakyThrows
    @Override
    public Object around(MyInvocation myInvocation) {
        System.out.println("...before around");
        Object process = myInvocation.process();
        System.out.println("...after around");
        return process;
    }

    @Override
    public void afterReturning() {

    }

    @Override
    public void afterThrowing() {

    }
}
