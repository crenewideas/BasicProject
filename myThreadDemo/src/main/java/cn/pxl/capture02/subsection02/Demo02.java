package cn.pxl.capture02.subsection02;
//多个锁就是异步执行的。（见synchronized(anyThing)）

//当前线程名称：{Thread-0},在{1650176927223}进入代码块。
//设置名称完毕，休息3000ms
//当前线程名称：{Thread-1},在{1650176927223}进入代码块。
//。。。。。。
public class Demo02 {
      public static void main(String[] args) {
        ServiceDemo29 serviceDemo29 = new ServiceDemo29();
        Thread threadA = new Thread(new ThreadA29(serviceDemo29));
        Thread threadB = new Thread(new ThreadB29(serviceDemo29));
        threadA.start();
        threadB.start();
    }
}

class ServiceDemo29{
    private String userName;
    private String passWord;

    public  void setUserNameAndPassWord(String userName,String passWord){
        //用任意一个对象作为参数。
        String anyThing = new String();
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

class ThreadA29 implements Runnable{

    private ServiceDemo29 serviceDemo01;

    public ThreadA29(ServiceDemo29 serviceDemo01){
        this.serviceDemo01 = serviceDemo01;
    }


    @Override
    public void run() {
        serviceDemo01.setUserNameAndPassWord("A","a");
    }
}

class ThreadB29 implements Runnable{

    private ServiceDemo29 serviceDemo01;

    public ThreadB29(ServiceDemo29 serviceDemo01){
        this.serviceDemo01 = serviceDemo01;
    }


    @Override
    public void run() {
        serviceDemo01.setUserNameAndPassWord("B","b");
    }
}