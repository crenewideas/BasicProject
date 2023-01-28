package cn.pxl.feign.config;

import cn.pxl.feign.fallback.UserClientFallbackFactory;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }
}
