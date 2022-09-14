package cn.pxl.capture01.subsection04;


//1. 实例方法 interrupt()       :this 线程打断标记设置为 true。
//2. 静态方法 interrupted()     :Thread.interrupted() 返回当前线程的 打断状态，然后把当前线程的打断状态设置为 false。
//3. 实例方法 isInterrupted()   :this 返回线程的 打断状态，不会修改打断状态。
public class Demo01Interrupted {
    public static void main(String[] args) {

        //打断当前线程。
        Thread.currentThread().interrupt();
        //测试当前线程 是否被打断：这个方法会清除当前线程的状态，改为false。
        boolean interrupted = Thread.interrupted();//true
        System.out.println(interrupted);
        boolean interrupted2 = Thread.interrupted();//false
        System.out.println(interrupted2);

        MyThread myThread = new MyThread();
        myThread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打断指定线程对象线程。
        myThread.interrupt();
        System.out.println(myThread.isInterrupted());//true
        System.out.println(myThread.isInterrupted());//true

        MyThreadThrew myThreadThrew = new MyThreadThrew();
        myThreadThrew.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打断指定线程对象线程。
        myThreadThrew.interrupt();
        System.out.println(myThreadThrew.isInterrupted());//true

    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
            if(Thread.interrupted()){
                break;
                //当前线程被打断后，如果想直接停止当前线程的执行，那么可以：
                //1.抛出异常：InterruptedException
                //2.直接return().
                //相比较，InterruptedException 方式更好，因为可以进行异常的处理等。
            }
        }
        System.out.println("for 循环被打断后，不影响run 方法继续执行。");
    }
}

class MyThreadThrew extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
            if(Thread.interrupted()){
                try {
                    throw new InterruptedException();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("当前线程已被打断，执行 catch 中的代码。");
                }
            }
        }
        System.out.println("for 循环被打断后，不影响run 方法继续执行。");
    }
}
