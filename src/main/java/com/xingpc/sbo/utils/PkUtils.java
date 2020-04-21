package com.xingpc.sbo.utils;

import java.util.UUID;

/**
 * @Author: XingPc
 * @Description: 生成唯一主键
 * @Date: 2020/3/30 9:27
 * @Version: 1.0
 */
public class PkUtils {

    /**
     *
     * 功能描述:获取32位唯一uuid
     * @Author XingPc
     * @Date 9:29 2020/3/30
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
