package com.abyss.cloudalibaba.controller;

import com.abyss.cloudalibaba.entity.CommonResult;
import com.abyss.cloudalibaba.entity.Order;
import com.abyss.cloudalibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Abyss Watchers
 * @create 2023-01-06 18:43
 */
@RestController
public class OrderController {
    @Resource
    OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }
}
