package cn.pxl.capture02.subsection01;

import cn.pxl.capture02.common.ReadFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadOrWriteText {
    public static void main(String[] args) {
        try {
            //inputStreamReader();
            inputStreamReaderAutoFlush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed");
        }
    }

    //默认情况下禁止自动冲刷时，创建PrintWriter的方式
    private static void inputStreamReader() throws IOException {
        //InputStreamReader inputStreamReader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
//        FileInputStream fileInputStream = new FileInputStream(ReadFile.getFile());
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        PrintWriter printWriter = new PrintWriter("coreDemo2/src/main/java/cn/pxl/capture02/subsection01/Word2.txt");
        String name = "彭笑良";
        String age = "25";
        //这些字符将被转换为字节，并最终写入到Word2.txt中。这种创建对象的方式，自动冲刷缓冲区是禁止的。
        printWriter.println(name);
        printWriter.println(" ");
        printWriter.println(age);
        printWriter.close();
    }

    //开启自动冲刷
    private static void inputStreamReaderAutoFlush() throws IOException {
        //InputStreamReader inputStreamReader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
//        FileInputStream fileInputStream = new FileInputStream(ReadFile.getFile());
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream("coreDemo2/src/main/java/cn/pxl/capture02/subsection01/Word2.txt"),StandardCharsets.UTF_8));
        String name = "彭笑良";
        String age = "25";
        //这些字符将被转换为字节，并最终写入到Word2.txt中。这种创建对象的方式，自动冲刷缓冲区是禁止的。
        printWriter.println(name);
        printWriter.println(" ");
        printWriter.println(age);
        printWriter.close();
    }

}
