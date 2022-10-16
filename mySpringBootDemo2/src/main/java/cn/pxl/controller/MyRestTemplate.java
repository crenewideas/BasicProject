package cn.pxl.controller;

import cn.pxl.entity.User;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/restTemplate")
public class MyRestTemplate {



    @RequestMapping("/doTest")
    public String doTest(){
        RestTemplate restTemplate = new RestTemplate();
        User user = new User();

        //请求头。
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //组装请求头到http实体中。
        HttpEntity<User> userHttpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<User> userResponseEntity = restTemplate.postForEntity("http://localhost:8081/entity/test", userHttpEntity, User.class);
        User body = userResponseEntity.getBody();
        HttpHeaders headers = userResponseEntity.getHeaders();
        System.out.println(headers.get("success"));//true;
        //响应http状态码
        System.out.println(userResponseEntity.getStatusCodeValue());
        return body.getName();
    }
}
