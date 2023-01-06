package com.abyss.lbrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Abyss Watchers
 * @create 2023-01-06 17:33
 */
@Configuration
public class MyRule {
    @Bean
    public IRule getMyRule() {
        return new RandomRule();
    }
}
