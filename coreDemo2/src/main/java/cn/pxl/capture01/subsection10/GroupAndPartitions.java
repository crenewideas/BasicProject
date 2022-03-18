package cn.pxl.capture01.subsection10;

import cn.pxl.capture01.common.ReadFile;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//群组和分区
public class GroupAndPartitions {
    private static Stream<Locale> availableLocales = Stream.of(Locale.getAvailableLocales());

    public static void groupStreamTest(){
        Map<String, List<Locale>> collect = availableLocales.collect(Collectors.groupingBy(Locale::getCountry));
        List<Locale> ch = collect.get("CH");
        System.out.println(ch);
    }

    //分区：将符合条件的分组，不符合条件的分成另外一组
    public static void partitionStreamTest(){
        Map<Boolean, List<Locale>> en = availableLocales.collect(Collectors.partitioningBy(locale -> locale.getLanguage().equals("en")));
        List<Locale> locales = en.get(true);
        System.out.println(locales);
    }
}
