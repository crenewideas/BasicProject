package cn.pxl.capture02.subsection04;

import java.sql.SQLOutput;

public class MainClass {
    public static void main(String[] args) {
        //Demo01Path.demo01();
        //Demo02Files.readFileDemo();
        //Demo02Files.writeFileDemo();
        //Demo03CreDirectory.createDirectory();
        //Demo04CreEmptyOrTempFile.demo01();
        //Demo05MoveCopyOrDeleteFile.doDemo();
        //Demo06GetFileMsg.doDemo();
        //Demo07StreamPath.doDemo02();
        //Demo10MemoryMapFile.doDemo();
        Demo11FileLock.doDemo();
        new Thread(Demo11FileLock::doDemo);
    }
}
