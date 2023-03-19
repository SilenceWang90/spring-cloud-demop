package com.wp.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Author admin
 * @Date 2023/3/19 20:36
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AuthInfoInterceptor authInfoInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInfoInterceptor).addPathPatterns("/**");
    }
}
