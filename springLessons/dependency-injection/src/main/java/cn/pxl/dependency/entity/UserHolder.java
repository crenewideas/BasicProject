package cn.pxl.dependency.entity;

import cn.pxl.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserHolder {
    private User user;
    //城市枚举
    private City city;

}
