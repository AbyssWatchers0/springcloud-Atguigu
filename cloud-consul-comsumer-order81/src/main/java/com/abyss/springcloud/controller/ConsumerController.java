package com.abyss.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Abyss Watchers
 * @create 2022-12-20 16:52
 */
@RestController
public class ConsumerController {
    private final String invoke_url = "http://cloud-consul-provider-payment";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentConsul() {
        return restTemplate.getForObject(invoke_url + "/payment/consul", String.class);
    }
}
