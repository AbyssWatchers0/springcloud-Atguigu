package com.abyss.springcloud.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Abyss Watchers
 * @create 2022-12-22 18:40
 */
@Service
@FeignClient(value = "CLOUD-HYSTRIX-PROVIDER-PAYMENT", fallback = PaymentServiceFallback.class)
public interface PaymentService {

    @RequestMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);


    @RequestMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id);
}
