package com.abyss.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Abyss Watchers
 * @create 2022-12-22 18:39
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class ConsumerHystrix81 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrix81.class, args);
    }
}
