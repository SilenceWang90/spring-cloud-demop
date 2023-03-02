package com.wp.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Author admin
 * @Date 2023/3/2 16:38
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaConsumerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaConsumerApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
