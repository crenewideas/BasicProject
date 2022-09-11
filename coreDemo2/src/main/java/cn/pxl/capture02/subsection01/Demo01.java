package cn.pxl.capture02.subsection01;

import cn.pxl.capture02.common.ReadFile;

import java.io.*;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) {
        try {
            //inputStreamMethod();
            //readData();
            readDataFromBuffer();
        } catch (IOException e) {
            System.out.println("读取失败！");
        }
    }

    public static void inputStreamMethod() throws IOException {
        File file = ReadFile.getFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        //返回当前可读入的字节数量
        int available = fileInputStream.available();
        if(available > 0){
            System.out.println(available);
            //创建字节数组
            byte[] bytes = new byte[available];
            //将字节读入字节数组中去
            int read = fileInputStream.read(bytes);
            System.out.println(read);
            //可以查看读入的每个字节。
            for (byte aByte : bytes) {
                System.out.println(aByte);
            }
        }


    }

    public static void readData() throws IOException {
        File file = ReadFile.getFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        //dataInputStream,用于读取数值，入参是一次读取一个字节的fileInputStream
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        int v = dataInputStream.readInt();
        System.out.println(v);
    }

    public static void readDataFromBuffer() throws IOException {
        File file = ReadFile.getFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
        int read = dataInputStream.read();
        System.out.println(read);

    }

    //中介流
    public static void intermediateStream() throws IOException {
        File file = ReadFile.getFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        PushbackInputStream pushbackInputStream = new PushbackInputStream(bufferedInputStream);
        //预读取一个字节
        int read = pushbackInputStream.read();
        //如果下一个字节不是1，那么就推回到流中。
        if(read != 1) pushbackInputStream.unread(read);
    }

    //将中介流作为参数传给数值流，利用pushbackInputStream进行回推，利用dataInputStream获取数值。
    public static void dataIntermediateStream() throws IOException {
        File file = ReadFile.getFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        PushbackInputStream pushbackInputStream = new PushbackInputStream(bufferedInputStream);
        DataInputStream dataInputStream = new DataInputStream(pushbackInputStream);
        //预读取一个字节
        int read = pushbackInputStream.read();
        //如果下一个字节不是1，那么就推回到流中。
        if(read != 1) pushbackInputStream.unread(read);
    }

}


