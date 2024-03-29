package com.wp.springcloud.service;

import com.wp.springcloud.entity.Friend;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 声明式接口类
 * @Author admin
 * @Date 2023/3/15 15:04
 */
@FeignClient(name = "eureka-client", path = "/test")
public interface IService {

    @GetMapping("/sayHi")
    String sayHi();

    @PostMapping("/sayHiPost")
    Friend sayHiPost(@RequestBody Friend friend);

    @GetMapping("/expressCongratulation")
    String expressCongratulation(@RequestParam("aa") String aa, @RequestParam("bb") String bb);

    @GetMapping("/retry")
    String retry(@RequestParam(name = "timeout") int timeout);
}
