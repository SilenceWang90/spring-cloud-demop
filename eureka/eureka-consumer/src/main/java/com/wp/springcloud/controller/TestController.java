package com.wp.springcloud.controller;

import com.wp.springcloud.entity.Friend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description
 * @Author admin
 * @Date 2023/3/2 16:49
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() {
        // 通过eureka包中自带的负载均衡器，拉取注册中心服务列表中的服务。我们可以通过项目名称指定要拉取哪个服务的可用实例(所谓可用的实例就是通过负载均衡选择后的服务器)。
        ServiceInstance instance = loadBalancerClient.choose("eureka-client");
        // 如果获取不到则返回异常提示
        if (instance == null) {
            return "No available instances";
        }
        // 通过获取的服务实例，得到对应服务的IP和端口
        String target = String.format("http://%s:%s/test/sayHi", instance.getHost(), instance.getPort());
        log.info("url is {}", target);
        // 发起真实调用
        return restTemplate.getForObject(target, String.class);
    }

    @PostMapping("/hello")
    public Friend helloPost() {
        // 通过eureka包中自带的负载均衡器，拉取注册中心服务列表中的服务。我们可以通过项目名称指定要拉取哪个服务的可用实例(所谓可用的实例就是通过负载均衡选择后的服务器)。
        ServiceInstance instance = loadBalancerClient.choose("eureka-client");
        // 如果获取不到则返回异常提示
        if (instance == null) {
            return null;
        }
        // 通过获取的服务实例，得到对应服务的IP和端口
        String target = String.format("http://%s:%s/test/sayHi", instance.getHost(), instance.getPort());
        log.info("url is {}", target);

        Friend friend = new Friend();
        friend.setName("Eureka Consumer");
        // 发起真实调用
        return restTemplate.postForObject(target, friend, Friend.class);
    }
}
