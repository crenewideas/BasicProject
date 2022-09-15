package cn.pxl.capture02.subsection01;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Demo04GetValueSynchronized implements Runnable{

    private Demo04 demo04;

    @Override
    public void run() {
        demo04.getValueSync();
    }
}
