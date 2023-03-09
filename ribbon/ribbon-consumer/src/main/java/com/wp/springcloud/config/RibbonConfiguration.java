package com.wp.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Ribbon配置类
 * @Author admin
 * @Date 2023/3/9 18:00
 */
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule defaultLBStrategy() {
        return new RoundRobinRule();
    }
}
