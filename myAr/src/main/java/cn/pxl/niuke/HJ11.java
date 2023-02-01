package cn.pxl.niuke;
import java.util.Scanner;
import java.util.Stack;

//HJ11 输入一个整数，将这个整数以字符串的形式逆序输出，程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
//输入一个int整数
//将这个整数以字符串的形式逆序输出
public class HJ11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer scan = -1;
        if (scanner.hasNextLine()) {
            scan = scanner.nextInt();
        }
        scanner.close();
        if (scan == -1) return;
        char[] chars = String.valueOf(scan).toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            stack.push(aChar);
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }
}
