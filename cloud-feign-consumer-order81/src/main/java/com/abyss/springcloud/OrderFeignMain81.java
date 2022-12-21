package com.abyss.springcloud;

import com.abyss.rule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Abyss Watchers
 * @create 2022-12-21 17:20
 */
@SpringBootApplication
@EnableFeignClients
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MyRule.class)
public class OrderFeignMain81 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain81.class, args);
    }
}
