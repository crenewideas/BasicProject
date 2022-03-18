package cn.pxl.capture02.subsection01;

import cn.pxl.capture02.common.ReadFile;

import java.util.List;

public class Demo01 {
    public static void main(String[] args) {
        List<String> stringList = ReadFile.readFileIntoString();
        //1.串行流计数
//        long count = stringList.stream().filter(str -> str.length() == 12).count();
//        System.out.println(count);

        //2.并行流计数
        long count = stringList.parallelStream().filter(str -> {
            System.out.println(Thread.currentThread().getName());
            return str.length() > 12;
        }).count();
        System.out.println(count);

    }
}


