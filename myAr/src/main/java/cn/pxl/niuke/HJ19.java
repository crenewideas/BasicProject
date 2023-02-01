package cn.pxl.niuke;

import java.util.ArrayList;
import java.util.Scanner;

//HJ18 识别有效的IP地址和掩码并进行分类统计
//请解析IP地址和对应的掩码，进行分类识别。要求按照 A/B/C/D/E 类地址归类，不合法的地址和掩码单独归类。

//所有的IP地址划分为 A,B,C,D,E 五类
//A类地址从 1.0.0.0   到 126.255.255.255;
//B类地址从 128.0.0.0 到 191.255.255.255;
//C类地址从 192.0.0.0 到 223.255.255.255;
//D类地址从 224.0.0.0 到 239.255.255.255；
//E类地址从 240.0.0.0 到 255.255.255.255

//私网IP范围是：
//从10.0.0.0到10.255.255.255
//从172.16.0.0到172.31.255.255
//从192.168.0.0到192.168.255.255

//输入描述：多行字符串。每行一个IP地址和掩码，用~隔开。
//输出描述：统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。

public class HJ19 {
    public static void main(String[] args) {
        String str = "1...1";
        String[] split = str.split("\\.");
        for (String s : split) {
            System.out.println(s);
        }
    }

}
