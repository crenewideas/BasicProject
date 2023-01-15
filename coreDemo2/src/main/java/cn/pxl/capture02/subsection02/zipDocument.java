package cn.pxl.capture02.subsection02;

import cn.pxl.capture02.common.ReadFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class zipDocument {

    public static void main(String[] args) throws IOException {
        writeZipDemo();
    }

    //通读zip文档的代码示例
    private static void readZipDemo() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(ReadFile.getFileByName("Zip.zip"));
        ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) !=null ){
            zipEntry.getComment();
            zipEntry.getName();
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
    }


    //写出zip文档的代码示例
    private static void writeZipDemo() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(ReadFile.getFileByName("OutFile.zip"));
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        ZipEntry zipEntry = new ZipEntry("testName");
        zipEntry.setExtra(new byte[]{1,2,3,4,5});
        zipOutputStream.putNextEntry(zipEntry);
        zipOutputStream.closeEntry();
        zipOutputStream.close();
    }
}
