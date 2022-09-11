package cn.pxl.capture03;

import java.lang.reflect.Array;
import java.util.Arrays;

//数组，是一种数据结构，用于存放相同类型值的集合。
public class Subsection10Array {
    //args 也是数组，字符串类型的数组。
    public static void main(String[] args) {
//        doDemo01();
        balancesDemo();
    }

    public static void doDemo01() {
        //一：数组的声明方式：
        // 1. 类型[] 变量名 推荐使用
        // 2. 类型 变量名[]
        int[] a;

        //二：使用 new 操作符 创建数组 new 类型[长度n] 会创建一个长度为n的数组。
        a = new int[100];

        //数组长度不要求是常量，也可以是变量。
        int length = 20;
        int[] b = new int[length];

        //三：创建数组，并提供初始值的简写形式 :
        // 类型[] 变量名 = {初始值1,初始值2，...,初始值n};
        //这种写法不需要 new 操作符，也不需要指定数组长度。
        int[] c = {12,13};

        //四：创建匿名数组的方式： new 类型[] {初始值1,初始值2，...,初始值n};
        //这种语法，可以重新初始化一个数组，而无需创建变量。
        int[] d = new int[3];
        d = new int[]{1,2,3,4};//重新初始化一个匿名数组,无需新建变量。这种写法等同于：

        int[] e = {1,2,3,4};
        d = e;
        //d = {1,2}; 这种语法是错误的！。 必须在创建数组的同时，提供初始值。想要修改初始值，那么用 d = new int[]{1,2}的语法。


        //五：创建一个int数组后，所有的元素都会初始化为0，对象元素会初始化为null。
        System.out.println(a[1]);//0
        String[] str01 = new String[2];
        System.out.println(str01[0]);//null;

        //数组或者实现了 Iterable 接口的实现类，可以使用 增强for语法
        for (String oneStr : str01) {
            System.out.println(oneStr);//null;
        }
        //使用
        String str01ToString = Arrays.toString(str01);
        System.out.println(str01ToString);//[null, null]


        //数组拷贝
        //1.浅拷贝，引用原数组的地址；
        System.out.println(d == e); // true
        //2 深拷贝，第二位可以增加数组的长度，赋值为默认值。（int ->0 ; object -> null ; boolean -> false）
        int[] copyd = Arrays.copyOf(d, d.length);
        System.out.println(copyd == d);//false
        System.out.println(copyd[0] == d[0]);//true，存的都是相同的数字。

        //数组排序
        Arrays.sort(d);

        //含头不含尾的随机数
        double random = Math.random();
        System.out.println(random);
    }

    private static void balancesDemo(){
        //一：二维数组声明方式：
        int[][] balanceInt;

        //二：定长定宽的二维数组：前边是行号，后边是列号；声明一个三行四列的数组：
        int[][] balanceInt2 = new int[3][4];
        balanceInt2[0][1] = 2;

        //三：声明二维数组的同时进行初始化
        int[][] balanceInt3 = {{1,2,3,4},{5,6,7,8}};
        System.out.println(balanceInt3[1][0]);


    }
}
