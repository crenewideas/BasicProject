package cn.pxl.niuke;

import java.util.Scanner;

//HJ1 字符串最后一个单词的长度
//计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）
public class HJ1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String finalLine = "";
        while (in.hasNextLine()) {
            finalLine = in.nextLine();
        }
        if(0 == finalLine.length()){
            System.out.println(0);
            return;
        }
        if (!finalLine.contains(" ")) {
            System.out.println(finalLine.length());
            return;
        }
        String[] splitArray = finalLine.split(" ");
        System.out.println(splitArray[splitArray.length - 1].length());
    }
}
