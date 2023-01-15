package cn.pxl.capture02.subsection04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//创建一个空文件
public class Demo04CreEmptyOrTempFile {
    public static void demo01(){
        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02/newFile.txt");
        try {
            //在 capture02 下创建一个 newFile.txt 空文件。 如果当前路径不存在，那么会抛出： java.nio.file.NoSuchFileException
            //Files.createFile(path);
            //创建临时文件或临时目录
            Path capture02 = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02");
            Files.createTempFile(capture02,null,".txt");//创建了一个名为：311226141747805126.txt 的文件。如果prefix为空，那么文件名是个随机数。
            //创建一个临时目录 ， 名称 ：2220330602915691699 ；如果prefix为空，那么文件名是个随机数。
            Files.createTempDirectory(capture02,null);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed");
        }
    }
}
