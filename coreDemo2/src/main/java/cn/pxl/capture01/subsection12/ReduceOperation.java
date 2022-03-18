package cn.pxl.capture01.subsection12;

import cn.pxl.capture01.common.ReadFile;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReduceOperation {
    private static String[] values = ReadFile.readFileIntoStringArray();
    public static void reduceOperationTest(){
        Stream<String> valueStream = Arrays.stream(values);
        Stream<Integer> integerStream = valueStream.map(String::length);
        //约简，将所有字符串的长度相加，获取总长度。
        //Optional<Integer> reduce = integerStream.reduce((x, y) -> x + y);
        Optional<Integer> reduce = integerStream.reduce(Integer::sum);
        Integer totalLength = reduce.orElse(0);
        System.out.println(totalLength);
    }
}
