package com.wp.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description ribbon的RestTemplate的配置
 * @Author admin
 * @Date 2023/3/8 15:59
 */
@Configuration
public class RestTemplateRibbonConfig {
    @Bean
    @LoadBalanced
    public RestTemplate ribbonRestTemplate() {
        return new RestTemplate();
    }
}
