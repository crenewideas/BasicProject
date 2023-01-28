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
@RequestMapping("/sentinel")
public class SentinelController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/query")
    public String queryOrder() {
        // 查询商品
        orderService.queryGoods();
        // 查询订单
        System.out.println("查询订单");
        return "查询订单成功";
    }

    @GetMapping("/save")
    public String saveOrder() {
        // 查询商品
        orderService.queryGoods();
        // 新增订单
        System.err.println("新增订单");
        return "新增订单成功";
    }

    @GetMapping("/update")
    public String updateOrder() {
        return "更新订单成功";
    }
}
