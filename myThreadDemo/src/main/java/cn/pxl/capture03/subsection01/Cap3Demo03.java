package cn.pxl.capture03.subsection01;

public class Cap3Demo03 {

    private static long sharedValue = 0L;

    private String sharedLock;

    public Cap3Demo03(String sharedLock){
        this.sharedLock = sharedLock;
    }

    //生产者，每生产一个产品，就通知消费者进行消费
    public Runnable consumer1 = new Runnable(){
        @Override
        public void run() {
            consumerRun();
        }
    };

    //生产者，每生产一个产品，就通知消费者进行消费
    public Runnable consumer2 = new Runnable(){
        @Override
        public void run() {
            consumerRun();
        }
    };

    private void consumerRun() {
        synchronized (sharedLock){
            while (true){
                if (sharedValue == 0L){
                    System.out.println("");
                    try {
                        sharedLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //消费者收到通知了，开始消费
                System.out.println("消费者消费的值是:" + sharedValue);
                sharedValue = 0L;
                //通知生产者
                sharedLock.notify();
            }
        }
    }

    //消费者，每消费一个产品，就通知生产者进行生产
    public Runnable producer1 = new Runnable(){
        @Override
        public void run() {
            producerRun();
        }
    };

    //消费者，每消费一个产品，就通知生产者进行生产
    public Runnable producer2 = new Runnable(){
        @Override
        public void run() {
            producerRun();
        }
    };

    private void producerRun() {
        synchronized (sharedLock){
            while (true){
                //有值就等待
                if (sharedValue != 0L) {
                    try {
                        sharedLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //这里是 消费者 提醒已经没值了，再开始生产
                sharedValue = System.currentTimeMillis();
                System.out.println("生产者生产的值是:" + sharedValue);
                //生产好了，通知消费者
                sharedLock.notify();
            }
        }
    }
}
