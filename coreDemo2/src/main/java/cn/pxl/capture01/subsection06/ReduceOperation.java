package cn.pxl.capture01.subsection06;

import cn.pxl.capture01.common.ReadFile;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

//约简操作：也称为终止操作，将流对象约简为非流值或对象。
public class ReduceOperation {

    //简单约简
    public static void simpleReduceOperation(){
        Stream<String> stream = Arrays.stream(ReadFile.readFileIntoStringArray());
        //通过约简操作，将流转换为非流的值或对象

        //1.计数
        long count = stream.count();
        //2.获取最大值
        Optional<String> maxValueOptional = stream.max(String::compareToIgnoreCase);
        //获取optional值，如果为空则赋值空串
        String maxValue = maxValueOptional.orElse("");
        //3.获取最小值，方式同上
        //4.返回非空集合的第一条记录，通常与filter组合使用
        Optional<String> findA = stream.filter(word -> word.startsWith("a")).findFirst();
        //5.返回任意满足条件的对象：findAny,该方法通常在并行处理流时很有效，所以将流转换为并行流处理
        Optional<String> findAnyA = stream.parallel().filter(word -> word.startsWith("a")).findAny();
        //6.返回是否存在匹配条件
        boolean isExistStartWithA = stream.anyMatch(word -> word.startsWith("a"));

    }


}
