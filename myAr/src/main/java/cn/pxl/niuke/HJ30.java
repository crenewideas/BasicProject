package cn.pxl.niuke;

//描述 : 第一步：将输入的两个字符串str1和str2进行前后合并。如给定字符串 "dec" 和字符串 "fab" ， 合并后生成的字符串为 "decfab"
//第二步：对合并后的字符串进行排序，要求为：下标为奇数的字符和下标为偶数的字符分别从小到大排序。这里的下标的意思是字符在字符串中的位置。
// 注意排序后在新串中仍需要保持原来的奇偶性。例如刚刚得到的字符串“decfab”，分别对下标为偶数的字符'd'、'c'、'a'和下标为奇数的字符
// 'e'、'f'、'b'进行排序（生成 'a'、'c'、'd' 和 'b' 、'e' 、'f'），再依次分别放回原串中的偶数位和奇数位，新字符串变为“abcedf”
//第三步：对排序后的字符串中的'0'~'9'、'A'~'F'和'a'~'f'字符，需要进行转换操作。
//转换规则如下：
//对以上需要进行转换的字符所代表的十六进制用二进制表示并倒序，然后再转换成对应的十六进制大写字符（注：字符 a~f 的十六进制对应十进制的10~15，大写同理）。
//如字符 '4'，其二进制为 0100 ，则翻转后为 0010 ，也就是 2 。转换后的字符为 '2'。
//如字符 ‘7’，其二进制为 0111 ，则翻转后为 1110 ，对应的十进制是14，转换为十六进制的大写字母为 'E'。
//如字符 'C'，代表的十进制是 12 ，其二进制为 1100 ，则翻转后为 0011，也就是3。转换后的字符是 '3'。
//根据这个转换规则，由第二步生成的字符串 “abcedf” 转换后会生成字符串 "5D37BF"。

//输入描述：
//样例输入两个字符串，用空格隔开。

//输出描述：
//输出转化后的结果。

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HJ30 {

    public static void main(String[] args) throws IOException {

//        ArrayList<String> arrayList = new ArrayList<>();
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/pengxiaoliang/Downloads/string.txt"));
//        String str;
//        while ((str = bufferedReader.readLine()) != null) {
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

        for (String oneStr : arrayList) {
            oneStr = oneStr.replace(" ", "");
            char[] chars = oneStr.toCharArray();
            //排序
            LinkedList<Character> evenList = new LinkedList<>();
            LinkedList<Character> oddList = new LinkedList<>();
            boolean isEven = true;
            for (int i = 0; i < chars.length; i++) {
                if (isEven) {
                    evenList.add(chars[i]);
                    isEven = false;
                } else {
                    oddList.add(chars[i]);
                    isEven = true;
                }
            }
            evenList = evenList.stream().sorted().collect(Collectors.toCollection(LinkedList::new));
            oddList = oddList.stream().sorted().collect(Collectors.toCollection(LinkedList::new));
            isEven = true;
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                if (isEven) {
                    builder.append(Character.toChars(evenList.pop()));
                    isEven = false;
                } else {
                    builder.append(Character.toChars(oddList.pop()));
                    isEven = true;
                }
            }
            String sortedStr = builder.toString();
            StringBuilder stringBuilder = new StringBuilder();
            for (char aChar : sortedStr.toCharArray()) {
                stringBuilder.append(convertStr(aChar));
            }
            System.out.println(stringBuilder);
        }
    }

    //16进制转2进制
    private static String convertStr(Character oneChar){
        String charStr = oneChar.toString();
        try{
            Integer parseInt = Integer.parseInt(charStr, 16);
            //Integer.toBinaryString 会将左侧的 0 截取掉，所以要补0！
            String toBinaryString = Integer.toBinaryString(parseInt);
            StringBuilder binaryString = new StringBuilder(toBinaryString);
            while (binaryString.length() < 4){
                binaryString.insert(0, "0");
            }
            String hexString = Integer.toHexString(Integer.parseInt(new StringBuilder(binaryString.toString()).reverse().toString(),2));
            StringBuilder builder = new StringBuilder();
            char[] chars = hexString.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if(Character.isLetter(chars[i])){
                    builder.append(Character.toChars(Character.toUpperCase(chars[i])));
                }else {
                    builder.append(Character.toChars(chars[i]));
                }
            }
            return builder.toString();
        }catch (NumberFormatException e){
            return charStr;
        }
    }
}

//5D37BF

