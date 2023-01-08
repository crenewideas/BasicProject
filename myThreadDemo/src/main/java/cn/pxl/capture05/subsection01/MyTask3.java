package cn.pxl.capture05.subsection01;

public class MyTask3 {

    private static boolean flag = true;

    public static void main(String[] args) {

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag=false;
            System.out.println("新线程已修改值为false");
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        foo();
    }

    private static void foo(){
        for (int i = 0; i < 10; i++) {
            System.out.println("flag = " + flag +",i = " + i);//
            System.out.println("i = " + i);//
        }
        System.out.println("已读取到改变！ -");
    }

}
