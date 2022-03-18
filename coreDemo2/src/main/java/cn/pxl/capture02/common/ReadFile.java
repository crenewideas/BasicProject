package cn.pxl.capture02.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ReadFile{
    public static List<String> readFileIntoString() {
        byte[] bytes = new byte[0];
        try {
            //相对路径，相对于BasicProject来说
            bytes = Files.readAllBytes(Paths.get("coreDemo2\\src\\main\\java\\cn\\pxl\\capture01\\subsection01\\Word.txt"));
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
            bytes = Files.readAllBytes(Paths.get("coreDemo2\\src\\main\\java\\cn\\pxl\\capture01\\subsection01\\Word.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String string = new String(bytes);
        return  string.split("\\PL+");
    }

    public static File getFile(){
        //相对路径
        return new File("coreDemo2\\src\\main\\java\\cn\\pxl\\capture02\\subsection01\\Word.txt");
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));//E:\CODE\IDEACODE\OTHERS\BasicProject
    }
}