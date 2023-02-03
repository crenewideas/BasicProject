package cn.pxl.niuke;

//HJ20 密码验证合格程序
//密码要求:
//        1.长度超过8位
//        2.包括大小写字母.数字.其它符号,以上四种至少三种
//        3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HJ20 {
    private static final String OK = "OK";
    private static final String NG = "NG";
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
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<Integer> typeCount = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            String oneStr = arrayList.get(i);
            if(oneStr.length() <= 8 ) {
                resultList.add(i,NG);
                continue;
            }
            char[] chars = oneStr.toCharArray();

            boolean breakFlag = hasDuplicateStr(oneStr);

            //没有被打断，说明连续子串校验通过
            if(breakFlag) {
                resultList.add(i,NG);
                continue;
            }else {
                //四种的三种
                for (int i1 = 0; i1 < chars.length; i1++) {
                    char aChar = chars[i1];
                    if(!typeCount.contains(0) && (aChar >= 65 && aChar <= 90)){
                        typeCount.add(0);
                    }else if(!typeCount.contains(1) && (aChar >= 48 && aChar <= 57)){
                        typeCount.add(1);
                    }else if(!typeCount.contains(2) && (aChar >= 97 && aChar <= 122)){
                        typeCount.add(2);
                    }else if(!typeCount.contains(3) && !(aChar >= 65 && aChar <= 90) && !(aChar >= 48 && aChar <= 57) && !(aChar >= 97 && aChar <= 122)){
                        typeCount.add(3);
                    }
                }
                if(typeCount.size() < 3){
                    resultList.add(i,NG);
                    typeCount.clear();
                    continue;
                }else {
                    typeCount.clear();
                }
            }
            resultList.add(i,OK);
        }

        for (String s : resultList) {
            System.out.println(s);
        }
    }

    //判断公共子串是否有重复
    private static boolean hasDuplicateStr(String str){
        if(str == null || str.length() < 5) return  false;
        //首行3个没有重复，那么才递归调用，看偏移之后的是否有重复。
        String firstStr = str.substring(0, 3);
        String compareStr;
        for (int i = 0; i < str.length() - 5; i++) {
            compareStr = str.substring(i+3,i+6);
            if(firstStr.equals(compareStr)) {
                return true;
            }
        }
        //到这里，说明没有重复子序列，那么递归。
        str = str.substring(1);
        return hasDuplicateStr(str);
    }

}



