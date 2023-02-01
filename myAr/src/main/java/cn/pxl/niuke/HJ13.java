package cn.pxl.niuke;
import java.util.Scanner;
import java.util.Stack;

//HJ13 句子逆序
//将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
//注意本题有多组输入
//输入一个英文语句，每个单词用空格隔开。保证输入只包含空格和字母。
//得到逆序的句子
public class HJ13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String scan = "";
        if (scanner.hasNextLine()) {
            scan = scanner.nextLine();
        }
        scanner.close();
        if(scan.isEmpty()) return;
        String[] chars = scan.split(" ");
        Stack<String> stack = new Stack<>();
        for (String aChar : chars) {
            stack.push(aChar);
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()){
            stringBuilder.append(stack.pop() + " ");
        }
        System.out.println(stringBuilder.toString().trim());
    }
}
