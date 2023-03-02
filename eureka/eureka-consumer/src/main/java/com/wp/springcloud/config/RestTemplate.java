package com.wp.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author admin
 * @Date 2023/3/2 17:09
 */
@Configuration
public class RestTemplate {
    @Bean
    public RestTemplate register() {
        return new RestTemplate();
    }
}
