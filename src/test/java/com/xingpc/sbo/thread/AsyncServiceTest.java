package com.xingpc.sbo.thread;

import com.xingpc.sbo.service.asyn.AsyncTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: XingPc
 * @Description: 异步调用测试
 * @Date: 2020/3/30 15:35
 * @Version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AsyncServiceTest {

    @Autowired
    private AsyncTestService asyncTestService;

    @Test
    public void testNoAsync() {
        long start = System.currentTimeMillis();
        asyncTestService.noAsync(1);
        asyncTestService.noAsync(2);
        long end = System.currentTimeMillis();
        System.out.println("testNoAsync:" + (end-start));
    }

    @Test
    public void testNoReturnAsync() {
        long start = System.currentTimeMillis();
        asyncTestService.noReturnAsync(1);
        asyncTestService.noReturnAsync(2);
        long end = System.currentTimeMillis();
        System.out.println("testNoReturnAsync:" + (end-start));
    }

    @Test
    public void testNoReturnAsync2() throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch downLatch = new CountDownLatch(2);
        asyncTestService.noReturnAsync2(1,downLatch);
        asyncTestService.noReturnAsync2(2,downLatch);
        downLatch.await();
        System.out.println("countDownLatch执行完毕！");
        long end = System.currentTimeMillis();
        System.out.println("testNoReturnAsync:" + (end-start));
    }

    @Test
    public void testReturnAsync() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Future<String> future = asyncTestService.returnAsync(1);
        Future<String> future1 = asyncTestService.returnAsync(2);
        long end = System.currentTimeMillis();
        System.out.println(future.get()+","+future1.get());
        System.out.println("testReturnAsync:" + (end-start));
    }

}
