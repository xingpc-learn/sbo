package com.xingpc.sbo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: XingPc
 * @Description: 以静态变量保存ApplicationContext,在任何地方访问Spring Bean
 * @Date: 2020/1/22 16:16
 * @Version: 1.0
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return SpringContextHolder.context;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T)context.getBean(name);
    }

    public static <T> T getBean(Class<T> clz) {
        return (T)context.getBean(clz);
    }

}
