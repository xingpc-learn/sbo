package com.xingpc.sbo.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author: XingPc
 * @Description: ${description}
 * @Date: 2020/3/25 10:50
 * @Version: 1.0
 */
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized...web应用启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextInitialized...当前web项目销毁");
    }
}
