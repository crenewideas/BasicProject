package cn.pxl.niuke;
import java.util.*;
import java.util.stream.Collectors;

//HJ14
//给定 n 个字符串，请对 n 个字符串按照字典序排列。
//输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
//数据输出n行，输出结果为按照字典序排列的字符串。
public class HJ14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int intCount = -1;
        if (scanner.hasNextLine()) {
            String count = scanner.nextLine();
            intCount = Integer.parseInt(count);
        }
        if(intCount == -1) return;
        ArrayList<String> strs = new ArrayList<>();
        for (int i = 0; i < intCount; i++) {
            if (scanner.hasNextLine()) {
                strs.add(scanner.nextLine());
            }
        }
        scanner.close();
        List<String> collect = strs.stream().sorted().collect(Collectors.toList());
        for (String s : collect) {
            System.out.println(s);
        }
    }
}
