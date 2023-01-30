package cn.pxl.niuke;

import com.sun.tools.javac.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//HJ4 字符串分隔
//输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
//长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
public class HJ4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = "";
        if (scanner.hasNextLine()) {
            str = scanner.nextLine();
        }
        scanner.close();
        if(0 == str.length()) return;
        int forCount = str.length() / 8;
        for (int i = 0; i < forCount; i++) {
            System.out.println(str.substring(0,8));
            str = str.substring(8);
        }
        if(0 == str.length()) return;
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i = 0; i < 8 - str.length(); i++) {
            stringBuilder.append("0");
        }
        System.out.println(stringBuilder);
    }
}
