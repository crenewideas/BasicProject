package cn.pxl.capture01.subsection05;

import cn.pxl.capture01.common.ReadFile;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

//转换流的其他情况
public class OtherStreamConvert {
    private static Stream<String> stringStream = Arrays.stream(ReadFile.readFileIntoStringArray());
    // 取得去除重复元素的新流
    public static void distinctStream(){
        //重复的元素只保留一份
        Stream<String> distinctStream = stringStream.distinct();
    }

    // 取得排序后的新流
    public static void sortedStream(){
        //获取降序排序后的流 。 这种接收的是Comparator对象
        Stream<String> sortedStream = stringStream.sorted(Comparator.comparing(String::length).reversed());
    }

    //peek方法，在每一次获取流中的一个元素时，就会调用一次peek方法。
    //peek方法，只有在调用终止操作后，才会执行相关代码，否则不执行。例如：调用count()后，会执行peek中的打印代码，未调用count()，不会打印代码
    public static Stream<String> peekStream(){
        //在peek中打断点，方便调试
        return stringStream.peek(System.out::println);
    }

}
