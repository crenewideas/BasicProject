package cn.pxl.capture02.subsection01;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Demo04GetValue implements Runnable{

    private Demo04 demo04;

    @Override
    public void run() {
        demo04.getValue();
    }
}
