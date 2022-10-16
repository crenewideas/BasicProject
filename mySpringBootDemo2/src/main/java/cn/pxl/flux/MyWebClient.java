package cn.pxl.flux;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class MyWebClient {

    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://localhost:8081");
        // /interceptor/test

        Mono<String> stringMono = webClient.get()
                .uri("/interceptor/test")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(String.class);
        //获取服务器发布的数据流，此时会发送请求
        String block = stringMono.block();
        System.out.println("用户名称："+block); //用户名称：success

    }

}
