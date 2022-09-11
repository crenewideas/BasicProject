package cn.pxl.capture03;

import java.math.BigDecimal;

//八种基本数据类型
public class Subsection01 {

    //浮点型数据，float是单精度，占四个字节；double是双精度，占8个字节。
    public static void doFloatAndDouble(){
        //不加后缀时，默认是double类型。
        // float a = 3.1; 会报错，因为自动类型提升只允许低级转高级；但3.1是double类型，a 是 float类型，不满足自动类型提升。

        //声明float类型的数据，必须添加后缀 f/F
        float a = 3.1f;

        //二进制无法精确的表示 0.1,因此浮点数做运算时有可能会有精度损失。
        double b = 2.0 - 1.1;
        System.out.println(b);  //0.8999999999999999

        //精确的计算，需要用 BigDecimal 类型

    }


    //字符型
    public static void doChar(){
        //char字面量需要用 单引号；
        char a = 'A';
        char tm = '\u2122';//用十六进制表示的字符：
        //  is a escape sequence; 注意， 反斜杠u ,可以出现在 字符字面量 或者 字符串 或者 任何一个地方，而其他的转义序列则不可以。
        //  所以 反斜杠u不能出现在注释中，否则会报错 java: 非法的 Unicode 转义
        System.out.println(tm); //™
        // other escape sequence :
        //转义序列可以出现在 字符字面量 或者 字符串中。

        System.out.println('\b');//退格键
        System.out.println('\t');//制表符
        System.out.println('\n');//换行
        System.out.println('\r');//回车
        System.out.println('\"');//双引号
        System.out.println('\'');//单引号
        System.out.println('\\');//反斜杠

        //在java中，char类型描述了UTF-16编码中的一个 代码单元（在基本多语言平面中，每个字符用16位表示，通常成为 "代码单元"）；
        //基本多语言平面：Unicode编码中，可以分为17个代码平面，其中的第一个代码平面称为基本多语言平面（普适性的语言字符。）包括从码点 U+0000 -> U+FFFF的经典Unicode代码。；
        //其余16个平面称为辅助字符

        //如果java中，描述的是 UTF-16编码中的代码单元，那么使用char没影响；如果 描述的是辅助字符 ， 那么不要用char类型：(辅助字符用的是两个代码单元组成的，char只能表示其中的一个代码单元)
        //码点：对于辅助字符来讲，一个码点由两位代码单元组成（也就是32位2进置组成）；
        //码点：对于基本多语言平面字符来讲，一个码点由一位代码单元组成（也就是16位2进置组成）；

    }


    //变量的声明和使用
    public static void elementDemo(){
        //声明的方式：
        int a;
        double b;

        //未初始化的变量不可以使用
        //System.out.println(a); 是错误的，因为a没有初始化。

        //变量初始化：
        a =2;
        System.out.println(a); // 已经初始化的变量是可以使用的。

        //可以同时声明和初始化
        int c = 3;

        //java 中，变量的声明可以在类内部的任何地方。
    }

    //常量
    //有些常量，希望在一个类中的多个方法中使用，这些常量可以称为 ：类常量，可以用 static final 表示。
    public static final int e = 2;
    public static void constantsDemo(){
        //final 表示当前变量只能被赋值一次。一旦被赋值后，就不能再进行更改。
        final double d ;
        d =4;
        //d= 5; error;
    }

    //枚举类型声明的方式；
    public enum enumType {Small,Large};
    public static void enumDemo(){
        enumType small = enumType.Small;
        System.out.println(small);              //Small
        System.out.println(small.getClass());   //class cn.pxl.capture03.Subsection01$enumType

    }


}
