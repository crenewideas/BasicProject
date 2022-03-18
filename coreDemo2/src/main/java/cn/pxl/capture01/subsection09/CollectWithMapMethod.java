package cn.pxl.capture01.subsection09;

import cn.pxl.capture01.common.ReadFile;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectWithMapMethod {

    public static void collectWithMapMethodTest(){
        Stream<String> stream = Arrays.stream(ReadFile.readFileIntoStringArray());
        //以字符串长度为key，以字符串本身为value
        Map<Integer, String> collect = stream.collect(Collectors.toMap(String::length, Function.identity()));
    }

    //多个元素具有相同的键，那么会存在冲突，第三个参数用于解决冲突后取哪个value值。
    public static void localStream(){
        //第三个参数：针对已有值和新值解决冲突并确定键对应的值。这个函数会返回已有的值或新值，或他们的组合
        Stream<Locale> availableLocales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> collect = availableLocales.collect(Collectors.toMap(Locale::getDisplayLanguage, locale -> locale.getDisplayCountry(locale), (existingValue, newValue) -> existingValue));
        System.out.println(collect);
    }

}
