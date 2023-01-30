package cn.pxl.niuke;

import java.util.Scanner;

//HJ2 计算某字符出现次数
//写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
//数据范围：1≤n≤1000
public class HJ2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = "";
        if (scanner.hasNextLine()){
            str = scanner.nextLine();
        }
        if(0 == str.length()){
            System.out.println(0);
            return;
        }
        String oneCharStr = "";
        if (scanner.hasNextLine()){
            oneCharStr = scanner.nextLine();
            scanner.close();
        }
        if(oneCharStr.length() == 0){
            System.out.println(0);
            return;
        }
        char[] charArray = str.toCharArray();
        char oneChar = oneCharStr.toCharArray()[0];
        char convertChar;
        int count = 0;
        //大写字母
        if (65 <= oneChar && oneChar <= 90) {
            //大写字母对应的小写字母
            convertChar = oneCharStr.toLowerCase().toCharArray()[0];
            for (char c : charArray) {
                if (oneChar == c || convertChar == c) count++;
            }
        }
        //小写字母
        else if (97 <= oneChar && oneChar <= 122) {
            //小写字母对应的大写字母
            convertChar = oneCharStr.toUpperCase().toCharArray()[0];
            for (char c : charArray) {
                if (oneChar == c || convertChar == c) count++;
            }
        //非大小写字母
        } else {
            for (char c : charArray) {
                if (oneChar == c) count++;
            }
        }
        System.out.println(count);
    }
}
