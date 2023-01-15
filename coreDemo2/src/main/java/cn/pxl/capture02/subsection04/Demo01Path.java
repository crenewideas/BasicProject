package cn.pxl.capture02.subsection04;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Demo01Path {

    public static void demo01(){
        //以根路径起始，称为绝对路径
        Path absolutePath = Paths.get("/Users", "pengxiaoliang", "Downloads","Document");
        Path fileName = absolutePath.getFileName();
        System.out.println(fileName);//Document

        //不以根路径起始，称为相对路径
        Path relativePath = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02/Word3.txt");
        System.out.println(relativePath.getFileName());//Word3.txt
        //相对路径相对的起始路径
        String basePathStr = System.getProperty("user.dir");
        System.out.println(basePathStr);// /Users/pengxiaoliang/IdeaProjects/IdeaProject/Basic/Project01/BasicProject

        //r = p.resolve(q):
        // 1.q为起始路径，结果为q；
        // 2.q不为起始路径，结果为：p + q。
        //创建起始路径
        Path basePath = Paths.get(basePathStr);
        //查找相对于basePath的路径。
        Path resolvePath = basePath.resolve("resolvePath");
        System.out.println(relativePath.getNameCount());//8，从起始路径开始，到 resolvePath，共8个层级。

        //解析父路径，产生兄弟路径：
        Path birthResolvePath = basePath.resolveSibling("resolvePath");
        System.out.println(birthResolvePath.getNameCount());//7 从起始路径开始，到 resolvePath，共7个层级
        // （/Users/pengxiaoliang/IdeaProjects/IdeaProject/Basic/Project01/resolvePath）：即 resolvePath 和 BasicProject 是兄弟路径。

        //q = p.relativize(r)。
        Path relativize = basePath.relativize(resolvePath);
        System.out.println(relativize.getNameCount());
        System.out.println(relativize.getFileName()); // resolvePath

        Path harryPath = Paths.get("home/harry");
        System.out.println(harryPath.getParent());//home
        System.out.println(harryPath.getFileName());//harry
        System.out.println(harryPath.getRoot());//null
        Path inputPath = Paths.get("home/fred/input.txt");
        Path relativizePath = harryPath.relativize(inputPath);
        System.out.println(relativizePath.getNameCount());

    }
}
