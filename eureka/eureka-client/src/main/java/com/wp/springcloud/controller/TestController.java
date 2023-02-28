package com.wp.springcloud.controller;

import com.wp.springcloud.entity.Friend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author admin
 * @Date 2023/2/28 15:08
 */
@RestController("/test")
@Slf4j
public class TestController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/sayHi")
    public String sayHi() {
        return "This is " + port;
    }

    @PostMapping("/sayHi")
    public Friend sayHiPost(@RequestBody Friend friend) {
        log.info("You are " + friend.getName());
        friend.setPort(port);
        return friend;
    }
}
