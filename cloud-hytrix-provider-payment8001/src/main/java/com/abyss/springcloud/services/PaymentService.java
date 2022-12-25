package com.abyss.springcloud.services;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    /**
     * 服务熔断
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), // 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreakerFallback(Integer id){
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}
