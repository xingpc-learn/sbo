package com.xingpc.sbo.transaction.mapper;

import org.apache.ibatis.annotations.Insert;

/**
 * @Author: XingPc
 * @Description: 事务测试mapper接口
 * @Date: 2020/1/21 17:45
 * @Version: 1.0
 */
public interface TransactionDemoMapper {

    /**
     * 测试方法，向money表添加数据
     * @param 
     * @Return 
     */
    @Insert(value = "insert into money(id,create_at) value(UUID(),now())")
    void addAcct();

}
