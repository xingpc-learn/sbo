package com.xingpc.sbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author: XingPc
 * @Description: ${description}
 * @Date: 2020/3/26 17:25
 * @Version: 1.0
 */
@Service
public class BaseService {

    @Autowired
    private Environment environment;

    public String getProperty(String key) {
        return environment.getProperty(key);
    }

}
