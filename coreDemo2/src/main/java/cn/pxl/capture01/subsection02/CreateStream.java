package cn.pxl.capture01.subsection02;

import cn.pxl.capture01.common.ReadFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreateStream{
    //方式一： 调用Collection接口的stream方法
    public Stream<String> createStreamOne(){
        List<String> stringList = ReadFile.readFileIntoString();
        return stringList.stream();
    }

    //方式二：Stream.of(数组)
    public Stream<String> createStreamTwo(){
        //创建数组对应的流
//        String[] strings = ReadFile.readFileIntoStringArray();
//        return Stream.of(strings);

        //创建一个空流
        Stream<String> empty = Stream.empty();
        return empty;
    }

    //方式三:Arrays.stream(array,from,to)
    public static Stream<String> createStreamThree(){
        //用数组中一部分元素创建流
        String[] strings = ReadFile.readFileIntoStringArray();
        //这里创建了数组中前5个元素的流
        return Arrays.stream(strings,0,5);
    }

    //方式四：创建无限流
    public static Stream<String> createStreamFore(){
        //创建某个固定字符串的无限流
        Stream<String> generateStr = Stream.generate(() -> "Echo");
        //创建某个随机数的流
        Stream<String> generateRandom = Stream.generate(() -> String.valueOf(Math.random()));
        //产生递增序列的无限流，用filter获取符合条件的流
        Stream<Integer> iterateInt = Stream.iterate(0, n -> n + 1).filter(oneValue ->oneValue < 10);
        //用谓词决定迭代何时结束:java 8 未添加
        //Stream<Integer> iterateInt = Stream.iterate(0, n -> n.compareTo(1000) < 0 ,n -> n + 1);

        return generateRandom;
    }

    //方式五：iterable对象转换为流    , iterator对象结果构成的流

}
