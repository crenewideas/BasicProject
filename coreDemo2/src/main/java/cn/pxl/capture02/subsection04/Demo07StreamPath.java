package cn.pxl.capture02.subsection04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Demo07StreamPath {

    public static void doDemo(){
        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02");
        try {
            //返回 path 目录下所有的 path 的 Stream。(不包含子目录)
            Stream<Path> list = Files.list(path);
            list.forEach(onePath -> {
                System.out.println(onePath.getFileName());
            });
            //subsection04
            //subsection03
            //newDirectory
            //transient.dta
            //subsection02
            //.DS_Store
            //Person.txt
            //readResolve.dta
            //common
            //subsection01
            //Word3.txt
            //Word4.txt
            //Word5.txt
            //Word6.txt
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doDemo02(){
        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02");
        try {
            //返回 path 目录下所有的 path 的 Stream。（包含子目录）;maxDepth:遍历树的深度。
            Stream<Path> list = Files.walk(path,2);
            list.forEach(onePath -> {
                System.out.println(onePath.getFileName());
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO 拷贝一个完整的目录
    public static void doCopyDemo(){
        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02");
        try {
            //返回 path 目录下所有的 path 的 Stream。（包含子目录）;maxDepth:遍历树的深度。
            Stream<Path> list = Files.walk(path);
            list.forEach(onePath -> {
                Path relativize = path.relativize(onePath);

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
