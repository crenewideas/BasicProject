package cn.pxl.niuke;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//HJ8 合并表记录
//先输入键值对的个数n（1 <= n <= 500）
//接下来n行每行输入成对的index和value值，以空格隔开
//输出描述：
//输出合并后的键值对（多行）
//数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
public class HJ8 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Integer> oneMap = new HashMap<>();
        int count = 0;
        if (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            count = Integer.parseInt(s);
        }
        if(0 == count) return;
        for (int i = 0; i < count; i++) {
            if (scanner.hasNextLine()) {
                String oneStr = scanner.nextLine();
                String[] strings = oneStr.split(" ");
                int key = Integer.parseInt(strings[0]);
                int value = Integer.parseInt(strings[1]);
                if (oneMap.containsKey(key)) {
                    Integer oldValue = oneMap.get(key);
                    oldValue = oldValue + value;
                    oneMap.put(key,oldValue);
                } else {
                    oneMap.put(key,value);
                }
            }

        }
        scanner.close();
        List<Integer> sortedKey = oneMap.keySet().stream().sorted().collect(Collectors.toList());
        for (Integer integer : sortedKey) {
            System.out.println(integer + " " + oneMap.get(integer));
        }
    }
}
