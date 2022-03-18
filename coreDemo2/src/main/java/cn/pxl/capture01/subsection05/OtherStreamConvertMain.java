package cn.pxl.capture01.subsection05;

import java.util.stream.Stream;

public class OtherStreamConvertMain {
    public static void main(String[] args) {
        Stream<String> stringStream = OtherStreamConvert.peekStream();
        System.out.println(stringStream.count());
    }
}
