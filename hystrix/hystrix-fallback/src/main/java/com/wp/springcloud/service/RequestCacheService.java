package com.wp.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.wp.springcloud.entity.Friend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author admin
 * @Date 2023/7/19 16:04
 */
@Service
@Slf4j
public class RequestCacheService {
    @Resource
    private IService service;

    @CacheResult
    @HystrixCommand(commandKey = "wpCacheKey")
    public Friend requestCache(@CacheKey String name) {
        log.info("request cache {}", name);
        Friend friend = new Friend();
        friend.setName(name);
        friend = service.sayHiPost(friend);
        log.info("after requesting cache {}", name);
        return friend;
    }
}
