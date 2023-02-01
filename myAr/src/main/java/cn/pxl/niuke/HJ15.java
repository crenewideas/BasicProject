package cn.pxl.niuke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//HJ15 输入一个 int 型的正整数，计算出该 int 型数据在内存中存储时 1 的个数。
//输入一个整数（int类型）
//这个数转换成2进制后，输出1的个数
public class HJ15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int scan = -1;
        if (scanner.hasNextLine()) {
            scan = scanner.nextInt();
        }
        scanner.close();
        if(scan == -1) return;
        String string = Long.toBinaryString(scan);
        char[] chars = string.toCharArray();
        int count = 0;
        for (char aChar : chars) {
            if(aChar == '1') count++;
        }
        System.out.println(count);
    }
}
