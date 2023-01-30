package cn.pxl.niuke;

import java.util.Scanner;

//HJ5 字符串分隔
//写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
//数据范围：保证结果在 1 <= n <= 2^31-1
public class HJ5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = "";
        if (scanner.hasNextLine()) {
            str = scanner.nextLine();
        }
        scanner.close();
        if(0 == str.length()) return;
        String substring = str.substring(2);
        System.out.println(Long.parseLong(substring,16));
    }
}
