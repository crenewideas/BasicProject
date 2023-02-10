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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//参考大佬答案
public class HJ28_2 {

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

        String[] splitIntStrs = arrayList.get(1).split(" ");
        //偶数
        List<Integer> addInts = Arrays.stream(splitIntStrs).map(Integer::parseInt).filter(oneInt -> oneInt % 2 == 0).collect(Collectors.toList());
        //奇数
        List<Integer> evenInts = Arrays.stream(splitIntStrs).map(Integer::parseInt).filter(oneInt -> oneInt % 2 == 1).collect(Collectors.toList());
        //利用匈牙利算法计算最大匹配数
        int[] evensMatch = new int[addInts.size()];
        int resultCount = 0;
        for (int i = 0; i < addInts.size(); i++) {
            int[] used = new int[evenInts.size()];
            if (find(used, evenInts, addInts.get(i), evensMatch))
                resultCount++;
        }
        System.out.println(resultCount);
    }

    //判断一个数字是否是素数的方式。
    public static boolean isPrime(int m) {
        for (int i = 2; i * i <= m; i++) {
            if (m % i == 0) {
                return false;
            }
        }
        return true;
    }

    //匈牙利算法
    //used：i 索引处的奇数是否经配对过。（1为已经配对，0位未配对。每一次偶数循环，used都会清空。而递归调用时，不会清空used！）
    //evensMatch: 奇数索引处匹配的偶数值。
    //evens：所有的奇数
    //num：某一个偶数
    public static boolean find(int[] used, List<Integer> evens, int num, int[] evensMatch) {
        //循环遍历每一个奇数
        for (int i = 0; i < evens.size(); i++) {
            //当前的 偶数 和 奇数 满足素数条件，并且当前的奇数没有被配对过。
            if (isPrime(num + evens.get(i)) && used[i] == 0) {
                //当前的奇数配对值改为已配对。
                used[i] = 1;
                //如果暂无匹配，或者原来匹配的偶数素可以找到新的奇数元素匹配。
                //这里递归时，used 没有被清空！used[i] 还是 1 ，因此 if (isPrime(num + evens.get(i)) && used[i] == 0) 不会走，也就是evensMatch[i]跳过了旧有指向的奇数。
                if (evensMatch[i] == 0 || find(used, evens, evensMatch[i], evensMatch)) {
                    evensMatch[i] = num;
                    return true;
                }
            }
        }
        return false;
    }

}



