package cn.pxl.niuke;

import java.util.*;

//HJ8 输入一个 int 型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
//保证输入的整数最后一位不是 0 。
//按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
public class HJ9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int scan = 0;
        if (scanner.hasNextLine()) {
            scan = scanner.nextInt();
        }
        scanner.close();
        if(0 == scan) return;
        char[] chars = String.valueOf(scan).toCharArray();
        LinkedList<Character> characters = new LinkedList<>();
        for (int i = chars.length - 1; i >= 0 ; i--) {
            char aChar = chars[i];
            if (characters.contains(aChar)) continue;
            characters.add(aChar);
        }
        while (!characters.isEmpty()) {
            System.out.print(characters.poll());
        }

    }
}
