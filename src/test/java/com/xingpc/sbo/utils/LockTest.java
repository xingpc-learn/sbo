package com.xingpc.sbo.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: XingPc
 * @Description: 分布式锁测试
 * @Date: 2020/3/30 9:24
 * @Version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LockTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testDistributeLock() {
        final String lockKey = "lock_demo_";
        final String requestId = PkUtils.getUuid();
        try {
            if (redisUtil.reTryGetLock(lockKey, requestId, 3L)){
                System.out.println("执行业务逻辑！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisUtil.releaseDistributeLock(lockKey,requestId );
        }
    }

}
