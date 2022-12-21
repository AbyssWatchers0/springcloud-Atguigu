package com.abyss.springcloud.controller;


import com.abyss.springcloud.entities.CommonResult;
import com.abyss.springcloud.entities.Payment;
import com.abyss.springcloud.lb.MyLb;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author Abyss Watchers
 * @create 2022-12-17 12:28
 */
@RestController
@Slf4j
public class OrderController {
    public static final String BASE_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(BASE_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(BASE_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getByEntity/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(BASE_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(entity.getStatusCodeValue(), "RPC error");
        }
    }

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    MyLb myLb;

    @GetMapping("/consumer/payment/lb")
    public CommonResult<String> lbTest() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() == 0) {
            return new CommonResult<>(400, "没有找到微服务");
        }
        ServiceInstance instance = myLb.instance(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", CommonResult.class);
    }
}
