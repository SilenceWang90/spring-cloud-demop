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
    @Value("${info.profile}")
    private String profile;

//    @Value("${love}")
//    private String love;

    @GetMapping("/name")
    public String getName() {
        return name;
    }

    @GetMapping("/words")
    public String getWords() {
        return words;
    }

//    @GetMapping("/love")
//    public String getLove() {
//        return love;
//    }

    @GetMapping("/all")
    public void getAll() {
        System.out.println("name 信息是：" + name);
        System.out.println("words 信息是：" + words);
        System.out.println("info.profile 信息是：" + profile);
    }
}
