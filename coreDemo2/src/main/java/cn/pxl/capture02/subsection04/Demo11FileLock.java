package cn.pxl.capture02.subsection04;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//文件加锁，防止多个程序同时对一个文件进行修改。
public class Demo11FileLock {

    public static void doDemo(){
        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02/Word3.txt");
        try {
            FileChannel openChannel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE);
            //调用lock方法，为当前通道加锁，加锁后这个文件保持锁定状态，直到通道关闭或者调用 lock 对象对release方法。
            //1.当锁获取不到时，会阻塞，直到获取到锁。
            FileLock lock = openChannel.lock();
            //2.当锁获取不到时，会返回一个空 null 的对象。
            FileLock fileLock = openChannel.tryLock();
            //3.锁定文件的一部分，shared = true时，表示锁的方式是共享锁，允许多个进程从文件中读入，并阻止任何进程获得独占锁。？？？（允许多个进程读，但不允许任何一个进程加锁）？？。
            openChannel.lock(0,openChannel.size(),true);
            //4.shared = false 表示锁定文件的目的是读写。
            openChannel.tryLock(0,openChannel.size(),false);
            //调用 release 方法释放锁，或者通道关闭后释放锁。这段代码应放在try cache中。
            lock.release();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
