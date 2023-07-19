package com.wp.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description
 * @Author admin
 * @Date 2023/7/19 10:27
 */
@FeignClient(name = "eureka-client", path = "/test")
public interface IService {
}
