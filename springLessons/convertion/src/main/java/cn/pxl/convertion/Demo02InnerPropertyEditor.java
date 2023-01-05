package cn.pxl.convertion;

import org.springframework.beans.propertyeditors.ByteArrayPropertyEditor;

//Spring 内建 PropertyEditor 实现
//用于 String —> 其他类型。
public class Demo02InnerPropertyEditor {
    public static void main(String[] args) {
        byteArray();
    }

    //
    private static void byteArray(){
        String byteText = "{97,65}";
        ByteArrayPropertyEditor byteArrayPropertyEditor = new ByteArrayPropertyEditor();
        byteArrayPropertyEditor.setAsText(byteText);
        System.out.println(byteArrayPropertyEditor.getAsText());
    }
}
