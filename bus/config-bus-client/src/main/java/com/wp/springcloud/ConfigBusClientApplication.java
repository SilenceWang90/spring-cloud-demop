package com.wp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Author admin
 * @Date 2023/9/3 14:51
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigBusClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigBusClientApplication.class, args);
    }
}
