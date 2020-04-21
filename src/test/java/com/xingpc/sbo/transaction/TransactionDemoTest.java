package com.xingpc.sbo.transaction;

import com.xingpc.sbo.transaction.service.TransactionDemoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: XingPc
 * @Description: ${description}
 * @Date: 2020/1/21 17:59
 * @Version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionDemoTest {

    @Resource
    private TransactionDemoService transactionDemoService;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testNoTransaction() {
        //调用带没有事务注解，事务不生效，不回滚
        transactionDemoService.testNoTransaction();
    }

    @Test
    public void testHasTransactionHasExcetion() {
        //调用带有事务注解，事务生效，正常回滚
        transactionDemoService.testHasTransactionHasException();
    }

    @Test
    public void testNoInvokeTransaction() {
        //调用没有事务方法，方法内部调用含有事务注解方法，事务不生效，不能正常回滚
        transactionDemoService.testNoInvokeTransaction();
    }

    @Test
    public void testNoInvokeTransaction2() {
        //调用没有事务方法，方法内部代理方式调用含有事务注解方法，事务生效，正常回滚
        transactionDemoService.testNoInvokeTransaction2();
    }

    @Test
    public void testHasInvokeTransaction() {
        transactionDemoService.testHasInvokeTransaction();
    }

    @Test
    public void testHasInvokeTransaction1() {
        transactionDemoService.testHasInvokeTransaction1();
    }

    @Test
    public void testHasInvokeTransaction2() {
        transactionDemoService.testHasInvokeTransaction2();
    }

    @Test
    public void testHasInvokeTransaction3() {
        transactionDemoService.testHasInvokeTransaction3();
    }

    @Test
    public void testHasInvokeTransaction4() {
        transactionDemoService.testHasInvokeTransaction4();
    }

}
