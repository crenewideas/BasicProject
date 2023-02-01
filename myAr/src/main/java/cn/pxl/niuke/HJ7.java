package cn.pxl.niuke;

import java.util.Scanner;

//HJ7 取近似值
//写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于 0.5 ,向上取整；小于 0.5 ，则向下取整。
public class HJ7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double oneDouble = -1;
        if (scanner.hasNextLine()) {
            oneDouble = scanner.nextDouble();
        }
        scanner.close();
        if(-1 == oneDouble) return;
        long b = Math.round(oneDouble);
        System.out.println(b);
    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        double oneDouble = -1;
//        if (scanner.hasNextLine()) {
//            oneDouble = scanner.nextDouble();
//        }
//        scanner.close();
//        if(-1 == oneDouble) return;
//        if(oneDouble < 0.5) {
//            System.out.println(0);
//            return;
//        }else if(oneDouble < 1.5){
//            System.out.println(1);
//            return;
//        }
//        String doubleStr = String.valueOf(oneDouble);
//        String[] split = doubleStr.split("\\.");
//        int result = Integer.parseInt(split[0]);
//        String floatResultStr = "0." + split[1];
//        double floatResult = Double.parseDouble(floatResultStr);
//        if(floatResult >= 0.5) result ++;
//        System.out.println(result);
//    }
}
