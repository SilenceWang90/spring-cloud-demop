package com.wp.springcloud.controller;

import com.wp.springcloud.entity.Friend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author admin
 * @Date 2023/2/28 15:08
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/sayHi")
    public String sayHi() {
        return "This is " + port;
    }

    @PostMapping("/sayHiPost")
    public Friend sayHiPost(@RequestBody Friend friend) {
        log.info("You are " + friend.getName());
        friend.setPort(port);
        return friend;
    }

    @GetMapping("/expressCongratulation")
    public String expressCongratulation(@RequestParam("aa") String aa, @RequestParam("bb") String bb) {
        return aa + bb + "!";
    }

    @GetMapping("/retry")
    public String retry(@RequestParam(name = "timeout") int timeout) throws InterruptedException {
        while (timeout-- >= 0) {
            Thread.sleep(1000);
        }
        log.info("retry " + port);
        return port;
    }

    @GetMapping("/error")
    public String error() {
        throw new RuntimeException("black sheep");
    }
}
