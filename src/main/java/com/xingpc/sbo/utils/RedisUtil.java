package com.xingpc.sbo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Collections;

/**
 * @Author: XingPc
 * @Description: redis相关工具
 * @Date: 2020/3/27 14:08
 * @Version: 1.0
 */
@Component
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private JedisSentinelPool jedisPool;

    private Integer db_index;

    /** 默认获取锁超时异常信息 */
    private static final String DEFAULT_TIMEOUT_MESSAGE = "同时参与人数过多，请稍后再试！";

    /** 默认最大尝试获取锁时间，单位(s) */
    private static final long MAX_RETRY_TIME = 60L;

    /** 默认加锁时间，单位(s) */
    private static final long DEFAULT_LOCK_TIME = 2L;

    /**
     * 功能描述:
     *
     * @param dbIndex redis库索引
     * @return
     * @Author XingPc
     * @Date 16:21 2020/3/27
     */
    public void init(String dbIndex) {
        if (!StringUtils.isEmpty(this.db_index)) {
            this.db_index = Integer.valueOf(dbIndex);
        }
    }

    /**
     * 功能描述:循环尝试加锁，直至超时或获取到锁
     *
     * @param lockKey   锁的key值
     * @param requestId 加锁标识，解锁时用到
     * @param lockTime  获取到锁后，加锁时间(s)
     * @return true，表示加锁成功
     * @Author XingPc
     * @Date 16:09 2020/3/27
     */
    public boolean reTryGetLock(String lockKey, String requestId, Long lockTime) throws Exception {
        return reTryGetLock(lockKey, requestId, lockTime, MAX_RETRY_TIME, DEFAULT_TIMEOUT_MESSAGE);
    }

    /**
     * 功能描述:循环尝试加锁，直至超时或获取到锁
     *
     * @param lockKey   锁的key值
     * @param requestId 加锁标识，解锁时用到
     * @param timeoutMessage  获取锁超时返回信息
     * @return true，表示加锁成功
     * @Author XingPc
     * @Date 16:09 2020/3/27
     */
    public boolean reTryGetLock(String lockKey, String requestId, String timeoutMessage) throws Exception {
        return reTryGetLock(lockKey, requestId, DEFAULT_LOCK_TIME, MAX_RETRY_TIME, timeoutMessage);
    }

    /**
     * 功能描述:循环尝试加锁，直至超时或获取到锁
     *
     * @param lockKey   锁的key值
     * @param requestId 加锁标识，解锁时用到
     * @param timeoutMessage  获取锁超时返回信息
     * @param lockTime  获取锁时间
     * @return true，表示加锁成功
     * @Author XingPc
     * @Date 16:09 2020/3/27
     */
    public boolean reTryGetLock(String lockKey, String requestId,Long lockTime, String timeoutMessage) throws Exception {
        return reTryGetLock(lockKey, requestId, lockTime, MAX_RETRY_TIME, timeoutMessage);
    }

    /**
     * 功能描述:循环尝试加锁，直至超时或获取到锁
     *
     * @param lockKey   锁的key值
     * @param requestId 加锁标识，解锁时用到
     * @param maxRetryTime 最大尝试获取锁时间
     * @param lockTime  获取锁时间
     * @return true，表示加锁成功
     * @Author XingPc
     * @Date 16:09 2020/3/27
     */
    public boolean reTryGetLock(String lockKey, String requestId,Long lockTime, Long maxRetryTime) throws Exception {
        return reTryGetLock(lockKey, requestId, lockTime, maxRetryTime, DEFAULT_TIMEOUT_MESSAGE);
    }

    /**
     * 功能描述:循环尝试加锁，直至超时或获取到锁
     *
     * @param lockKey   锁的key值
     * @param requestId 加锁标识，解锁时用到
     * @return true，表示加锁成功
     * @Author XingPc
     * @Date 16:09 2020/3/27
     */
    public boolean reTryGetLock(String lockKey, String requestId) throws Exception {
        return reTryGetLock(lockKey, requestId, DEFAULT_LOCK_TIME, MAX_RETRY_TIME, DEFAULT_TIMEOUT_MESSAGE);
    }

    /**
     * 功能描述:循环尝试加锁，直至超时或获取到锁
     *
     * @param lockKey               锁的key值
     * @param requestId             加锁标识，解锁时用到
     * @param lockTime              获取到锁后，加锁时间(s)
     * @param maxRetryTime          最大尝试获取锁时间，单位(s)
     * @param defaultTimeoutMessage 获取锁失败返回消息
     * @return true，表示加锁成功
     * @Author XingPc
     * @Date 16:09 2020/3/27
     */
    private boolean reTryGetLock(final String lockKey, final String requestId, final Long lockTime, final long maxRetryTime, String defaultTimeoutMessage) throws Exception {
        final long startTime = System.currentTimeMillis();
        final long waitTime = maxRetryTime * 1000;
        while (!reTryGetDistrbuteLock(lockKey, requestId, lockTime)) {
            // 当获取锁失败，等待50毫秒，继续获取锁，直到成功。当获取锁时间达到最大尝试获取锁时间，抛出异常
            Thread.sleep(50);
            long now = System.currentTimeMillis();
            if (now - startTime > waitTime) {
                throw new Exception(defaultTimeoutMessage);
            }
        }
        return true;
    }

    /**
     * 功能描述:获取分布式锁
     *
     * @param lockKey   锁的key值
     * @param requestId 加锁标识，解锁时用到
     * @param lockTime  获取到锁后，加锁时间(s)
     * @return
     * @Author XingPc
     * @Date 17:35 2020/3/27
     */
    private boolean reTryGetDistrbuteLock(String lockKey, String requestId, Long lockTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 允许使用自定义数据库
            if (!StringUtils.isEmpty(db_index)) {
                jedis.select(db_index);
            }
            String result = jedis.set(lockKey, requestId, "NX", "PX", lockTime * 1000);
            if ("OK".equals(result)) {
                return true;
            }
            String oldValue = jedis.get(lockKey);
            // 可重入锁
            if (requestId.equals(oldValue)) {
                return true;
            }
        } catch (Exception e) {
            logger.error("获取分布式锁失败，",e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     *
     * 功能描述:释放分布式锁
     * @Author XingPc
     * @Date 18:12 2020/3/27
     * @param lockKey   锁的key值
     * @param requestId 加锁标识，对应锁的序号
     * @return
     */
    public boolean releaseDistributeLock(String lockKey, String requestId) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 允许使用自定义数据库
            if (!StringUtils.isEmpty(db_index)) {
                jedis.select(db_index);
            }
            String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
            if ("1L".equals(result)) {
                return true;
            }
        } catch (Exception e) {
            logger.error("解除分布式事务锁",e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

}
