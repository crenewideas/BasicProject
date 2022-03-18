package cn.pxl.capture01.subsection03;

import java.util.ArrayList;
import java.util.stream.Stream;
//PXL TODO offsetByCodePoints是什么含义？？
public class CodePoints {
    public static Stream<String> getCodePoints(String str){
        ArrayList<String> results = new ArrayList<>();
        int i = 0;
        while ( i < str.length()){
            int j = str.offsetByCodePoints(i, 1);
            results.add(str.substring(i,j));
            i = j;
        }
        return results.stream();
    }
}
