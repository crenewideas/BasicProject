package cn.pxl.capture02.subsection01;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Demo05Thread implements Runnable{
    private Demo05 demo05;

    @Override
    public void run() {
        demo05.service1();
    }
}
