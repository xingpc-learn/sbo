package com.xingpc.sbo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * @Author: XingPc
 * @Description: ${description}
 * @Date: 2020/2/18 15:19
 * @Version: 1.0
 */
@Controller
public class HelloController {

    @Value("${person.lastName}")
    private String name;

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping(value = "/sayHello")
    @ResponseBody
    public String sayHello() {
        return "Hello " + name + "!";
    }

    @RequestMapping(value = "/success")
    public String success(Map<String,Object> map) {
        map.put("name", "<h1>jack</h1>");
        map.put("nameList", Arrays.asList("zhangsan","macker","honi"));
        return "success";
    }

}
