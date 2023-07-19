package com.wp.springcloud.controller;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.wp.springcloud.entity.Friend;
import com.wp.springcloud.request.HystrixRequestParam;
import com.wp.springcloud.service.HystrixTestService;
import com.wp.springcloud.service.IService;
import com.wp.springcloud.service.RequestCacheService;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private HystrixTestService hystrixTestService;
    @Resource
    private RequestCacheService requestCacheService;

    /**
     * 基于Feign中的Hystrix服务降级
     *
     * @param requestParam
     * @param gate
     * @return
     */
    @PostMapping("/getError")
    public String getError(@RequestBody HystrixRequestParam requestParam, @RequestParam("gate") String gate) {
        return iService.error(requestParam, gate);
    }

    /**
     * 基于@HystrixComman的服务降级
     *
     * @return
     */
    @GetMapping("/getHystrixCommand")
    public String getHystrixCommand() {
        return hystrixTestService.testHystrixCommand("测试传参");
    }

    @GetMapping("/timeout")
    public String timeout(@RequestParam("timeout") Integer timeout) {
        return iService.retry(timeout);
    }

    @GetMapping("/getFriend")
    public Friend getFriend(@RequestParam("name") String name) {
        // 开启缓存上下文
        // lombok提供的方法，会调用修饰对象的close()方法，简化通过try catch finally手动关闭资源
        // 如果方法名不叫close叫shutdown、halt等，那就只需要增加注解参数修改关闭的方法名即可
        @Cleanup("close") HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        Friend friend = requestCacheService.requestCache(name);
        friend = requestCacheService.requestCache(name);
        return friend;
    }

}
