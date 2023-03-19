package com.wp.springcloud.config;

import com.wp.springcloud.entity.Friend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author admin
 * @Date 2023/3/19 20:35
 */
@Configuration
@Slf4j
public class AuthInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            log.info("cookie的名称{}", cookie.getName());
            log.info("cookie的值{}", cookie.getValue());
        }
        String header1 = request.getHeader("header1");
        String header2 = request.getHeader("header2");
        log.info("获取header1为：{}，header2为：{}", header1, header2);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Friend friend = new Friend();
        friend.setName("我是cookie");
        friend.setPort("我是cookie的Port");
        Cookie cookie = new Cookie("特制", friend.toString());
        response.addCookie(cookie);
    }


}
