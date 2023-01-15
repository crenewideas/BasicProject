package cn.pxl.capture02.subsection04;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;

public class Demo05MoveCopyOrDeleteFile {

    public static void doDemo(){
        Path originPath = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02/Word3.txt");
        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02/newDirectory/Word3.txt");
        //拷贝 originPath 到指定路径。
        try {
            //如果路径已经存在了，那么会拷贝失败；如果想要覆盖已有的目标路径，第三个参数：StandardCopyOption.REPLACE_EXISTING；
            //                             如果想要复制所有的文件属性，那么：StandardCopyOption.COPY_ATTRIBUTES
            //Files.copy(originPath,path, StandardCopyOption.REPLACE_EXISTING);
            //Path toPath = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02/newDirectory/Word2.txt");
            //第三个参数：保证移动的原子性。
            //Files.move(originPath,toPath,StandardCopyOption.ATOMIC_MOVE);
            //Files.move(toPath,originPath,StandardCopyOption.ATOMIC_MOVE);

            //将输入流复制到Path中。表示：将输入流中的数据写出到磁盘中的文件。
            FileInputStream fileInputStream = new FileInputStream("coreDemo2/src/main/java/cn/pxl/capture02/Word3.txt");
            Path newPath = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02/Word6.txt");
            //拷贝 fileInputStream 流中的数据到 path 指定的文件中。
            Files.copy(fileInputStream,newPath);

            //将 path 指定的文件中的内容读入 字节输出流中。
            FileOutputStream fileOutputStream = new FileOutputStream("coreDemo2/src/main/java/cn/pxl/capture02/Word5.txt");
            //拷贝 newPath 中的文件数据，写出到 字节输出流中。
            Files.copy(newPath,fileOutputStream);

            //删除指定 path 目录的文件。
            Files.delete(newPath);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed");
        }
    }

}
