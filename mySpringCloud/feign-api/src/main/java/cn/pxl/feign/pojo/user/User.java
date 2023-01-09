package cn.pxl.feign.pojo.user;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String address;
}