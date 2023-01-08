package cn.pxl.order.service;

import cn.pxl.commonutils.result.ResultEntity;
import cn.pxl.order.mapper.OrderMapper;
import cn.pxl.order.pojo.Order;
import cn.pxl.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //远程调用 user-service 查询出用户数据
        String url = "http://192.168.0.103:8002/user/common/" + order.getUserId();
        User remoteUserInfo = restTemplate.getForObject(url, User.class);
        order.setUser(remoteUserInfo);
        // 4.返回
        return order;
    }
}
