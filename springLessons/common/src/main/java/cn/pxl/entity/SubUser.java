package cn.pxl.entity;

import cn.pxl.annotation.Sub;
import lombok.Data;

@Data
@Sub //自定义注解，标识当前类是一个 Sub 类
public class SubUser extends User{
    private String address;

    @Override
    public String toString() {
        return "SubUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
