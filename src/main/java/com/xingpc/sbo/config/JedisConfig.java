package com.xingpc.sbo.config;

import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: XingPc
 * @Description: jedis配置
 * @Date: 2020/3/30 11:34
 * @Version: 1.0
 */
@Configuration
public class JedisConfig {

    @Value("${spring.redis.sentinel.master}")
    private String masterName;

    @Value("${spring.redis.sentinel.nodes}")
    private String nodes;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.timeout}")
    private String timeout;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxTotal;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private String maxWaitMillis;

    /**
     *
     * 功能描述:返回的JedisSentinelPool是单例，并且可以注入其他类中
     * @Author XingPc
     * @Date 13:32 2020/3/30
     * @return
     */
    @Bean
    public JedisSentinelPool getSentinelInstance() {
        // 设置哨兵的位置
        Set<String> sentinelSet = new HashSet<String>();
        String[] sentinelsArray = nodes.split(",");
        for (String str: sentinelsArray) {
            if (!StringUtils.isEmpty(str)) {
                sentinelSet.add(str.trim());
            }
        }
        // 设置连接池参数
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(Long.valueOf(maxWaitMillis.substring(0,maxWaitMillis.length()-2 )));
        return new JedisSentinelPool(masterName,sentinelSet,config,Integer.valueOf(timeout.substring(0,timeout.length()-2)),null,database );
    }

}
