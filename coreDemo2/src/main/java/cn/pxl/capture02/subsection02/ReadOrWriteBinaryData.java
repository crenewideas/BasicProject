package cn.pxl.capture02.subsection02;

import cn.pxl.capture02.common.ReadFile;

import java.io.*;

public class ReadOrWriteBinaryData {

    public static void main(String[] args) {
        try {
            dataOutPutDemo();
        } catch (IOException e) {
            System.out.println("失败！");
        }
    }

    private static void dataOutPutDemo() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(ReadFile.getFileByName("Word3"));
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        //将 int 类型的数字 输出为 4字节 （4个8位2进置）（4 * 8 = 32）
        dataOutputStream.writeInt(21949283);
    }
}
