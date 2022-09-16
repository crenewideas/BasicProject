package cn.pxl.capture03.subsection01;

import lombok.SneakyThrows;

public class Cap3Demo04 {

    private static boolean flag = false;

    private String sharedLock;

    public Cap3Demo04(String sharedLock){
        this.sharedLock = sharedLock;
    }

    //生产者，每生产一个产品，就通知消费者进行消费
    public Runnable soutOne = new Runnable(){
        @SneakyThrows
        @Override
        public void run() {
            synchronized (sharedLock){
                for (int i = 0; i < 5; i++) {
                    while (flag){
                        sharedLock.wait();
                    }
                    System.out.println("⭐️");
                    flag = true;
                    sharedLock.notify();
                }
            }
        }
    };

    public Runnable soutTwo = new Runnable(){
        @SneakyThrows
        @Override
        public void run() {
            synchronized (sharedLock){
                for (int i = 0; i < 5; i++) {
                    while (!flag){
                        sharedLock.wait();
                    }
                    System.out.println("🟥");
                    flag = false;
                    sharedLock.notify();
                }
            }
        }
    };

}
