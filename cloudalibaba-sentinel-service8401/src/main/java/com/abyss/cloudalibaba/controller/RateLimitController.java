package com.abyss.cloudalibaba.controller;

import com.abyss.springcloud.entities.CommonResult;
import com.abyss.springcloud.entities.Payment;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abyss Watchers
 * @create 2023-01-05 19:30
 */
@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流成功", new Payment(2021L, "serial001"));
    }
    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + " " + exception.getMessage() + "\t服务不可用");
    }
}
