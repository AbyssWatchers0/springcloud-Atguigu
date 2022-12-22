package com.abyss.springcloud.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

/**
 * @author Abyss Watchers
 * @create 2022-12-22 17:30
 */
@Service
public class PaymentService {
    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池" + Thread.currentThread().getName() + " paymentInfo_OK, id " + id + "\t\\^_^/";
    }

    /**
     * 超时访问
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentIfo_TimeoutHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")}
            )
    public String paymentIfo_Timeout(Integer id) {
//        int i = 10 / 0;
        int timeDelay = 5;
        try {
            Thread.sleep(timeDelay * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + " paymentIfo_Timeout, id " + id + "\t:) 耗时：" + timeDelay + "秒钟";
    }

    public String paymentIfo_TimeoutHandler(Integer id) {
        return "线程池" + Thread.currentThread().getName() + " 服务器繁忙 或 系统故障, id " + id + "\t o(T_T)o";
    }
}
