package com.wp.springcloud.service;

import com.wp.springcloud.hystrix.FallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description
 * @Author admin
 * @Date 2023/7/19 10:27
 */
@FeignClient(name = "eureka-client", path = "/test", fallback = FallBack.class)
public interface IService {
    @GetMapping("/error")
    String error();
}
