package cn.pxl.capture02.subsection01;

import cn.pxl.capture02.common.ReadFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessDemo {
    public static void main(String[] args) {
        File file = ReadFile.getFile();
        try {
            //读取任意位置的数据，代码如下
            RandomAccessFile accessFile = new RandomAccessFile(file, "r");
            /**
             * model各个参数详解
             * r 代表以只读方式打开指定文件
             * rw 以读写方式打开指定文件
             * rws 读写方式打开，并对内容或元数据都同步写入底层存储设备
             * rwd 读写方式打开，对文件内容的更新同步更新至底层存储设备
             *
             * **/
            //获取RandomAccessFile对象文件指针的位置，初始位置是0
            System.out.println("RandomAccessFile文件指针的初始位置:"+accessFile.getFilePointer()); //指针位置：0
            accessFile.seek(10);    //移动文件指针位置。
            System.out.println("RandomAccessFile文件指针的初始位置:"+accessFile.getFilePointer()); //指针位置：10
            byte[]  buff = new byte[1024]; //用于保存实际读取的字节数
            int hasRead=0;               //用于保存实际读取的字节数
            //循环读取
            while((hasRead = accessFile.read(buff)) > 0){
                //打印读取的内容,并将字节转为字符串输入
                System.out.println(new String(buff,0,hasRead));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
