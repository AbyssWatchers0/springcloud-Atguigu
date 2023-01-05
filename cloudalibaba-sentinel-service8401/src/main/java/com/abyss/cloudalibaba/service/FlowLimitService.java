package com.abyss.cloudalibaba.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Abyss Watchers
 * @create 2023-01-05 16:39
 */
@Service
public class FlowLimitService {
    // SentinelResource 把方法注册为sentinel资源
    @SentinelResource("getUser")
    public String getUser() {
        return "user" + UUID.randomUUID().toString();
    }
}
