package cn.pxl.confing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MyLocaleConfig {

    private LocaleChangeInterceptor localeChangeInterceptor = null;

    //国际化解析器，采用 SessionLocaleResolver 进行解析。这里bean名称只能为 localeResolver ，spring Mvc 中的约定。
    @Bean(name = "localeResolver")
    public LocaleResolver initLocaleResolver(){
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        //默认国际化区域
        sessionLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return sessionLocaleResolver;
    }

    //创建国际化拦截器
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        if(localeChangeInterceptor != null){
            return localeChangeInterceptor;
        }
        localeChangeInterceptor = new LocaleChangeInterceptor();
        //拦截器将读取http请求中 名称为 language 的参数。
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }


}
