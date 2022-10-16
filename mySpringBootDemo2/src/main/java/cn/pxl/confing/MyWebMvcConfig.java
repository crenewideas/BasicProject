package cn.pxl.confing;

import cn.pxl.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    //在 MyLocaleConfig 中已经注册好了bean。
    @Autowired
    private LocaleChangeInterceptor localeChangeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册拦截器到mvc机制中
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new MyInterceptor());
        //拦截什么样的请求路径：ip:port/interceptor/*
        interceptorRegistration.addPathPatterns("/interceptor/*");

        registry.addInterceptor(localeChangeInterceptor);
    }
}
