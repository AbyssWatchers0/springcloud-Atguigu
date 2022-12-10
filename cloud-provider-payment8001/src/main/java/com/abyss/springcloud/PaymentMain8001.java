package com.abyss.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Abyss Watchers
 * @create 2022-12-10 13:17
 */
@SpringBootApplication
public class PaymentMain8001 {
    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(PaymentMain8001.class, args);
    }
}
