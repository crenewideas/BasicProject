package cn.pxl.config.metadata;

import cn.pxl.entity.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

//  自定义BeanDefinitionParser实现：XML元素与BeanDefinition解析
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        setPropertyValue("userName",element,builder);
        setPropertyValue("passWord",element,builder);
        setPropertyValue("age",element,builder);
    }

    private void setPropertyValue(String attributeName,Element element, BeanDefinitionBuilder builder){
        String attributeNameValue = element.getAttribute(attributeName);
        if(StringUtils.hasText(attributeNameValue)){
            builder.addPropertyValue(attributeName,attributeNameValue);// BeanDefinition 定义了 userName 属性
        }
    }
}
