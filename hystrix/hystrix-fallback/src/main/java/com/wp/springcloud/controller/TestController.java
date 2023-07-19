package com.wp.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wp.springcloud.request.HystrixRequestParam;
import com.wp.springcloud.service.HystrixTestService;
import com.wp.springcloud.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @Author admin
 * @Date 2023/7/19 11:00
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {
    @Resource
    private IService iService;
    @Resource
    private HystrixTestService hystrixTestService;

    /**
     * 基于Feign中的Hystrix服务降级
     * @param requestParam
     * @param gate
     * @return
     */
    @PostMapping("/getError")
    public String getError(@RequestBody HystrixRequestParam requestParam, @RequestParam("gate") String gate) {
        return iService.error(requestParam, gate);
    }

    /**
     * 基于@HystrixComman的服务降级
     * @return
     */
    @GetMapping("/getHystrixCommand")
    public String getHystrixCommand() {
        return hystrixTestService.testHystrixCommand("测试传参");
    }
}
