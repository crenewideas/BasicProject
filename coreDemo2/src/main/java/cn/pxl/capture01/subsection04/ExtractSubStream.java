package cn.pxl.capture01.subsection04;

import cn.pxl.capture01.common.ReadFile;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

//抽取子流
public class ExtractSubStream {

    //从无限流中抽取有限子流
    public static void limitAndSkipTest(){
        Stream<Integer> iterateStream = Stream.iterate(0, n -> n + 1);
        //抽取前100个元素到新的流中
        Stream<Integer> limitStream = iterateStream.limit(100);
        //跳过第一个元素，抽取剩余的元素到新的流中
        String[] strings = ReadFile.readFileIntoStringArray();
        Stream<String> stringStream = Arrays.stream(strings);
        //跳过第一个元素，抽取剩余的元素到新的stream中
        Stream<String> skipStream = stringStream.skip(1L);
    }

    //Java 9 :takeWhile
    /*
    public static void takeWhileTest(){
        String[] strings = ReadFile.readFileIntoStringArray();
        Stream<String> stringStream = Arrays.stream(strings);
        stringStream.takeWhile(Predicate...);
    }
    */


}
