package cn.pxl.convertion.registry;

import cn.pxl.convertion.entity.Person;
import cn.pxl.convertion.propertyEditor.StringToPropertiesPropertyEditor;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.stereotype.Component;

// 自定义 PropertyEditorRegistrar
public class MyPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // 1. 通用的类型转换
        registry.registerCustomEditor(Person.class,"properties", new StringToPropertiesPropertyEditor());
        // 2. 针对 Java Bean 属性类型转换
    }
}
