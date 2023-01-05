package cn.pxl.event;

import org.springframework.context.support.GenericApplicationContext;

// ApplicationListener 的使用
public class Demo02ApplicationListener {
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        //向 applicationContext 注册事件
        applicationContext.addApplicationListener((event)->{
            System.out.println("接收到spring相关事件：" + event);
        });
        applicationContext.refresh();
        applicationContext.start();
        applicationContext.close();
    }
}
