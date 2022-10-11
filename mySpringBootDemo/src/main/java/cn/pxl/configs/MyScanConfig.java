package cn.pxl.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "cn.pxl.beans",lazyInit = true)
public class MyScanConfig {
}
