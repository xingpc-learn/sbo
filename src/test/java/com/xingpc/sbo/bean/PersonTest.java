package com.xingpc.sbo.bean;

import com.xingpc.sbo.entities.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: XingPc
 * @Description: 配置文件映射组件测试
 * @Date: 2020/2/18 15:47
 * @Version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonTest {

    @Autowired
    private Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void contextLoads() {
        System.out.println(person);
    }

    @Test
    public void testImportResource() {
        boolean helloService = ioc.containsBean("helloService");
        System.out.println(helloService);
    }

}
