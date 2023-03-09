package com.wp.springcloud.config;

import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @Description
 * @Author admin
 * @Date 2023/3/9 18:43
 */
@RibbonClient(name = "eureka-client",configuration = RandomRule.class)
public class RibbonStrategy {
}
