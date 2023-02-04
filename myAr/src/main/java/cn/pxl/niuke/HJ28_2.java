package cn.pxl.niuke;

//题目描述
// 若两个正整数的和为素数，则这两个正整数称之为“素数伴侣”，如2和5、6和13，它们能应用于通信加密。现在密码学会请你设计一个程序，
// 从已有的 N （ N 为偶数）个正整数中挑选出若干对组成“素数伴侣”，挑选方案多种多样，例如有4个正整数：2，5，6，13，如果将5和6分为一组中只能得到一组“素数伴侣”，
// 而将2和5、6和13编组将得到两组“素数伴侣”，能组成“素数伴侣”最多的方案称为“最佳方案”，当然密码学会希望你寻找出“最佳方案”。

//输入:
//有一个正偶数 n ，表示待挑选的自然数的个数。后面给出 n 个具体的数字。
//输入说明
//1 输入一个正偶数 n
//2 输入 n 个整数
//输出:
//输出一个整数 K ，表示你求得的“最佳方案”组成“素数伴侣”的对数。求得的“最佳方案”组成“素数伴侣”的对数。

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HJ28 {

    public static void main(String[] args) throws IOException {

        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/pengxiaoliang/Downloads/string.txt"));
        String str;
        while ((str = bufferedReader.readLine()) != null){
            arrayList.add(str);
        }
        bufferedReader.close();
        System.out.println(arrayList);

//        ArrayList<String> arrayList = new ArrayList<>();
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String nextLine = scanner.nextLine();
//            arrayList.add(nextLine);
//        }

        int count = Integer.parseInt(arrayList.get(0));
        String[] splitIntStrs = arrayList.get(1).split(" ");
        //偶数
        List<Integer> addInts = Arrays.stream(splitIntStrs).map(Integer::parseInt).filter(oneInt -> oneInt % 2 == 0).collect(Collectors.toList());
        //奇数
        List<Integer> evenInts = Arrays.stream(splitIntStrs).map(Integer::parseInt).filter(oneInt -> oneInt % 2 == 1).collect(Collectors.toList());
        //利用匈牙利算法计算最大匹配数

    }




}



