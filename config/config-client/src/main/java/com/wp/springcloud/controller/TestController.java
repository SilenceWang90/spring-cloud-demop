package com.wp.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author admin
 * @Date 2023/8/22 17:19
 */
@RestController
@RequestMapping(value = "test")
public class TestController {
    // 直接从config配置中心获取
    @Value("${name}")
    private String name;
    // application.yml从config配置中心获取信息，然后再从application.yml获取
    @Value("${myWords}")
    private String words;

    @GetMapping("/name")
    public String getName() {
        return name;
    }

    @GetMapping("/words")
    public String getWords() {
        return words;
    }
}
