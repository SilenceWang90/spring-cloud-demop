package com.wp.springcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description
 * @Author admin
 * @Date 2023/12/17 10:46
 */
@Component(value = "myFilter")
@Slf4j
public class MyFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("这是我的自定义过滤器");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 数值越小越先执行
        return 1;
    }
}
