package cn.pxl.capture01.subsection02;

import java.util.stream.Stream;

//流的创建
public class Demo02 {
    public static void main(String[] args) {
        Stream<String> streamThree = CreateStream.createStreamThree();
        System.out.println(streamThree.count());
    }
}

