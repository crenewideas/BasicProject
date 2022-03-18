package cn.pxl.capture01.subsection13;

import cn.pxl.capture01.common.ReadFile;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BasicTypeStream {
    public static void createBasicTypeStream(){
        int[] ints = {1, 2, 34,100, 5666};
        IntStream ints1 = IntStream.of(ints);
        IntStream stream = Arrays.stream(ints);
        int sumValue = ints1.reduce(0, Integer::sum);
        //含头不含尾
        IntStream range = IntStream.range(1, 100);
        //含头含尾
        IntStream intStream = IntStream.rangeClosed(1, 100);
        String[] strings = ReadFile.readFileIntoStringArray();
        Stream<String> stream1 = Arrays.stream(strings);
        IntStream intStream1 = stream1.mapToInt(String::length);

    }

}
