package cn.pxl.aop.entity;

import cn.pxl.aop.entity.intf.TargetInterface;

public class TargetEntity implements TargetInterface {


    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }
}
