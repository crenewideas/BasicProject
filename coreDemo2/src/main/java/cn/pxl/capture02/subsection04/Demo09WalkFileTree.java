package cn.pxl.capture02.subsection04;

import cn.pxl.capture02.subsection04.entity.SimpleFIleVisitorChild;

import java.io.IOException;
import java.nio.file.*;

public class Demo09WalkFileTree {

    public static void doDemo(){
        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02");
        SimpleFIleVisitorChild simpleFIleVisitorChild = new SimpleFIleVisitorChild();
        try {
            Files.walkFileTree(path,simpleFIleVisitorChild);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
