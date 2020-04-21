package com.xingpc.sbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author XingPc
 */
@MapperScan(value = {"com.xingpc.sbo.mapper","com.xingpc.sbo.*.mapper"})
//@ImportResource(locations = {"classpath:HelloService.xml"})
@SpringBootApplication
@EnableCaching
@EnableAsync
public class SboApplication {

    public static void main(String[] args) {
        SpringApplication.run(SboApplication.class, args);
    }

}
