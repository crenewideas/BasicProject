package cn.pxl.capture01.subsection11;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//下游收集器：用于处理groupBy产生的列表集合！作为groupingBy的第二个参数传入即可
public class DownStreamCollector {
    private static Stream<Locale> availableLocales = Stream.of(Locale.getAvailableLocales());
    public static void downStreamCollectorTest(){
        //如果希望分组产生的是集合，而不是列表：
        Map<String, Set<Locale>> collect = availableLocales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.toSet()));
        //如果希望只统计每个分组的个数
        Map<String, Long> countingTest = availableLocales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.counting()));
        //获取分组后每组数据的和，可以用 下游收集器： summingInt
        //获取分组后每组数据的最大值，最小值，可以用 下游收集器： maxBy/minBy

        //在收集器后添加一个最终处理步骤。根据国家分组，并根据下游收集器收集到Set集合中，最后统计没有Set集合中收集的数据的个数。
        Map<String, Integer> collect1 = availableLocales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.collectingAndThen(Collectors.toSet(), Set::size)));

        //将一个函数应用于收集到的每一个元素，并传递给下游收集器。首先根据 国家 分组，然后在每个组的内部，会获取语言代码，并将其收集到一个Set集合中。
        Map<String, Set<String>> collect2 = availableLocales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.mapping(Locale::getDisplayLanguage, Collectors.toSet())));

    }
}
