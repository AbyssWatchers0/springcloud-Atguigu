package com.abyss.springcloud.controller;

import com.abyss.springcloud.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abyss Watchers
 * @create 2022-12-22 17:37
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    String serverPort;

    @RequestMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String res = paymentService.paymentInfo_OK(id);
        log.info("result:" + res);
        return res;
    }

    @RequestMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        String res = paymentService.paymentIfo_Timeout(id);
        log.info("result:" + res);
        return res;
    }
}
