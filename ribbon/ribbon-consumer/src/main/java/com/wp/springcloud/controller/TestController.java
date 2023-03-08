package com.wp.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description
 * @Author admin
 * @Date 2023/3/8 15:46
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RestTemplate ribbonRestTemplate;

    @GetMapping("/sayHi")
    public String sayHi() {
        // url为：http/https://{spring.application.name}/{servlet.context-path，没有就不需要}/{接口地址}
        // url的域名处只需要填写我们要调用的服务提供端的服务名称即可(spring.application.name，对应Eureka UI界面的服务名称)
        // 不需要像普通的Eureka Consumer那样配置完整的请求路径，当然这个前提是因为我们对RestTemplate的实现做了“特殊处理”
        return restTemplate.getForObject("http://eureka-client/test/sayHi", String.class);
    }
}
