package com.xingpc.sbo.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.xingpc.sbo.constant.SystemThreadConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @Author: XingPc
 * @Description: 线程池配置
 * @Date: 2020/3/30 15:11
 * @Version: 1.0
 */
@Configuration
public class ThreadPoolsConfig {

    /** 参数初始化 */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /** 核心线程数量大小 */
    private static final int corePoolSize = Math.max(2, Math.min(CPU_COUNT - 1, 4));

    /** 线程池最大容纳线程数 */
    private static final int maximumPoolSize = CPU_COUNT * 2 + 1;

    /** 线程空闲后的存活时长 */
    private static final int keepAliveTime = 10;

    /**
     *
     * 功能描述:默认线程池
     * @Author XingPc
     * @Date 16:51 2020/3/30
     * @return
     */
    @Bean(name = SystemThreadConstant.DEFAULT_EXECUTOR_SERVICE)
    public ThreadPoolExecutor defaultExecutorService() {
        ThreadPoolExecutor defaultExecutorService = new ThreadPoolExecutor(corePoolSize,maximumPoolSize ,keepAliveTime , TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(2048),new ThreadFactoryBuilder().setNameFormat("default-pool-%d").build(),new ThreadPoolExecutor.AbortPolicy());
        defaultExecutorService.allowCoreThreadTimeOut(true);
        return defaultExecutorService;
    }

}
