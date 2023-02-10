package cn.pxl.niuke;

//描述 : 对输入的字符串进行加解密，并输出。
//加密方法为：
//当内容是英文字母时则用该英文字母的后一个字母替换，同时字母变换大小写,如字母a时则替换为B；字母Z时则替换为a；
//当内容是数字时则把该数字加1，如0替换1，1替换2，9替换0；
//其他字符不做变化。
//解密方法为加密的逆过程。
//数据范围：输入的两个字符串长度满足 1 \le n \le 1000 \1≤n≤1000  ，保证输入的字符串都是只由大小写字母或者数字组成

//输入描述：
//第一行输入一串要加密的密码
//第二行输入一串加过密的密码

//输出描述：
//第一行输出加密后的字符
//第二行输出解密后的字符

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HJ29 {

    public static void main(String[] args) throws IOException {

//        ArrayList<String> arrayList = new ArrayList<>();
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/pengxiaoliang/Downloads/string.txt"));
//        String str;
//        while ((str = bufferedReader.readLine()) != null){
//            arrayList.add(str);
//        }
//        bufferedReader.close();
//        System.out.println(arrayList);

        ArrayList<String> arrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            arrayList.add(nextLine);
        }
        String originStr = arrayList.get(0);
        String codedStr = arrayList.get(1);
        System.out.println(encodOriginStr(originStr));
        System.out.println(decodStr(codedStr));
    }

    //解密
    private static String decodStr(String codedStr) {
        if(codedStr.isEmpty()) System.out.println("");
        char[] chars = codedStr.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if(Character.isLetter(aChar)){
                if(97 == (int)aChar){
                    builder.append("Z");
                }else if(65 == (int)aChar){
                    builder.append("z");
                }else {
                    if(Character.isUpperCase(aChar)){
                        builder.append(Character.toChars(Character.toLowerCase(aChar - 1)));
                    }else {
                        builder.append(Character.toChars(Character.toUpperCase(aChar - 1)));
                    }
                }
            }else if(Character.isDigit(aChar)){
                if(48 == aChar) {
                    builder.append(9);
                }else{
                    builder.append(Character.toChars(aChar - 1));
                }
            }else {
                builder.append(aChar);
            }
        }
        return builder.toString();

    }

    private static String encodOriginStr(String originStr) {
        if(originStr.isEmpty()) System.out.println("");
        char[] chars = originStr.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if(Character.isLetter(aChar)){
                if(122 == (int)aChar){
                    builder.append("A");
                }else if(90 == (int)aChar){
                    builder.append("a");
                }else {
                    if(Character.isUpperCase(aChar)){
                        builder.append(Character.toChars(Character.toLowerCase(aChar + 1)));
                    }else {
                        builder.append(Character.toChars(Character.toUpperCase(aChar + 1)));
                    }
                }
            }else if(Character.isDigit(aChar)){
                if(57 == aChar) {
                    builder.append(0);
                }else{
                    builder.append(Character.toChars(aChar + 1));
                }
            }else {
                builder.append(aChar);
            }
        }
        return builder.toString();
    }


}



