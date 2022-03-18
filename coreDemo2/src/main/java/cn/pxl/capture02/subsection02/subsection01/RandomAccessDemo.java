package cn.pxl.capture02.subsection02.subsection01;

import cn.pxl.capture02.common.ReadFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class RandomAccessDemo {
    public static void test01() {
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

    //追加数据
    public static void test02() throws IOException {
        File file = ReadFile.getFile();
        /**以读写的方式建立一个RandomAccessFile对象**/
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        //获取RandomAccessFile对象文件指针的位置，初始位置是0
        System.out.println("RandomAccessFile文件指针的初始位置:"+accessFile.getFilePointer()); //指针位置：0
        accessFile.seek(accessFile.length());
        System.out.println("RandomAccessFile文件指针的末尾位置:"+accessFile.getFilePointer()); //指针位置：文件尾部
        accessFile.write("追加的数据\r\n".getBytes());
    }

    //向指定位置插入数据，是自己改造的功能，RandomAccessFile并不直接支持，需要新建一个缓冲区临时空间，
    //存数据，然后在写，因为一旦数据量上了级别，在任意位置插入数据，是很耗内存的，这个也就是为什么hadoop的HDFS文件系统，
    //只支持append的方式，而没有提供修改的操作。
    //任意位置插入数据
    public static void test03() throws IOException {
        File tmp = File.createTempFile("tmp", null);
        tmp.deleteOnExit();//在JVM退出时删除
        File file = ReadFile.getFile();
        /**以读写的方式建立一个RandomAccessFile对象**/
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        //创建一个临时文件夹来保存插入点后的数据
        FileOutputStream tmpOut=new FileOutputStream(tmp);
        FileInputStream tmpIn=new FileInputStream(tmp);
        //获取RandomAccessFile对象文件指针的位置，初始位置是0
        accessFile.seek(20);
        /**将插入点后的内容读入临时文件夹**/
        byte[] bytes = new byte[1024];
        //用于保存临时读取的字节数
        int hasRead=0;
        //循环读取插入点后的内容
        while((hasRead=accessFile.read(bytes))>0){
            // 将读取的数据写入临时文件中
            tmpOut.write(bytes, 0, hasRead);
        }
        //插入需要指定添加的数据
        accessFile.seek(20);//返回原来的插入处
        //追加需要追加的内容
        accessFile.write("追加的内容".getBytes());
        //最后追加临时文件中的内容 :
        while((hasRead = tmpIn.read(bytes))>0){
            accessFile.write(bytes,0,hasRead);
        }
    }
}
