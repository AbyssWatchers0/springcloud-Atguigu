package com.abyss.springcloud.controller;

import com.abyss.springcloud.entities.CommonResult;
import com.abyss.springcloud.entities.Payment;
import com.abyss.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author Abyss Watchers
 * @create 2022-12-10 19:36
 */
@Slf4j
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int res = paymentService.create(payment);
        log.info(serverPort+"插入结果：" + res);
        if (res > 0) {
            return new CommonResult(200, "插入数据成功:"+serverPort, res);
        } else {
            return new CommonResult(444, "插入数据失败:"+serverPort);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment res = paymentService.getPaymentById(id);
        log.info(serverPort+"查询到的数据为：" + res);
        if (res != null) {
            return new CommonResult<>(200, "查询成功:"+serverPort, res);
        } else {
            return new CommonResult<>(441, "查询失败:"+serverPort);
        }
    }

    @GetMapping("/payment/lb")
    public CommonResult<String> testLb() {
        return new CommonResult<>(200, "ok", serverPort);
    }
}
