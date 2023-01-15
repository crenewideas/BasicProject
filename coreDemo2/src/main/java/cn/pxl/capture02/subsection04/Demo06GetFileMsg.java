package cn.pxl.capture02.subsection04;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserPrincipal;

//获取文件信息
public class Demo06GetFileMsg {

    public static void doDemo(){
        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02/Word4.txt");
        try {
            //获取文件的字节数
            long size = Files.size(path);//86
            System.out.println(size);

            //文件是否被隐藏
            boolean hidden = Files.isHidden(path);
            //文件是否可读性
            boolean readable = Files.isReadable(path);
            //文件是否可写
            boolean writable = Files.isWritable(path);
            //文件是否可执行
            boolean executable = Files.isExecutable(path);
            // isRegularFile() is a static method of the FileUtils class that is used to check whether or not a given file is a regular file.
            //是否是一个普通文件
            boolean regularFile = Files.isRegularFile(path);
            //是否是一个路径
            boolean directory = Files.isDirectory(path);
            //是否是软链接
            //A symbolic link, also termed a soft link, is a special kind of file that points to another file
            boolean symbolicLink = Files.isSymbolicLink(path);

            //获取文件拥有者的实例
            UserPrincipal owner = Files.getOwner(path);
            //获取文件的属性信息
            Object attribute = Files.readAttributes(path, BasicFileAttributes.class);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed");
        }

    }

}
