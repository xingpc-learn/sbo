package com.xingpc.sbo.service.asyn;

import com.xingpc.sbo.constant.SystemThreadConstant;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @Author: XingPc
 * @Description: ${description}
 * @Date: 2020/3/30 15:37
 * @Version: 1.0
 */
@Service
public class AsyncTestService {

    public void noAsync(int param) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第" + param +"次调用");
    }

    @Async(value = SystemThreadConstant.DEFAULT_EXECUTOR_SERVICE)
    public void noReturnAsync(int param) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第" + param +"次调用");
    }

    @Async(value = SystemThreadConstant.DEFAULT_EXECUTOR_SERVICE)
    public void noReturnAsync2(int param, CountDownLatch downLatch) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第" + param +"次调用");
        downLatch.countDown();
    }

    @Async(value = SystemThreadConstant.DEFAULT_EXECUTOR_SERVICE)
    public Future<String> returnAsync(int param) {
        Future<String> future = null;
        try {
            Thread.sleep(2000);
            future = new AsyncResult<>("hello sync" + param);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第" + param +"次调用");
        return future;
    }

}
