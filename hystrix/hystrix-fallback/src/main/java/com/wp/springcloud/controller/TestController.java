package com.wp.springcloud.controller;

import com.wp.springcloud.service.IService;
import org.springframework.web.bind.annotation.GetMapping;
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
public class TestController {
    @Resource
    private IService iService;

    @GetMapping("/getError")
    public String getError() {
        return iService.error();
    }
}
