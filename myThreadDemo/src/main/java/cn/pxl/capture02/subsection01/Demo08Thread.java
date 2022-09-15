package cn.pxl.capture02.subsection01;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Demo08Thread implements Runnable{
    private Demo08 demo08;

    @Override
    public void run() {
        demo08.doDemo();
    }
}
