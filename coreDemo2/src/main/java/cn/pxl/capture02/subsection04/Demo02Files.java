package cn.pxl.capture02.subsection04;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//这些方法，用于处理中等长度的文本文件比较合适；这些方法可以便捷的对文本文件进行读写操作。
//文件长度较大，或者是二进制文件，那么应用输入/输出流读写。
public class Demo02Files {

    //利用Files读取普通文件的操作
    public static void readFileDemo()  {
        //通过相对路径获取path对象
        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02/Word3.txt");
        byte[] pathBytes = new byte[0];
        List<String> pathStr = null;
        Stream<String> pathLinesStream = null;
        try {
            //将文件读取为字节
            pathBytes = Files.readAllBytes(path);
            //将文件读取为字符串
            pathStr = Files.readAllLines(path);
            //将文件读取为流
            pathLinesStream = Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed");
        }
        System.out.println(pathBytes.length);//27
        System.out.println(pathStr.size());//1
        List<String> collect = pathLinesStream.collect(Collectors.toList());
        System.out.println(collect.size());//1
    }

    //利用Files写出普通文件的操作
    public static void writeFileDemo()  {
        Path path = Paths.get("coreDemo2/src/main/java/cn/pxl/capture02/Word3.txt");
        String content = "PXLNGU：彭笑良永不言败！";
        try {
            //向指定path路径的文件中写入字符串
            Files.write(path,content.getBytes(StandardCharsets.UTF_8));
            String appendStr = "是否依然孤独的转个不停";
            //向指定path路径的文件中追加字符串
            Files.write(path,appendStr.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            ArrayList<String> lines = new ArrayList<>();
            lines.add("第一行");
            lines.add("第二行");
            //向指定path路径的文件中追加多行字符串
            Files.write(path,lines,StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("failed!");
        }

    }
}
