package com.abyss.springcloud.controller;

import com.abyss.springcloud.server.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abyss Watchers
 * @create 2022-12-22 18:42
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {
    @Autowired
    PaymentService paymentService;

    @RequestMapping("/consumer/payment/hystrix/ok/{id}")
    @HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo_OK(id);
    }

    @HystrixCommand(fallbackMethod = "paymentIfo_TimeoutHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")}
    )
    @RequestMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo_Timeout(id);
    }

    public String paymentIfo_TimeoutHandler(Integer id) {
        return "线程池" + Thread.currentThread().getName() + " 服务调用超时！或对方支付系统异常, id " + id + "\t o(T_T)o";
    }

    public String defaultFallback() {
        return "global 异常处理";
    }
}
