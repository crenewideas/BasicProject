package cn.pxl.capture02.subsection01;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Demo09ThreadDemo2 implements Runnable{
    private Demo09 demo09;

    @Override
    public void run() {
        demo09.doDemo02();
    }
}
