package cn.pxl;

import cn.pxl.capture04.subsection01.Cap4Demo01;
import cn.pxl.capture04.subsection01.Cap4Demo02;
import lombok.SneakyThrows;
import org.junit.Test;
import sun.java2d.ReentrantContextProviderTL;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Capture04Test {

    //使用 ReentrantLock 实现线程同步执行。
    @Test
    public void doDemo01(){

        //ThreadName:Thread-0:1
        //ThreadName:Thread-0:2
        //ThreadName:Thread-0:3
        //ThreadName:Thread-0:4
        //ThreadName:Thread-0:5
        //ThreadName:Thread-1:1
        //ThreadName:Thread-1:2
        //ThreadName:Thread-1:3
        //ThreadName:Thread-1:4
        //ThreadName:Thread-1:5

        Cap4Demo01 cap4Demo01 = new Cap4Demo01();
        Thread thread01 = new Thread(cap4Demo01::demoTest);
        Thread thread02 = new Thread(cap4Demo01::demoTest);

        thread01.start();
        thread02.start();
    }

    //多个线程执行，由于锁对象是同一个，多个线程获取同一个锁对象会有先后顺序。
    @Test
    public void doDemo02(){

        //demoTest02 - ThreadName:Thread-2:1
        //demoTest02 - ThreadName:Thread-2:2
        //demoTest02 - ThreadName:Thread-2:3
        //demoTest02 - ThreadName:Thread-2:4
        //demoTest02 - ThreadName:Thread-2:5
        //demoTest02 - ThreadName:Thread-3:1
        //demoTest02 - ThreadName:Thread-3:2
        //demoTest02 - ThreadName:Thread-3:3
        //demoTest02 - ThreadName:Thread-3:4
        //demoTest02 - ThreadName:Thread-3:5
        //demoTest01 - ThreadName:Thread-0:1
        //demoTest01 - ThreadName:Thread-0:2
        //demoTest01 - ThreadName:Thread-0:3
        //demoTest01 - ThreadName:Thread-0:4
        //demoTest01 - ThreadName:Thread-0:5
        //demoTest01 - ThreadName:Thread-1:1
        //demoTest01 - ThreadName:Thread-1:2
        //demoTest01 - ThreadName:Thread-1:3
        //demoTest01 - ThreadName:Thread-1:4
        //demoTest01 - ThreadName:Thread-1:5

        Cap4Demo01 cap4Demo01 = new Cap4Demo01();
        Thread thread01 = new Thread(cap4Demo01::demoTest);
        Thread thread02 = new Thread(cap4Demo01::demoTest);
        Thread thread03 = new Thread(cap4Demo01::demoTest2);
        Thread thread04 = new Thread(cap4Demo01::demoTest2);

        thread01.start();
        thread02.start();
        thread03.start();
        thread04.start();
    }

    // signal(信号，用于唤醒某一线程) signalAll 用于唤醒所有等待线程
    @Test
    public void doDemo03(){
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        // await 方法的作用和同步中的 wait 方法的作用相同。
        Thread thread01 = new Thread(()->{
            try {
                reentrantLock.lock();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "获取到锁，开始等待并释放锁对象");
                //必须在 await 调用之前获取锁对象。否则会抛出 IllegalMonitorStateException 异常。
                condition.await();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "已被唤醒，继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "已执行完毕，锁已释放");
                reentrantLock.unlock();
            }
        });

        Thread thread02 = new Thread(()->{
            System.out.println("当前线程：" + Thread.currentThread().getName() + "获取到锁，执行业务花费2s");
            try {
                reentrantLock.lock();
                Thread.sleep(2000);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "业务执行完毕，唤醒其他某一个正在等待的线程");
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        });

        thread01.start();
        thread02.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //condition.signalAll();唤醒所有线程
    @Test
    public void doDemo04(){
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Thread thread01 = new Thread(()->{
            reentrantLock.lock();
            try {
                condition.await();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "已被唤醒，继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        });

        Thread thread02 = new Thread(()->{
            reentrantLock.lock();
            try {
                condition.await();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "已被唤醒，继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        });

        Thread thread03 = new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "唤醒所有线程");
                condition.signalAll();
            } finally {
                reentrantLock.unlock();
            }
        });

        thread01.start();
        thread02.start();
        try {
            Thread.sleep(1000);
            thread03.start();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // 一个Condition 是一组锁，condition.signalAll() 唤醒组内所有线程。多个condition对象对应多个组。
    //thread01和thread02对应一个condition，唤醒时 condition.signalAll()只会唤醒 thread01和thread02。
    //thread03对应一个condition02，唤醒时 condition.signalAll() 不会唤醒 thread03，因为不属于 condition组。
    @Test
    public void doDemo05() {

        //当前线程：main睡眠5秒钟
        //当前线程：Thread-3唤醒condition组内所有线程
        //当前线程：Thread-0已被唤醒，继续执行
        //当前线程：Thread-1已被唤醒，继续执行

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Condition condition02 = reentrantLock.newCondition();

        Thread thread01 = new Thread(() -> {
            reentrantLock.lock();
            try {
                condition.await();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "已被唤醒，继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });

        Thread thread02 = new Thread(() -> {
            reentrantLock.lock();
            try {
                condition.await();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "已被唤醒，继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });

        Thread thread03 = new Thread(() -> {
            reentrantLock.lock();
            try {
                condition02.await();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "已被唤醒，继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });

        Thread thread04 = new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "唤醒condition组内所有线程");
                condition.signalAll();
            } finally {
                reentrantLock.unlock();
            }
        });

        thread01.start();
        thread02.start();
        thread03.start();
        try {
            Thread.sleep(1000);
            thread04.start();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //生产者、消费者一对一交替输出
    @Test
    public void doDemo06() {
        //main线程：main睡眠5秒钟
        //生产一个值
        //消费一个值
        //生产一个值
        //消费一个值
        //生产一个值
        //消费一个值

        Cap4Demo02 cap4Demo02 = new Cap4Demo02();
        //生产者
        Thread thread01 = new Thread(cap4Demo02.getRunnable01());

        //消费者
        Thread thread02 = new Thread(cap4Demo02.getRunnable02());

        thread01.start();
        thread02.start();
        try {
            System.out.println("main线程：" + Thread.currentThread().getName() + "睡眠5秒钟");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //公平锁与非公平锁，公平锁
    @SneakyThrows
    @Test
    public void doDemo07(){

        //先start的，比后start的要执行的早。后启动的线程，一定不能先抢到锁。
        //01++
        //01++
        //01++
        //01++
        //01++
        //02++
        //02++
        //02++
        //02++
        //02++

        //fair = true 表示公平锁
        ReentrantLock reentrantLock = new ReentrantLock(true);
        Thread[] threads01 = new Thread[5];
        Thread[] threads02 = new Thread[5];
        for (int i = 0; i < threads01.length; i++) {
            threads01[i] = new Thread(()->{
                reentrantLock.lock();
                System.out.println("01++");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            });
        }
        for (int i = 0; i < threads02.length; i++) {
            threads02[i] = new Thread(()->{
                reentrantLock.lock();
                System.out.println("02++");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            });
        }
        for (Thread thread : threads01) {
            thread.start();
        }
        Thread.sleep(1000);
        for (Thread thread : threads02) {
            thread.start();
        }
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //公平锁与非公平锁，非公平锁
    @SneakyThrows
    @Test
    public void doDemo08(){

        //先start的，不一定比后 start 的要执行的早。后启动的线程，依然有可能先抢到锁。
        //这里的先start，指的是 程序上的顺序。如这里threads01在程序中执行时，threads02先执行。
        //01++
        //02++
        //01++
        //01++
        //.....

        //fair = true 表示公平锁
        ReentrantLock reentrantLock = new ReentrantLock(false);
        Thread[] threads01 = new Thread[10];
        Thread[] threads02 = new Thread[10];
        for (int i = 0; i < threads01.length; i++) {
            threads01[i] = new Thread(()->{
                reentrantLock.lock();
                System.out.println("01++");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            });
        }
        for (int i = 0; i < threads02.length; i++) {
            threads02[i] = new Thread(()->{
                reentrantLock.lock();
                System.out.println("02++");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            });
        }
        for (Thread thread : threads01) {
            thread.start();
        }
        Thread.sleep(500);
        for (Thread thread : threads02) {
            thread.start();
        }
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //holdCount当前线程保持锁定的个数，即调用lock()的次数。
    //这种情况，可以发生在锁重入的情况下。
    @SneakyThrows
    @Test
    public void doDemo09(){

        //当前线程：Thread-0；持有锁的个数是：1
        //当前线程：Thread-0；持有锁的个数是：2
        //当前线程：Thread-0；持有锁的个数是：1
        //当前线程：Thread-0；持有锁的个数是：0

        ReentrantLock reentrantLock = new ReentrantLock(true);
        new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "；持有锁的个数是：" + reentrantLock.getHoldCount());
                reentrantLock.lock();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "；持有锁的个数是：" + reentrantLock.getHoldCount());
                reentrantLock.unlock();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "；持有锁的个数是：" + reentrantLock.getHoldCount());
            }
            finally {
                reentrantLock.unlock();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "；持有锁的个数是：" + reentrantLock.getHoldCount());
            }
        }).start();

        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //getQueueLength ，返回 lock 对象中，处于等待状态的数量(sleep,await都算)
    @SneakyThrows
    @Test
    public void doDemo10(){
        ReentrantLock reentrantLock = new ReentrantLock(true);
        Condition condition = reentrantLock.newCondition();
        Thread[] threads01 = new Thread[5];
        for (int i = 0; i < 4; i++) {
            threads01[i] = new Thread(()->{
                reentrantLock.lock();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            });
        }
        threads01[4] = new Thread(()->{
            reentrantLock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        });
        //创建了5个线程，第一个处于 sleep 状态时，其余的线程处于等待状态。
        for (Thread thread : threads01) {
            thread.start();
        }
        Thread.sleep(1000);
        //当前线程：main中，正在等待的线程个数为：4
        System.out.println("当前线程：" + Thread.currentThread().getName() + "中，正在等待的线程个数为：" + reentrantLock.getQueueLength());
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //getWaitQueueLength 返回 condition 组中 处于 await 状态的线程个数。
    @SneakyThrows
    @Test
    public void doDemo11(){
        //当前线程：main中，condition 中正在等待的线程个数为：3
        //当前线程：main中，condition 中正在等待的线程个数为：0
        //当前线程：main中，condition2中正在等待的线程个数为：1
        //当前线程：main中，condition2中正在等待的线程个数为：0
        ReentrantLock reentrantLock = new ReentrantLock(true);
        Condition condition = reentrantLock.newCondition();
        Condition condition2 = reentrantLock.newCondition();
        Thread[] threads01 = new Thread[5];
        for (int i = 0; i < 4; i++) {
            threads01[i] = new Thread(()->{
                reentrantLock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            });
        }
        for (int i = 3 ; i < 5 ; i++){
            threads01[i] = new Thread(()->{
                reentrantLock.lock();
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            });
        }
        //创建了5个线程，第一个处于 sleep 状态时，其余的线程处于等待状态。
        for (Thread thread : threads01) {
            thread.start();
        }
        reentrantLock.lock();
        System.out.println("当前线程：" + Thread.currentThread().getName() + "中，condition 中正在等待的线程个数为：" + reentrantLock.getWaitQueueLength(condition));
        condition.signalAll();
        System.out.println("当前线程：" + Thread.currentThread().getName() + "中，condition 中正在等待的线程个数为：" + reentrantLock.getWaitQueueLength(condition));
        System.out.println("当前线程：" + Thread.currentThread().getName() + "中，condition2中正在等待的线程个数为：" + reentrantLock.getWaitQueueLength(condition2));
        condition2.signalAll();
        System.out.println("当前线程：" + Thread.currentThread().getName() + "中，condition2中正在等待的线程个数为：" + reentrantLock.getWaitQueueLength(condition2));
        reentrantLock.unlock();
        Thread.sleep(1000);
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //某线程是否处于等待状态： reentrantLock.hasQueuedThread(threads02));
    //当前锁对象中是否有处于等待状态的线程：reentrantLock.hasQueuedThreads());
    //当前锁对象的组中是否有处于等待状态的线程：reentrantLock.hasWaiters(condition);
    @SneakyThrows
    @Test
    public void doDemo12(){
        ReentrantLock reentrantLock = new ReentrantLock(true);
        Condition condition = reentrantLock.newCondition();
        Thread threads01 = new Thread(()->{
            reentrantLock.lock();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        });
        Thread threads02 = new Thread(()->{
            reentrantLock.lock();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        });
        Thread threads03 = new Thread(()->{
            reentrantLock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        });
        threads01.start();
        Thread.sleep(500);
        threads02.start();
        Thread.sleep(500);
        threads03.start();
        Thread.sleep(500);
        //threads02是否处于等待状态：true
        //当前锁对象中是否有处于等待状态的线程：true
        //当前锁对象的组中是否有处于等待状态的线程：true : 其实是condition 组中的 threads03在处于等待状态。
        System.out.println("threads02是否处于等待状态：" + reentrantLock.hasQueuedThread(threads02));
        System.out.println("当前锁对象中是否有处于等待状态的线程：" + reentrantLock.hasQueuedThreads());
        reentrantLock.lock();
        System.out.println("当前锁对象的组中是否有处于等待状态的线程：" + reentrantLock.hasWaiters(condition));
        //当前锁对象是否是公平锁：true
        System.out.println("当前锁对象是否是公平锁：" + reentrantLock.isFair());
        //当前线程是否保持此锁：true
        System.out.println("当前线程是否保持此锁：" + reentrantLock.isHeldByCurrentThread());
        System.out.println("此锁是否由任意线程保持，并没有释放：" + reentrantLock.isLocked());
        reentrantLock.unlock();
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //interrupted方法打断
    //这个案例中，main线程用interrupted()打断thread02的等待，但显然，没有成功，thread02依然在等待thread01执行成功之后才执行。
    @SneakyThrows
    @Test
    public void doDemo13(){
        //当前线程：thread01睡眠2秒钟
        //当前线程：main开始打断thread02
        //当前线程：main睡眠5秒钟
        //当前线程：thread01睡眠2秒钟结束
        //当前线程：thread02开始执行
        ReentrantLock reentrantLock = new ReentrantLock(true);
        Thread thread01 = new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠2秒钟");
                Thread.sleep(2000);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠2秒钟结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        },"thread01");

        Thread thread02 = new Thread(()->{
            reentrantLock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "开始执行");
            reentrantLock.unlock();
        },"thread02");

        thread01.start();
        Thread.sleep(500);
        thread02.start();
        Thread.sleep(500);
        //主线程thread02处于等待期间，打断其执行
        System.out.println("当前线程：" + Thread.currentThread().getName() + "开始打断thread02");
        thread02.interrupt();
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //interrupted方法打断
    //这个案例中，main线程用interrupted()打断 thread02 的等待，并且成功了，thread02 在等待 thread01 执行时候被打断，并且抛出了异常。
    //reentrantLock.lockInterruptibly(); 某个线程，尝试获取锁，没有获取到并阻时，其他线程调用interrupted()打断这个阻塞的线程时，阻塞的线程将会抛出异常：java.lang.IllegalMonitorStateException
    @SneakyThrows
    @Test
    public void doDemo14(){
        //当前线程：thread01睡眠2秒钟
        //当前线程：thread02开始执行
        //当前线程：main开始打断thread02
        //java.lang.InterruptedException
        //Exception in thread "thread02" java.lang.IllegalMonitorStateException
        //当前线程：thread01睡眠2秒钟结束
        //当前线程：main睡眠5秒钟
        ReentrantLock reentrantLock = new ReentrantLock(true);
        Thread thread01 = new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠2秒钟");
                Thread.sleep(2000);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠2秒钟结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        },"thread01");

        Thread thread02 = new Thread(()->{
            System.out.println("当前线程：" + Thread.currentThread().getName() + "开始执行");
            try {
                reentrantLock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        },"thread02");

        thread01.start();
        Thread.sleep(500);
        thread02.start();
        Thread.sleep(500);
        //主线程thread02处于等待期间，打断其执行
        System.out.println("当前线程：" + Thread.currentThread().getName() + "开始打断thread02");
        thread02.interrupt();
        try {
            reentrantLock.lockInterruptibly();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //用 tryLock 嗅探是否能取得锁，返回一个boolean
    // tryLock 同样可以被打断
    @SneakyThrows
    @Test
    public void doDemo15(){
        //当前线程：thread01睡眠2秒钟
        //当前线程：thread02开始执行
        //当前线程：main开始打断thread02
        //java.lang.InterruptedException
        //Exception in thread "thread02" java.lang.IllegalMonitorStateException
        //当前线程：thread01睡眠2秒钟结束
        //当前线程：main睡眠5秒钟
        ReentrantLock reentrantLock = new ReentrantLock(true);
        Thread thread01 = new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠2秒钟");
                Thread.sleep(2000);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠2秒钟结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        },"thread01");

        Thread thread02 = new Thread(()->{
            System.out.println("当前线程：" + Thread.currentThread().getName() + "开始执行");
            if (reentrantLock.tryLock()) {
                reentrantLock.lock();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "已经获取到锁");
                reentrantLock.unlock();
            }else {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "没有获取到锁，已被其他线程占用");
            }

        },"thread02");

        thread01.start();
        Thread.sleep(500);
        thread02.start();
        Thread.sleep(500);
        //主线程thread02处于等待期间，打断其执行
        System.out.println("当前线程：" + Thread.currentThread().getName() + "开始打断thread02");
        thread02.interrupt();
        try {
            reentrantLock.lockInterruptibly();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //用 tryLock 嗅探是否能取得锁，返回一个boolean
    @SneakyThrows
    @Test
    public void doDemo16(){
        //当前线程：thread01睡眠2秒钟
        //当前线程：main睡眠5秒钟
        //当前线程：thread02开始执行
        //当前线程：thread02没有获取到锁，已被其他线程占用
        //当前线程：thread01睡眠2秒钟结束
        ReentrantLock reentrantLock = new ReentrantLock(true);
        Thread thread01 = new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠2秒钟");
                Thread.sleep(2000);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠2秒钟结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        },"thread01");

        Thread thread02 = new Thread(()->{
            System.out.println("当前线程：" + Thread.currentThread().getName() + "开始执行");
            if (reentrantLock.tryLock()) {
                reentrantLock.lock();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "已经获取到锁");
                reentrantLock.unlock();
            }else {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "没有获取到锁，已被其他线程占用");
            }

        },"thread02");

        thread01.start();
        Thread.sleep(500);
        thread02.start();
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doDemo00(){
        Cap4Demo01 cap4Demo01 = new Cap4Demo01();
        Thread thread01 = new Thread(()->{

        });

        Thread thread02 = new Thread(()->{

        });

        thread01.start();
        thread02.start();

        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "睡眠5秒钟");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
