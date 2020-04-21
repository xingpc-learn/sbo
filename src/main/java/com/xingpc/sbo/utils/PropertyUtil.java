package com.xingpc.sbo.utils;

import com.xingpc.sbo.service.BaseService;

/**
 * @Author: XingPc
 * @Description: 读取配置文件内容
 * @Date: 2020/3/26 17:32
 * @Version: 1.0
 */
public class PropertyUtil {

    public static String getProperty(String key){
        BaseService baseService = SpringContextHolder.getBean(BaseService.class);
        return baseService.getProperty(key);
    }

}
