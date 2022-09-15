package cn.pxl.capture02.subsection02;

public class Demo01 {
    //在this作为锁对象时：多个线程调用同一个对象的不同名称的同步方法或同步代码块，调用是按顺序执行的。
    //同一个时间，只会有一个线程执行同步代码块或同步方法。
    //同步代码块或同步方法，彼此之间的执行是同步的，而非异步。

    //在非this作为锁对象时：非this锁的同步代码不会与this锁的同步代码争抢锁，因此非this的同步代码可以与this同步代码同步执行。。
    public static void main(String[] args) {
        ServiceDemo01 serviceDemo01 = new ServiceDemo01();
        Thread threadA = new Thread(new ThreadA(serviceDemo01));
        Thread threadB = new Thread(new ThreadB(serviceDemo01));
        threadA.start();
        threadB.start();
    }
}

class ServiceDemo01{
    private String userName;
    private String passWord;

    private String anyThing = new String();
    public  void setUserNameAndPassWord(String userName,String passWord){
        //用任意一个对象作为参数。
        //同一个对象，调用方法，锁一样不同
        synchronized(anyThing){
            System.out.println(("当前线程名称：{"+Thread.currentThread().getName()+"},在{"+System.currentTimeMillis()+"}进入代码块。"));
            this.userName = userName;

            System.out.println("设置名称完毕，休息3000ms");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("休息完毕，开始设置密码");
            this.passWord = passWord;
            System.out.println("当前线程执行完毕。");
        }
    }
}

class ThreadA implements Runnable{

    private ServiceDemo01 serviceDemo01;

    public ThreadA(ServiceDemo01 serviceDemo01){
        this.serviceDemo01 = serviceDemo01;
    }


    @Override
    public void run() {
        serviceDemo01.setUserNameAndPassWord("A","a");
    }
}

class ThreadB implements Runnable{

    private ServiceDemo01 serviceDemo01;

    public ThreadB(ServiceDemo01 serviceDemo01){
        this.serviceDemo01 = serviceDemo01;
    }


    @Override
    public void run() {
        serviceDemo01.setUserNameAndPassWord("B","b");
    }
}