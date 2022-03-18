package cn.pxl.capture01.subsection14;

import cn.pxl.capture01.common.ReadFile;

import java.util.List;
import java.util.stream.Stream;

public class ParallelStream {
    public static void parallelStreamTest(){
        List<String> stringList = ReadFile.readFileIntoString();
        Stream<String> stream = stringList.stream();
        //方式一
        Stream<String> stringStream = stringList.parallelStream();
        //方式二
        Stream<String> parallel = stream.parallel();


    }

}
