package com.wp.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

/**
 * @Description
 * @Author admin
 * @Date 2023/9/7 16:55
 */
@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator customizedRoutes(RouteLocatorBuilder builder) {
        // 创建route路由策略
        return builder.routes()
                // route路由策略实现
                .route(route -> route
                        /**1、添加断言，通过and()、or()、negative()添加多个断言**/
                        // 添加path断言
                        .path("/java/**")
                        // 通过and、or、negative为一个route添加多个断言
                        .and()
                        // 添加method断言
                        .method(HttpMethod.GET)
                        /**2、添加filter过滤器，通过链式调用创建多个过滤器**/
                        // 如下stripPrefix()过滤器的作用是将请求中url端口号后或域名后的第一个地址符删除掉。比如请求地址http://localhost:65000/java/test/sayHi，那么过滤器会将yml这个地址符删除
                        // 如下addResponseHeader()过滤器的作用是将response响应体重增加java-param响应头
                        .filters(filter -> filter.stripPrefix(1)
                                .addResponseHeader("java-param","gateway-config"))
                        /**3、添加uri()设置匹配断言后的转发路径**/
                        // 断言匹配后转发到哪个服务上：通过lb负载均衡策略转发到EUREKA注册中心的EUREKA-CLIENT服务
                        .uri("lb://EUREKA-CLIENT"))
                .build();
    }
}
