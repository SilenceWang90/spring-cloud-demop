package com.wp.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Description
 * @Author admin
 * @Date 2023/7/21 14:53
 */
@SpringCloudApplication
@EnableHystrixDashboard
public class HystrixDashBoardApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixDashBoardApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
