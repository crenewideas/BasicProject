package cn.pxl.capture02.subsection04;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Demo10MemoryMapFile {
    public static void doDemo(){

        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02/Word3.txt");
        try {
            //获取一个通道
            //if: Exception in thread "main" java.nio.channels.NonWritableChannelException：
            //If you want READ_WRITE when mapping the file you need "rw" when creating the RandomAccessFile you are ultimately getting it from
            FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE,StandardOpenOption.READ);
            //第一个参数：映射模式：
            //FileChannel.MapMode.PRIVATE;缓冲区可写，但是任何修改，对于缓冲区来说是私有的，不会传播到文件中。
            //FileChannel.MapMode.READ_ONLY;缓冲区只读，不可写。任何写入缓冲区，都会报错：ReadOnlyBufferException
            //FileChannel.MapMode.READ_WRITE;
            MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.PRIVATE, 0L, channel.size());
            //顺序遍历字节缓冲区：
            while (byteBuffer.hasRemaining()) {
                //∂System.out.println(byteBuffer.getInt());用来读取在文件中存储为二进制的基本类型值。
                System.out.println(byteBuffer.get());
            }

            //随机遍历字节缓冲区
//            for (int i = 0; i < byteBuffer.limit(); i++) {
//                System.out.println(byteBuffer.get(i));
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
