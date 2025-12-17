package com.wp.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wangpeng
 * @description 网关服务Feign声明
 * @date 2025/12/17 13:31
 **/
@FeignClient(name = "gateway", path = "java")
public interface GatewayService {
    @GetMapping("/test/sayHi")
    String sayHi();
}
