package com.wp.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author admin
 * @Date 2023/7/19 12:09
 */
@Service
@Slf4j
public class HystrixTestService {

    @HystrixCommand(fallbackMethod = "testHystrixCommandFallBack")
    public String testHystrixCommand(String param1) {
        throw new RuntimeException("执行失败");
    }

    private String testHystrixCommandFallBack(String param1, Throwable throwable) {
        log.info("异常信息：{}", throwable.toString());
        log.info("参数为：{}", param1);
        return "失败补偿lalala";
    }
}
