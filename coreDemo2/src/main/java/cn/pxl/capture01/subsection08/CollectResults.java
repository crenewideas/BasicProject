package cn.pxl.capture01.subsection08;

import cn.pxl.capture01.common.ReadFile;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//收集结果到集合中
public class CollectResults {
    public static void collectResult(){
        //1.对收集的结果进行迭代
        Stream<String> stream = Arrays.stream(ReadFile.readFileIntoStringArray());
        stream.forEach(result -> System.out.println(result.length()+ ":" +result));
        //2.将元素收集到数组中,如果不传指定的类型，那么获取的是Object类型的数组。因为执行时无法创建泛型数组
        //Object[] objects = stream.toArray();
        String[] result = stream.toArray(String[]::new);
        //3.将元素收集到集合中
        List<String> collectResults = stream.collect(Collectors.toList());
        //4.收集流中所有字符串的集合
        String collectString = stream.collect(Collectors.joining());
        //5.收集流中所有字符串的集合,并且添加分隔符
        String collectStringAddSeparator = stream.collect(Collectors.joining(","));
        //6.总和，数量，平均值，最大值，最小值
        IntSummaryStatistics summaryStatistics = stream.collect(Collectors.summarizingInt(String::length));
        //获取所有字符串的平均长度
        double average = summaryStatistics.getAverage();
        //获取所有字符串的数量
        long count = summaryStatistics.getCount();
        //获取所有字符串长度之和
        long sum = summaryStatistics.getSum();
        //获取最长的字符串长度
        int max = summaryStatistics.getMax();
        //获取最短的字符串长度
        int min = summaryStatistics.getMin();
    }
}
