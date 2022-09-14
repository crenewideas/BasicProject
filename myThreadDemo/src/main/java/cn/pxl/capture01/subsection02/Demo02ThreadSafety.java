package cn.pxl.capture01.subsection02;

public class Demo02ThreadSafety {

    public static void main(String[] args) {
        //创建一个 Runnable 接口的实现 。
        ThreadSafetyTest threadSafetyTest = new ThreadSafetyTest();

        Thread threadA = new Thread(threadSafetyTest,"A");
        Thread threadB = new Thread(threadSafetyTest,"B");
        // threadSafetyTest 对象 只有一份，却同时在 threadA 和 threadB 两个线程中共享使用。
        // 由于 threadSafetyTest 对象只有一份，那么 threadSafetyTest 对象的 实例变量（实例字段） 也只有一份，在两个线程中共享。
        //这种情况会有线程安全问题。
        threadA.start();
        threadB.start();

        ThreadSafetyTest threadSafetyTest2 = new ThreadSafetyTest();
        Thread threadC = new Thread(threadSafetyTest2, "C");
        //threadC 中操作的对象是 threadSafetyTest2 ， 不与 threadA和threadB 共享任何变量，因此说 threadC 不存在线程安全问题。
        threadC.start();
    }

    private static class ThreadSafetyTest implements Runnable{
        //实例变量
        private int instanceCount = 100;
        @Override
        public void run() {
            //多个线程操作共享的 ThreadSafetyTest 对象的 instanceCount 变量时，要保证 修改逻辑的线程安全，可以使用synchronized 同步代码块。
            synchronized (this){
                while (instanceCount >0){
                    System.out.println("当前线程：" + Thread.currentThread().getName() +"，执行前结果为："+instanceCount);
                    instanceCount --;
                }
            }
        }
    }
}
