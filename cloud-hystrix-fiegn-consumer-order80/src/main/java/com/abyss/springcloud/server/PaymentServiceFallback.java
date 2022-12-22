package com.abyss.springcloud.server;

import org.springframework.stereotype.Component;

/**
 * @author Abyss Watchers
 * @create 2022-12-22 19:44
 */
@Component
public class PaymentServiceFallback implements PaymentService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentService paymentInfo_OK fallback, 对方服务器宕机 o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "paymentService paymentInfo_Timeout fallback, 对方服务器宕机 o(╥﹏╥)o";
    }
}
