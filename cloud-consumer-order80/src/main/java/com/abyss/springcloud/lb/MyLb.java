package com.abyss.springcloud.lb;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Abyss Watchers
 * @create 2022-12-21 16:12
 */
@Component
public class MyLb implements LoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int cur;
        int next;
        for (;;) {
            cur = atomicInteger.get();
            next = cur >= Integer.MAX_VALUE ? 0 : cur + 1;
            if (atomicInteger.compareAndSet(cur, next)) {
                System.out.println("第" + next + "次访问");
                return next;
            }
        }
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int next = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(next);
    }
}
