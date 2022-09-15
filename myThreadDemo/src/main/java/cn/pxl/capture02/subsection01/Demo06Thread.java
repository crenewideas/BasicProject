package cn.pxl.capture02.subsection01;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Demo06Thread implements Runnable{
    private Demo06 demo06;

    @Override
    public void run() {
        demo06.doDemo();
    }
}
