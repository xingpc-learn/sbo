package com.xingpc.sbo.exception;

/**
 * @Author: XingPc
 * @Description: 自定义异常
 * @Date: 2020/3/25 8:59
 * @Version: 1.0
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("用户不存在！");
    }

}
