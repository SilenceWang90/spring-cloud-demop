package com.wp.springcloud.controller;

import com.wp.springcloud.request.HystrixRequestParam;
import com.wp.springcloud.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/getError")
    public String getError(@RequestBody HystrixRequestParam requestParam) {
        return iService.error(requestParam);
    }
}
