package com.wp.springcloud.hystrix;

import com.wp.springcloud.request.HystrixRequestParam;
import com.wp.springcloud.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author admin
 * @Date 2023/7/19 10:57
 */
@Slf4j
@Component
public class FallBack implements IService {
    @Override
    public String error(HystrixRequestParam requestParam, String gate) {
        log.info("name是{}", requestParam.getName());
        log.info("age是{}", requestParam.getAge());
        log.info("gate是{}", gate);
        log.info("FallBack：I'm not a black sheep any more");
        return "FallBack：I'm not a black sheep any more";
    }

    @Override
    public String retry(Integer timeout) {
        return "you are late！";
    }

}
