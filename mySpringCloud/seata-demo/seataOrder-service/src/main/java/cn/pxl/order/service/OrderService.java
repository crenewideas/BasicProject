package cn.pxl.order.service;

import cn.pxl.order.entity.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    Long create(Order order);
}