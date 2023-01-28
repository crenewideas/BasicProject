package cn.pxl.order.web;

import cn.pxl.commonutils.result.ResultEntity;
import cn.pxl.order.pojo.Order;
import cn.pxl.order.service.OrderService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
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

    //这个对应的是 热点规则 中的资源名称
    @SentinelResource("hot")
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

    @GetMapping("/read")
    public String queryOrder() {
        return "查询订单成功";
    }

    @GetMapping("/update")
    public String updateOrder() {
        return "更新订单成功";
    }
}
