package cn.pxl.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("cn.pxl.order.mapper")
@SpringBootApplication

@EnableDiscoveryClient()
//使用    feign   替代  restTemplate
//如果不加 basePackages 那么扫描的是 cn.pxl.order 包下的 FeignClient。而当前的 FeignClient 被提取到了feign-api中，因此扫描不到。
//@EnableDiscoveryClient
//指定扫描 cn.pxl.feign.clients 包
@EnableFeignClients(basePackages = "cn.pxl.feign.clients")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}