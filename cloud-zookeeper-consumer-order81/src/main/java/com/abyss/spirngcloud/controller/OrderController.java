package com.abyss.spirngcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Abyss Watchers
 * @create 2022-12-20 15:49
 */
@RestController
public class OrderController {
    private final String INVOKE_URL = "http://cloud-zookeeper-provider-payment";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL+"/payment/zk", String.class);
    }
}
