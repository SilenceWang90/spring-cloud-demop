package com.wp.springcloud.service;

import com.wp.springcloud.hystrix.FallBack;
import com.wp.springcloud.request.HystrixRequestParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author admin
 * @Date 2023/7/19 10:27
 */
@FeignClient(name = "eureka-client", path = "/test", fallback = FallBack.class)
public interface IService {
    @PostMapping("/error")
    String error(@RequestBody HystrixRequestParam requestParam, @RequestParam("gate") String gate);

    @GetMapping("/retry")
    String retry(@RequestParam("timeout") Integer timeout);
}
