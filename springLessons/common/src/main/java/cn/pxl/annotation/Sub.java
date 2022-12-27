package cn.pxl.annotation;

import java.lang.annotation.*;

//识别子用户，这个注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sub  {
}
