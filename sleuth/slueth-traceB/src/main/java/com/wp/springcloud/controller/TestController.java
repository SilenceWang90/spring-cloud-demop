package com.wp.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @Author admin
 * @Date 2023/9/18 10:54
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/traceB")
    public String traceB() {
        log.info("------Trace B");
        return restTemplate.getForEntity("http://sleuth-traceA/traceA", String.class)
                .getBody();
    }
}
