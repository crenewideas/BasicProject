package cn.pxl.validation;

import cn.pxl.entity.User;

import java.beans.*;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class Demo01JavaBeans {
    public static void main(String[] args) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(User.class,Object.class);
            //一：属性描述符
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            Stream.of(propertyDescriptors).forEach((onePropertyDescriptor) ->{
                // User 中的读取方法，也就是get方法
                Method readMethod = onePropertyDescriptor.getReadMethod();
                // User 中的写入方法，也就是set方法
                Method writeMethod = onePropertyDescriptor.getWriteMethod();
                System.out.println("readMethod:"+readMethod);
                System.out.println("writeMethod:"+writeMethod);
                System.out.println("-------------------------");
            });
            //二：方法描述
            Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);

        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }


}
