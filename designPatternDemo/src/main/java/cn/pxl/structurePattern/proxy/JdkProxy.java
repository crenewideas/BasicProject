package cn.pxl.structurePattern.proxy;

public class JdkProxy implements ProxyInterface{


    @Override
    public void doSome() {
        System.out.println("doSome");
    }

    @Override
    public void doOther() {
        System.out.println("doOther");
    }
}
