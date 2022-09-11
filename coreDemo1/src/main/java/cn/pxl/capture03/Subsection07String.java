package cn.pxl.capture03;

public class Subsection07String {
    //格式化字符串
    public static void formatString(){
        //打印8个字符，精度为小数点后2位字符；
        String doubleFormat = String.format("%8.2f",4.23233);
        System.out.println(doubleFormat);
        //转换符：   f：浮点数 ；
        //          s：字符串；
        //          d：十进制的整数等，见书中第 58页所述

        // % 是格式化的起始标志； 转换符是格式化的结束标志，中间用不同的符号表示要格式化的形式。
        // %+d 表示 对十进制的整数前打印正负号。
        String pxl = String.format("hello %s , next year you will be %+d", "pxl", 25);
        System.out.println(pxl);

    }

}
