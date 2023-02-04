package cn.pxl.niuke;

//HJ27 定义一个单词的“兄弟单词”为：交换该单词字母顺序（注：可以交换任意次），而不添加、删除、修改原有的字母就能生成的单词。
//兄弟单词要求和原来的单词不同。例如： ab 和 ba 是兄弟单词。 ab 和 ab 则不是兄弟单词。
//现在给定你 n 个单词，另外再给你一个单词 x ，让你寻找 x 的兄弟单词里，按字典序排列后的第 k 个单词是什么？
//注意：字典中可能有重复单词。

//输入描述：输入只有一行。 先输入字典中单词的个数n，再输入n个单词作为字典单词。 然后输入一个单词x 最后后输入一个整数k
//输出描述：第一行输出查找到x的兄弟单词的个数m 第二行输出查找到的按照字典顺序排序后的第k个兄弟单词，没有符合第k个的话则不用输出。

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HJ27 {

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
            String[] splits = oneStr.split(" ");
            int count = Integer.parseInt(splits[0]);
            String originStr = splits[count + 1];

            //key:某一字符
            //value:该字符在字符串中出现的次数
            HashMap<Character, Integer> originMap = new HashMap<>();
            for (char aChar : originStr.toCharArray()) {
                if(originMap.containsKey(aChar)){
                    originMap.put(aChar,originMap.get(aChar) + 1);
                } else {
                    originMap.put(aChar,1);
                }
            }
            int k = Integer.parseInt(splits[count + 2]);
            ArrayList<String> brotherList = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
                //key:某一字符
                //value:该字符在字符串中出现的次数
                HashMap<Character, Integer> resultMap = new HashMap<>();
                String splitStr = splits[i];
                for (char aChar : splitStr.toCharArray()) {
                    if(resultMap.containsKey(aChar)){
                        resultMap.put(aChar,resultMap.get(aChar) + 1);
                    } else {
                        resultMap.put(aChar,1);
                    }
                }
                //判断是否所有key，value均相同，如果相同，并且原字符串不相等，那么属于同一兄弟。
                if(resultMap.size() != originMap.size()) continue;
                if(splitStr.equals(originStr)) continue;
                boolean isBrother = true;
                for (Map.Entry<Character, Integer> oneEntry : resultMap.entrySet()) {
                    Character oneEntryKey = oneEntry.getKey();
                    Integer oneEntryValue = oneEntry.getValue();
                    if(!originMap.containsKey(oneEntryKey)) {
                        isBrother = false;
                        break;
                    }else if(!oneEntryValue.equals(originMap.get(oneEntryKey))){
                        isBrother = false;
                        break;
                    }
                }

                //如果是兄弟元素
                if(isBrother){
                    brotherList.add(splitStr);
                }

            }
            System.out.println(brotherList.size());
            List<String> sortedStr = brotherList.stream().sorted().collect(Collectors.toList());
            if(sortedStr.size() < k) return;
            System.out.println(sortedStr.get(k - 1));
        }

    }
}



