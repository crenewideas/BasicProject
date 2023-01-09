package cn.pxl.order.pojo;

import cn.pxl.feign.pojo.user.User;
import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long price;
    private String name;
    private Integer num;
    private Long userId;
    private User user;
}