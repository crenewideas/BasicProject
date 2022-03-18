package cn.pxl.capture01.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ReadFile{
    public static List<String> readFileIntoString() {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get("E:\\CODE\\IDEACODE\\OTHERS\\BasicProject\\coreDemo2\\src\\main\\java\\cn\\pxl\\capture01\\subsection01\\Word.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String string = new String(bytes);
        String[] split = string.split("\\PL+");
        return Arrays.asList(split);
    }

    public static String[] readFileIntoStringArray() {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get("E:\\CODE\\IDEACODE\\OTHERS\\BasicProject\\coreDemo2\\src\\main\\java\\cn\\pxl\\capture01\\subsection01\\Word.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String string = new String(bytes);
        return  string.split("\\PL+");
    }

}