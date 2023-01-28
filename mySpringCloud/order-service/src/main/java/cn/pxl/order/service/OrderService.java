package cn.pxl.order.service;

import cn.pxl.feign.clients.UserClient;
import cn.pxl.feign.pojo.user.User;
import cn.pxl.order.mapper.OrderMapper;
import cn.pxl.order.pojo.Order;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    //默认情况下，OrderService中的方法是不被Sentinel监控的，需要我们自己通过注解来标记要监控的方法。
    //给OrderService的queryGoods方法添加@SentinelResource注解：
    @SentinelResource("goods")
    public void queryGoods(){
        System.err.println("查询商品");
    }

    //1.使用restTemplate完成nacos远程服务调用
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //远程调用 user-service 查询出用户数据
        //1.使用固定地址获取数据
//        String url = "http://192.168.0.103:8002/user/common/" + order.getUserId();
        //2.使用注册中心获取数据，注册中心可以是eureka、nacos等。

            //eureka注册中心：String url = "http://userservice/user/common/" + order.getUserId();

        //nacos注册中心：
        String url = "http://userservice/user/common/" + order.getUserId();
        User remoteUserInfo = restTemplate.getForObject(url, User.class);
        order.setUser(remoteUserInfo);
        // 4.返回
        return order;
    }

    //2.使用feign完成nacos远程服务调用
    public Order feingQueryOrderById(Long orderId) {
        Order order = orderMapper.findById(orderId);
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        return order;
    }
}
