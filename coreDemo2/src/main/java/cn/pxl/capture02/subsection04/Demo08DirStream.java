package cn.pxl.capture02.subsection04;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//使用目录流
public class Demo08DirStream {

    public static void doDemo(){
        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02");
        try {
            //第二个参数：利用  glob 模式来过滤文件。（具体含义参见书96页表格）
            DirectoryStream<Path> paths = Files.newDirectoryStream(path,"*.java");
            try {
                for (Path onePath : paths) {
                    //doSomeThing。
                }
            } finally {
                //用于关闭资源
                paths.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
