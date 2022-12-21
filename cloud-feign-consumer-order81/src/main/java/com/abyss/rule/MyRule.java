package com.abyss.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Abyss Watchers
 * @create 2022-12-21 17:41
 */
@Configuration
public class MyRule {
    @Bean
    public IRule mySelfRule() {
        return new RandomRule();
    }
}
