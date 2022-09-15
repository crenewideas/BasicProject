package cn.pxl.capture02.subsection01;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Demo07Thread implements Runnable{
    private Demo07 demo07;

    @Override
    public void run() {
        demo07.doDemo();
    }
}
