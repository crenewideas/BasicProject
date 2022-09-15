package cn.pxl.capture02.subsection02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Demo05 {
    //不是volatile变量
    private boolean stopFlag = false;
    //是volatile变量，可以增加变量在多个线程之间的可见性。
    private volatile boolean isStopFlag = false;

    public void setIsStopFlag(boolean isStopFlag){
        this.isStopFlag = isStopFlag;
    }

    public void doDemoWithoutVolatile(){
        System.out.println("开始执行死循环");
        while (!stopFlag){
        }
        System.out.println("死循环执行结束");
    }

    public void doDemoWithVolatile(){
        System.out.println("开始执行死循环");
        while (!isStopFlag){
        }
        System.out.println("死循环执行结束");
    }

    public void doDemoWithoutVolatileAndWithSynchronized(){
        //使用 Synchronized 关键字增加可见性。
        System.out.println("开始执行死循环");
        while (!stopFlag){
            //用于增加可见性，使得当前线程能识别到其他线程更改的 stopFlag。
            synchronized ("a"){
            }
        }
        System.out.println("死循环执行结束");
    }

}

