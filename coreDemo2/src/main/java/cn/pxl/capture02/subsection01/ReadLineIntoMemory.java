package cn.pxl.capture02.subsection01;

import cn.pxl.capture02.common.ReadFile;
import com.sun.org.apache.xalan.internal.xsltc.dom.AbsoluteIterator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ReadLineIntoMemory {
    public static void main(String[] args) {
        try {
            readLine();
        } catch (IOException e) {
            System.out.println("读取失败！");
        }
    }

    private static void readLine() throws IOException {
        File file = ReadFile.getFile();

        //将文件中的字节读入动态数组中
        List<String> strings = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        System.out.println(strings);
        //将文件中的字节按行读入流中
        Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()));

        //利用 BufferedReader 类处理文本
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }
    }
}
