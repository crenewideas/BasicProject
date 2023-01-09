package cn.pxl.order.web;

import cn.pxl.commonutils.result.ResultEntity;
import cn.pxl.order.pojo.Order;
import cn.pxl.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{orderId}")
    public ResultEntity<Order> queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        return ResultEntity.success(orderService.queryOrderById(orderId));
    }

    @GetMapping("/feign/{orderId}")
    public ResultEntity<Order> feignQueryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        return ResultEntity.success(orderService.feingQueryOrderById(orderId));
    }
}
