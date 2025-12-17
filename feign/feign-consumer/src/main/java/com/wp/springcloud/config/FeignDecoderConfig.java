package com.wp.springcloud.config;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

/**
 * Feign配置类
 * 用于配置Feign客户端的全局行为
 */
@Configuration
public class FeignDecoderConfig {

    /**
     * 向springcloud-feign中加入自定义的HeaderPassthroughDecoder。
     * 此处使用了装饰器模式
     */
    @Bean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        // 1. 创建默认的 SpringDecoder，它负责将 Response Body 转为 Java Object
        Decoder defaultDecoder = new SpringDecoder(messageConverters);
        // 2. 将其包装在 HeaderPassthroughDecoder 中，增加Header透传功能
        return new HeaderPassthroughDecoder(defaultDecoder);
    }

    /**
     * 创建自定义的Decoder，用于获取Response信息，并将Response信息透传到当前的request请求中
     */
    static class HeaderPassthroughDecoder implements Decoder {
        /**
         * 被包装的原始解码器（即 SpringDecoder）
         * 我们将把真正的解码工作委托给它。
         */
        private final Decoder delegate;

        /**
         * 构造函数
         *
         * @param delegate 被代理的解码器
         */
        public HeaderPassthroughDecoder(Decoder delegate) {
            this.delegate = delegate;
        }

        /**
         * 解码方法
         * 当 Feign接收到HTTP响应时，会调用此方法。
         *
         * @param response Feign 封装的原始 HTTP 响应对象，包含状态码、Headers 和 Body。
         * @param type     调用方期望的返回类型（例如 String.class 或 User.class）。
         * @return 解码后的 Java 对象。
         * @throws IOException    IO异常
         * @throws FeignException Feign相关异常
         */
        @Override
        public Object decode(Response response, Type type) throws IOException, FeignException {
            // 步骤 1：获取当前请求的上下文（Spring MVC 的 HttpServletResponse）
            // RequestContextHolder 是 Spring 提供的一个持有线程局部变量的工具，
            // 用于在任何地方获取当前请求的 Request 和 Response 对象。
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes != null) {
                HttpServletResponse servletResponse = attributes.getResponse();
                if (servletResponse != null) {
                    // 步骤 2：遍历 Feign Response 的 Headers，透传给 HttpServletResponse
                    // response.headers() 返回的是 Map<String, Collection<String>>，即一个 Header 名对应多个值
                    for (Map.Entry<String, Collection<String>> entry : response.headers().entrySet()) {
                        String key = entry.getKey();
                        // 逻辑控制：这里我们通过判断 Header 的名称来决定是否透传。
                        // 示例：只透传名称为 "java-param" 的 Header。
                        // 如果不加这个 if 判断，则会透传所有 Header（可能会包含一些不需要的系统 Header）。
                        if ("java-param".equalsIgnoreCase(key)) {
                            // 遍历该 Header 的所有值（因为 HTTP 允许一个 Header 有多个值）
                            for (String value : entry.getValue()) {
                                // 将 Header 写入到当前 Web 请求的响应中，这样 Postman 就能收到了
                                servletResponse.addHeader(key, value);
                            }
                        }
                    }
                }
            }

            // -----------------------------------------------------------
            // 步骤 3：执行原有的解码逻辑
            // -----------------------------------------------------------
            // 调用 SpringDecoder 将 Body 转为 Java 对象并返回
            return delegate.decode(response, type);
        }
    }
}
