package cn.pxl.convertion.propertyEditor;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

// String -> Property
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport {
    //1. 实现方法
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        //2. String -> Properties
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        //3. 存储临时对象
        setValue(properties);

        //4. next 获取 Properties
    }
}
