package cn.pxl.niuke;

import java.util.Scanner;

//HJ6 质数因子
//输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
public class HJ6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int oneInt = -1;
        if (scanner.hasNextLine()) {
            oneInt = scanner.nextInt();
        }
        scanner.close();
        if(-1 == oneInt) return;
        int sqrt = (int) Math.sqrt(oneInt);
        StringBuilder stringBuilder = new StringBuilder();
        int finalResult = 0;
        for (int i = 2; i <= sqrt; i++) {
            while (oneInt % i == 0){
                oneInt = oneInt / i;
                stringBuilder.append(i + " ");
                finalResult = oneInt;
            }
        }
        String result = stringBuilder.toString();
        if(result.length() == 0){
            result = String.valueOf(oneInt);
        }
        //最后一个是质数，那么也要算进去。如93938：2 13 3613
        if(finalResult > sqrt){
            result = result + finalResult;
        }
        System.out.println(result.trim());
    }
}
