package cn.pxl.aop.proxyEntity;

import cn.pxl.aop.interceptor.InterceptorInterface;
import cn.pxl.aop.invocation.MyInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxyEntity implements InvocationHandler {

    private Object target;
    private InterceptorInterface interceptor;

    public static Object getProxyBean(Object target,InterceptorInterface interceptor){
        MyProxyEntity myProxyEntity = new MyProxyEntity();
        myProxyEntity.target = target;
        myProxyEntity.interceptor = interceptor;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),myProxyEntity);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean exceptionFlag = false;
        MyInvocation myInvocation = new MyInvocation(proxy,args,method);
        InterceptorInterface interceptor = this.interceptor;
        interceptor.before();
        Object retObject = null;
        try{
            if(interceptor.useAround()){
                retObject = interceptor.around(myInvocation);
            }else {
                retObject = method.invoke(proxy,args);
            }
        }catch (Exception e){
            exceptionFlag = true;
        }
        interceptor.after();
        if(exceptionFlag){
            interceptor.afterThrowing();
        }else {
            interceptor.afterReturning();
            return retObject;
        }
        return null;
    }
}
