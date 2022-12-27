package cn.pxl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String name;
    private Integer age;

    public static User createUser(){
        User user = new User();
        user.setName("staticMethod");
        user.setAge(0);
        return user;
    }
}