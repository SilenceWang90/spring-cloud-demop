package com.wp.springcloud.controller;

import com.wp.springcloud.entity.Friend;
import com.wp.springcloud.service.GatewayService;
import com.wp.springcloud.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author admin
 * @Date 2023/3/15 15:22
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private IService iService;
    @Autowired
    private GatewayService gatewayService;

    @GetMapping("/sayHi")
    public String sayHi() {
        return iService.sayHi();
    }

    @PostMapping("/sayHiPost")
    public Friend sayHiPost(@RequestBody Friend friend) {
        return iService.sayHiPost(friend);
    }

    @GetMapping("/expressCongratulation")
    public String expressCongratulation(@RequestParam("aa") String aa, @RequestParam("bb") String bb) {
        return iService.expressCongratulation(aa, bb);
    }

    @GetMapping("/testRetry")
    public String testRetry(@RequestParam(name = "timeout") int timeout) {
        return iService.retry(timeout);
    }

    /**
     * 通过网关连接服务
     * @return
     */
    @GetMapping("/sayHiByGateway")
    public String sayHiByGateway() {
        return gatewayService.sayHi();
    }
}
