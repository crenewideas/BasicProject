package cn.pxl.niuke;

//HJ21 现在有一种密码变换算法。
//九键手机键盘上的数字与字母的对应： 1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9, 0--0，把密码中出现的小写字母都变成九键键盘对应的数字，如：a 变成 2，x 变成 9.
//而密码中出现的大写字母则变成小写之后往后移一位，如：X ，先变成小写，再往后移一位，变成了 y ，例外：Z 往后移是 a 。
//数字和其它的符号都不做变换。

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HJ21 {
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

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            String oneStr = arrayList.get(i);
            for (char aChar : oneStr.toCharArray()) {
                builder.append(convert(aChar));
            }
        }
        System.out.println(builder);
    }

    private static String convert(char aChar){
        //abc
        if(aChar >= 97 && aChar < 100){
            return "2";
        //def
        }else if(aChar >= 100 && aChar < 103){
            return "3";

        //ghi
        }else if(aChar >= 103 && aChar < 106){
            return "4";

        //jkl
        }else if(aChar >= 106 && aChar < 109){
            return "5";

        //mno
        }else if(aChar >= 109 && aChar < 112){
            return "6";

        //pqrs
        }else if(aChar >= 112 && aChar < 116){
            return "7";

        //tuv
        }else if(aChar >= 116 && aChar < 119){
            return "8";

        //wxyz
        }else if(aChar >= 119 && aChar < 123){
            return "9";
        }else if(aChar >= 48 && aChar <= 57){
            return String.valueOf(aChar);
        }else if(aChar >= 65 && aChar < 90){
            return String.valueOf((char)((int) aChar + 33));
            //Z
        }else if(aChar == 90){
            return "a";
        }

//        abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9
        return "";
    }
}



