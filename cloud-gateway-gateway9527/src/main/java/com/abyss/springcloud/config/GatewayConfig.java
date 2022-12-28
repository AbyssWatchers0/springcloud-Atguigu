package com.abyss.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Abyss Watchers
 * @create 2022-12-28 18:37
 */
@Configuration
public class GatewayConfig {

    /**
     * 配置了一个id为route-name的路由规则，
     * 当访问地址 http://localhost:9527/pcgame 时会自动转发到地址：https://xxxxx520.com/pcgame
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        RouteLocator locator = routes.route("route_switch520",
                r -> r.path("/pcgame")
                        .uri("https://xxxxx520.com/")
        ).build();

        return locator;
    }
}
