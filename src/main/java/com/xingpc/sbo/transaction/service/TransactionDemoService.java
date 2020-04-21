package com.xingpc.sbo.transaction.service;

import com.xingpc.sbo.transaction.mapper.TransactionDemoMapper;
import com.xingpc.sbo.utils.SpringContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: XingPc
 * @Description: 事务测试案例
 * @Date: 2020/1/21 17:57
 * @Version: 1.0
 */
@Service(value = "transactionDemoService")
public class TransactionDemoService {

    @Resource
    private TransactionDemoMapper transactionDemoMapper;

    public void testNoTransaction() {
        transactionDemoMapper.addAcct();
        int i = 1/0;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testHasTransaction() {
        transactionDemoMapper.addAcct();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void testHasTransaction2() {
        transactionDemoMapper.addAcct();
    }

    @Transactional(rollbackFor = Exception.class)
    public void testHasTransactionHasException() {
        transactionDemoMapper.addAcct();
        int i = 1/0;
    }

    public void testNoInvokeTransaction() {
        transactionDemoMapper.addAcct();//外部方法没有注解，插入成功
        testHasTransactionHasException();//有注解的内部调用，事务不生效
    }

    public void testNoInvokeTransaction2() {
        transactionDemoMapper.addAcct();//外部方法没有注解，插入成功
        TransactionDemoService bean = SpringContextHolder.getBean(TransactionDemoService.class);
        bean.testHasTransactionHasException(); //代理方式调用，事务生效，发生回滚
    }

    @Transactional(rollbackFor = Exception.class)
    public void testHasInvokeTransaction() {
        transactionDemoMapper.addAcct();//外部方法有注解，发生回滚
        testHasTransaction();//内部方法调用，外面有注解，发生回滚
        int i = 1/0;
    }

    @Transactional(rollbackFor = Exception.class)
    public void testHasInvokeTransaction1() {
        transactionDemoMapper.addAcct();//外部方法有注解，发生回滚
        testHasTransactionHasException();//内部方法调用，外面有注解，发生回滚
    }

    @Transactional(rollbackFor = Exception.class)
    public void testHasInvokeTransaction2() {
        transactionDemoMapper.addAcct();//外部有注解，发生回滚
        TransactionDemoService bean = SpringContextHolder.getBean(TransactionDemoService.class);
//        Propagation.REQUIRED //内部方法的事务会加入到外部事务中，外部发生异常，内部同样正常回滚
        bean.testHasTransaction();//代理方式调用，外边有注解，默认是发生回滚
        int i = 1/0;
    }

    @Transactional(rollbackFor = Exception.class)
    public void testHasInvokeTransaction3() {
        transactionDemoMapper.addAcct();//外部有注解，发生回滚
        TransactionDemoService bean = SpringContextHolder.getBean(TransactionDemoService.class);
        bean.testHasTransaction2();//代理方式调用，外边有注解，Propagation.REQUIRES_NEW 内部方法没有发生回滚，它会新开启一个事务，外部异常不影响内部提交
        int i = 1/0;
    }

    @Transactional(rollbackFor = Exception.class)
    public void testHasInvokeTransaction4() {
        transactionDemoMapper.addAcct();//外部有注解，发生回滚
        TransactionDemoService bean = SpringContextHolder.getBean(TransactionDemoService.class);
        bean.testHasTransaction2();//代理方式调用，外边有注解，Propagation.REQUIRES_NEW 内部方法没有发生回滚
        //如果是检查型异常，则不会回滚（上边两个方法都不会回滚）
        int i = 1/0;
    }

}
