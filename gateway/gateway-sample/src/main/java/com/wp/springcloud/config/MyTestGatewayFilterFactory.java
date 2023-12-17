package com.wp.springcloud.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author admin
 * @Date 2023/12/17 13:36
 */
@Component
@Slf4j
public class MyTestGatewayFilterFactory extends AbstractGatewayFilterFactory<MyTestGatewayFilterFactory.Config> implements Ordered {

    /**
     * 构造方法；传入配置类MyTestGatewayFilterFactory.Config用于加载application.yml中声明的过滤器的参数信息
     */
    public MyTestGatewayFilterFactory() {
        super(Config.class);
    }


    /**
     * 过滤器实现
     * @param config 配置类
     * @return
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("配置信息：{}", config.getYmlName());
            log.info("配置信息：{}", config.getYmlAge());
            // 过滤器逻辑
            log.info("我是MyTestGatewayFilterFactory------------------------");
            return chain.filter(exchange);
        };
    }

    /**
     * 配置信息，这样在配置文件的args中就可以声明参数值
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Config {
        private String ymlName;
        private Long ymlAge;
    }

    /**
     * 排序
     * @return
     */
    @Override
    public int getOrder() {
        // 数值越小越先执行
        return 0;
    }
}
