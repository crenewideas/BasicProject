package cn.aop.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MyInvocationHandler  implements InvocationHandler {

    //要代理增强的类。
    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("之前执行");
        Object invoke = method.invoke(target, args);
        System.out.println("之后执行");
        return invoke;
    }

    public Object getProxyObj(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),target.getClass().getInterfaces(),this);
    }

}
