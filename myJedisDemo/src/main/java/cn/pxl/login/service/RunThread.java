package cn.pxl.login.service;

import cn.pxl.login.service.impl.MyThread;

public class RunThread {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
