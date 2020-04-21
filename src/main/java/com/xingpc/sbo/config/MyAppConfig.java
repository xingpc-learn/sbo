package com.xingpc.sbo.config;

import com.xingpc.sbo.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: XingPc
 * @Description: ${description}
 * @Date: 2020/2/18 17:08
 * @Version: 1.0
 */
@Configuration
public class MyAppConfig {

    /**
     * @Bean 将返回值放到容器中
     * @param
     * @Return
     */
    @Bean
    public HelloService helloService() {
        return new HelloService();
    }



}
