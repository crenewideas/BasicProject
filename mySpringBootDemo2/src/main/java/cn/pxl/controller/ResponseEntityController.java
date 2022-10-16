package cn.pxl.controller;

import cn.pxl.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entity")
public class ResponseEntityController {


    @RequestMapping("/test")
    //指定状态码为201（资源创建成功）。
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> insertEntity(){
        HttpHeaders httpHeaders = new HttpHeaders();
        User user = new User();
        user.setName("PXL");
        httpHeaders.add("success","true");
        //数据、头、响应码
        return new ResponseEntity<User>(user,httpHeaders, HttpStatus.CREATED);
    }

}
