package cn.pxl.niuke;
import java.util.Scanner;
import java.util.Stack;

//HJ12 字符串反转
//接受一个只包含小写字母的字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
//输入一行，为一个只包含小写字母的字符串。
//输出该字符串反转后的字符串。
public class HJ12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String scan = "";
        if (scanner.hasNextLine()) {
            scan = scanner.nextLine();
        }
        scanner.close();
        if(scan.isEmpty()) return;
        char[] chars = scan.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            stack.push(aChar);
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }
}
