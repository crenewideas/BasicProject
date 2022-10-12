package cn.pxl.aop.interceptor;

import cn.pxl.aop.invocation.MyInvocation;

import java.lang.reflect.InvocationHandler;

public interface InterceptorInterface {
    public boolean before();
    public boolean useAround();
    public void after();
    public Object around(MyInvocation myInvocation);
    public void afterReturning();
    public void afterThrowing();
}
