package cn.pxl.convertion;

import cn.pxl.convertion.propertyEditor.StringToPropertiesPropertyEditor;
//自建 propertyEditor 实现
public class Demo01PropertyEditor {
    public static void main(String[] args) {
        String text = "name = pxl";
        StringToPropertiesPropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        //传递 String 类型的内容
        propertyEditor.setAsText(text);
        //从 PropertyEditor 中获取内容
        System.out.println(propertyEditor.getValue());

    }
}
