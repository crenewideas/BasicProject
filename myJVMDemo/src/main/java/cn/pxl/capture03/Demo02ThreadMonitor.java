package cn.pxl.capture03;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Demo02ThreadMonitor {
    @SneakyThrows
    public static void main(String[] args) {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        //线程阻塞，等待输入
//        bufferedReader.readLine();
        String lockObj = new String("abc");
        //线程锁等待
        new Thread(()->{
            synchronized (lockObj){
                try{
                    lockObj.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"testLockThread").start();
        Thread.sleep(100000);
    }
}
