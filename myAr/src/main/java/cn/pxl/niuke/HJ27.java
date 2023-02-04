package cn.pxl.niuke;

//HJ26 编写一个程序，将输入字符串中的字符按如下规则排序。
//规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
//如，输入： Type 输出： epTy
//规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
//如，输入： BabA 输出： aABb
//规则 3 ：非英文字母的其它字符保持原来的位置。
//如，输入： By?e 输出： Be?y

import java.io.IOException;
import java.util.*;

public class HJ26 {

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

        for (String oneStr : arrayList) {
            char[] chars = oneStr.toCharArray();
            //收集字母
            ArrayList<Character> letters = new ArrayList<>();
            for (char aChar : chars) {
                if(Character.isLetter(aChar)){
                    letters.add(aChar);
                }
            }
            //对字母进行排序
            letters.sort(Comparator.comparingInt(Character::toLowerCase));
            StringBuilder builder = new StringBuilder();
            for (int i = 0,j = 0; i < chars.length; i++) {
                char aChar = chars[i];
                if(Character.isLetter(aChar)){
                    //j表示的是第几个字符位置。
                    builder.append(letters.get(j++));
                } else {
                    //非字符，那么对应位置填入原来的值即可
                    builder.append(chars[i]);
                }
            }
            System.out.println(builder);
        }
    }
}



