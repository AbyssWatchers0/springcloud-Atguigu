package com.abyss.springcloud.controller;

import com.abyss.springcloud.entities.CommonResult;
import com.abyss.springcloud.entities.Payment;
import com.abyss.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Abyss Watchers
 * @create 2022-12-21 17:28
 */
@RestController
public class ConsumerController {
    @Resource
    PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public String feignTimeoutTest() {
        return paymentFeignService.feignTimeoutTest();
    }
}
