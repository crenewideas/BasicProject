package cn.pxl.capture03.subsection01;

import lombok.AllArgsConstructor;


public class Cap3Demo02 {
    private volatile boolean waitFlag = true;
    private String lock = "";

    public Cap3Demo02(String lock){
        this.lock = lock;
    }

    public Runnable runnableOne = new Runnable(){
        @Override
        public void run() {
            synchronized (lock){
                if (waitFlag){
                    System.out.println("begin wait!");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                    System.out.println("end wait!");
                }
            }
        }
    };

    public Runnable runnableTwo = new Runnable(){
        @Override
        public void run() {
            synchronized (lock){
                lock.notify();
                System.out.println("已通知wait方法");
                waitFlag = false;
            }
        }
    };


}
