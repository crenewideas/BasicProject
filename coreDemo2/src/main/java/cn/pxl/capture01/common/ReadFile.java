package cn.pxl.capture01.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReadFile{
    public static List<String> readFileIntoString() {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get("/Users/pengxiaoliang/Documents/TextDocument/SublimeDocument/nda.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String string = new String(bytes);
        String[] split = string.split("\\PL+");
        return Arrays.asList(split);
    }

    public static Stream<String> readFileLinesStream() {
        Stream<String> lines;
        try {
            lines = Files.lines(Paths.get("/Users/pengxiaoliang/Documents/TextDocument/SublimeDocument/nda.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
        return lines;
    }

    public static String[] readFileIntoStringArray() {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get("/Users/pengxiaoliang/Documents/TextDocument/SublimeDocument/nda.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String string = new String(bytes);
        return  string.split("\\PL+");
    }

    public static String readFileString() {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get("/Users/pengxiaoliang/Documents/TextDocument/SublimeDocument/nda.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }

}