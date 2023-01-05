package cn.pxl.convertion.convertor;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;

// 自定义转换器
public class PropertyToStringConvertor implements ConditionalGenericConverter {
    // 条件判断，判断是否能转换。
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Properties.class.equals(sourceType.getObjectType()) && String.class.equals(targetType.getObjectType());
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Properties.class,String.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        //2. String -> Properties
        Properties properties = (Properties)source;
        StringBuilder textString = new StringBuilder();
        properties.forEach((key,value)->{
            textString.append(key).append("=").append(value).append(System.getProperty("line.separator"));
        });
        return textString.toString();
    }
}
