package cn.pxl.niuke;

//HJ23 实现删除字符串中出现次数最少的字符，若出现次数最少的字符有多个，则把出现次数最少的字符都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
//数据范围：输入的字符串长度满足 1≤n≤20  ，保证输入的字符串中仅出现小写字母
//输入描述：字符串只包含小写英文字母, 不考虑非法输入，输入的字符串长度小于等于20个字节。
//输出描述：删除字符串中出现次数最少的字符后的字符串。

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HJ23 {

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

        for (int i = 0; i < arrayList.size(); i++) {
            //key：字符。
            //value:出现的次数。
            HashMap<Character,Integer> resultMap = new HashMap<>();
            String oneStr = arrayList.get(i);
            char[] chars = oneStr.toCharArray();
            for (char aChar : chars) {
                if (resultMap.containsKey(aChar)) {
                    resultMap.put(aChar,resultMap.get(aChar) + 1);
                }else {
                    resultMap.put(aChar,1);
                }
            }
            //转换map为以次数开头，并取 key 的最小值。
            HashMap<Integer, ArrayList<Character>> convertMap = new HashMap<>();
            int minKey = Integer.MAX_VALUE;
            for (Map.Entry<Character, Integer> oneEntry : resultMap.entrySet()) {
                Character key = oneEntry.getKey();
                Integer value = oneEntry.getValue();
                if(convertMap.containsKey(value)){
                    ArrayList<Character> characters = convertMap.get(value);
                    characters.add(key);
                    convertMap.put(value,characters);
                } else {
                    ArrayList<Character> characters = new ArrayList<>();
                    characters.add(key);
                    convertMap.put(value,characters);
                }
                if(minKey > value) minKey = value;
            }

            //最小key的集合
            ArrayList<Character> characters = convertMap.get(minKey);
            for (Character character : characters) {
                //替换掉
                oneStr = oneStr.replace(character.toString(),"");
            }
            System.out.println(oneStr);
        }

    }
}



