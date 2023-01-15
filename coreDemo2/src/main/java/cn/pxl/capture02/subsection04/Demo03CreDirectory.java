package cn.pxl.capture02.subsection04;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//创建文件目录
public class Demo03CreDirectory {

    public static void createDirectory(){
        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02/newDirectory");
        try {
            //创建单层目录：除最后一层目录外，其余目录应该存在，如果不存在，会报错：java.nio.file.NoSuchFileException
            Files.createDirectory(path);
            //创建多层目录：可以创建多层目录
//            Path secondDirectory = path.resolve("secondDirectory");
//            Files.createDirectories(secondDirectory);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
