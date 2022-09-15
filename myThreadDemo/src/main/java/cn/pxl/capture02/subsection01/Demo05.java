package cn.pxl.capture02.subsection01;

public class Demo05 {
    public synchronized void service1(){
        System.out.println("service1");
        service2();
    }

    public synchronized void service2(){
        System.out.println("service2");
        service3();
    }

    public synchronized void service3(){
        System.out.println("service3");
    }
}
