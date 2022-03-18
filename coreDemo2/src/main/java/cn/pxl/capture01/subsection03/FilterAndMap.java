package cn.pxl.capture01.subsection03;

import cn.pxl.capture01.common.ReadFile;

import java.util.List;
import java.util.stream.Stream;

public class FilterAndMap {
    private static List<String> words = ReadFile.readFileIntoString();

    //filter 过滤出符合条件的新流
    public static void FilterTest(){

        //过滤出符合 字符数大于12的数据流
        Stream<String> filteredValue = words.stream().filter(word -> word.length() > 12);
    }

    //map 用于转换流中的值
    public static void MapTest(){
        //生成一个小写字母转换为大写字母之后的流
        Stream<String> stringStream = words.stream().map(String::toUpperCase);
        //lambda表达式的写法为：
        //Stream<String> stringStream = words.stream().map(word -> word.toUpperCase());
    }

    //将包含流转换为平铺流
    public static void MapToFlatMap(){
        //获取一个包含流 map : 将流中的string类型的值，转换为码点的流，所以泛型是包含码点信息的流。
        Stream<Stream<String>> includingStream = words.stream().map(CodePoints::getCodePoints);
        //将包含流转换为平铺流
        Stream<String> flatStream = words.stream().flatMap(CodePoints::getCodePoints);
        //整个流程：List<String> ---> Stream<String> ---> Stream<Stream<String>>。
        //对应函数：    T <V>    --->    G<V>        ---> G<G<V>>
        //              调用f函数，可以将 T 转换为G<U>
        //              调用g函数，可以将 U 转换为G<V>
        //先调用f，再调用g，可以将：T ---> G<G<V>>。
    }
}
