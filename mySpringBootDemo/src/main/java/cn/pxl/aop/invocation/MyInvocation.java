package cn.pxl.aop.invocation;

import java.lang.reflect.Method;

public class MyInvocation implements InvocationInterface {

    private Object target ;
    private Object[] params;
    private Method method;

    public MyInvocation(Object target, Object[] params, Method method) {
        this.target = target;
        this.params = params;
        this.method = method;
    }

    @Override
    public Object process() throws Throwable {
        return  method.invoke(target, params);
    }

}
