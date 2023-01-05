package com.abyss.cloudalibaba.controller;

import com.abyss.cloudalibaba.myhandler.CustomerBlockHandler;
import com.abyss.cloudalibaba.service.FlowLimitService;
import com.abyss.springcloud.entities.CommonResult;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abyss Watchers
 * @create 2023-01-05 15:37
 */
@RestController
@Slf4j
public class FlowLimitController {
    @Autowired
    FlowLimitService flowLimitService;

    @GetMapping("/testA")
    public String testA() {
        return "_____testA______" + flowLimitService.getUser();
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t___testB___");
        return "_____testB______" + flowLimitService.getUser();
    }

    @GetMapping("/testC")
    public String testC() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + "\t___testC___测试RT");
        return "_____testC______" + flowLimitService.getUser();
    }

    @GetMapping("/testD")
    public String testD() {
        log.info("testD 测试异常比例");
        int age = 10 / 0;
        return "------testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "testHotKeyBlockHandler")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "test hot key";
    }

    public String testHotKeyBlockHandler(String p1, String p2, BlockException blockException) {
        return "blocked o(╥﹏╥)o";
    }

    /**
     * 自定义通用的限流处理逻辑，
     blockHandlerClass = CustomerBlockHandler.class
     blockHandler = handleException2
     上述配置：找CustomerBlockHandler类里的handleException2方法进行兜底处理
     */
    /**
     * 自定义通用的限流处理逻辑
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "按客户自定义限流处理逻辑");
    }

}
