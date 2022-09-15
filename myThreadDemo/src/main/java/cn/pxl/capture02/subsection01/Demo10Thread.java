package cn.pxl.capture02.subsection01;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Demo10Thread implements Runnable{
    @Override
    public void run() {
        //静态方法执行。
        Demo10.doDemo();
    }
}
