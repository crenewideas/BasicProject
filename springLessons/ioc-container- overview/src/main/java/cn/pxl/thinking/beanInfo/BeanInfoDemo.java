package cn.pxl.thinking.beanInfo;

import cn.pxl.entity.User;
import java.beans.*;
import java.util.stream.Stream;

public class BeanInfoDemo {
    public static void main(String[] args) {
        try {
//            BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
            BeanInfo beanInfo = Introspector.getBeanInfo(User.class,Object.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            Stream.of(propertyDescriptors).forEach(propertyDescriptor -> { //propertyDescriptor 允许增加 propertyEditor。
                String name = propertyDescriptor.getName();
                Class<?> propertyType = propertyDescriptor.getPropertyType();
                if("age".equals(name)){
                    propertyDescriptor.createPropertyEditor(StringToIntegerPropertyEditor.class);
                }
            });
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer integer = Integer.valueOf(text);
            setValue(integer);
        }
    }

}
