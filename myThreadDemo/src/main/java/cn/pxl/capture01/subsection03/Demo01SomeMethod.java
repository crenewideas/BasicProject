package cn.pxl.capture01.subsection03;

import java.util.Map;

public class Demo01SomeMethod {
    //在main方法中直接调用 现成对象的 run()，不会新起一个线程。
    //调用 Thread 对象的 start() 方法才会新起一个线程。
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread, "A");//构造函数初始化时，当前线程为：main
        thread.start();//执行run方法时，当前先成为：A
        myThread.run(); //执行run方法时，当前先成为：main
        System.out.println(thread.isAlive());//判断当前线程是否存活。

        //mian线程调用 c
        //myThread.c();



    }



}

class MyThread extends Thread{

    public MyThread(){
        System.out.println("构造函数初始化时，当前线程为：" + Thread.currentThread().getName()); //构造函数初始化时，当前线程为：main
        System.out.println("当前线程是否存活：" + Thread.currentThread().isAlive());//当前线程是否存活：true
        System.out.println("当前 MyThread 线程对象为：" + this.getName());//当前 MyThread 线程对象为：Thread-0
        System.out.println("当前 MyThread 线程对象是否存活：" + this.isAlive());//当前 MyThread 线程对象是否存活：false
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("执行run方法时，当前先成为："+ name);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!"main".equals(name)){
            System.out.println("sleep方法执行，当前线程被暂停了0.5秒。");
            //新线程调用了a
            a();
        }
    }

    public void a(){
        b();
    }

    public void b(){
        c();
    }

    public void c(){
        Thread currentThread = Thread.currentThread();
        System.out.println("当前线程ID:"+currentThread.getId());
        StackTraceElement[] stackTrace = currentThread.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            int lineNumber = stackTraceElement.getLineNumber();
            String methodName = stackTraceElement.getMethodName();
            String fileName = stackTraceElement.getFileName();
            System.out.println(
                    "className : " + className +
                    ",lineNumber : " + lineNumber +
                    ",methodName : " + methodName +
                    ",fileName : " + fileName);

            //获取所有活动线程的堆栈map信息。
            Map<Thread, StackTraceElement[]> allStackTraces = currentThread.getAllStackTraces();
//            allStackTraces.forEach((key,value)->{
//                System.out.println(key);
//                System.out.println(value);
//            });

        }


    }

}
