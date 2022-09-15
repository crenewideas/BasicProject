package cn.pxl.capture02.subsection01;

public class Demo07 extends Demo06{
    public Demo07(String a) {
        super(a);
    }
    int a =0;
    //重写了父类的方法，但是不是同步的，按子类的非同步执行。
    @Override
    public void doDemo() {
        for (int i = 0; i < 100; i++) {
            System.out.println(a);
            a ++;
        }
    }
}
