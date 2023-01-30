package cn.pxl.niuke;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//HJ3 明明的随机数
//明明生成了NN个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
//数据范围：1≤n≤1000  ，输入的数字大小满足 1≤val≤500
public class HJ3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<Integer> oneSet = new HashSet<>();
        int count = 0;
        if (scanner.hasNextLine()) {
            count = scanner.nextInt();
        }
        if(0 == count) return;
        for (int i = 0; i < count; i++) {
            if (scanner.hasNextLine()) {
                oneSet.add(scanner.nextInt());
            }
        }
        scanner.close();
        List<Integer> collects = oneSet.stream().sorted().collect(Collectors.toList());
        for (Integer sortedInteger: collects) {
            System.out.println(sortedInteger);
        }
    }
}
