package com.xingpc.sbo.system.password;

import com.xingpc.sbo.utils.PropertyUtil;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @Author: XingPc
 * @Description: 关键信息加密
 * @Date: 2020/3/26 17:38
 * @Version: 1.0
 */
public class PropertyEncry {

    /**
     *
     * 功能描述:
     * @Author XingPc
     * @Date 11:02 2020/3/27
     * @param param
     * @return
     */
    public static String encrypt(String param) {
        String password = PropertyUtil.getProperty("jasypt.encryptor.password");
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        return encryptor.encrypt(param);
    }

    /**
     *
     * 功能描述:
     * @Author XingPc
     * @Date 11:03 2020/3/27
     * @param param
     * @return
     */
    public static String decrypt(String param) {
        String password = PropertyUtil.getProperty("jasypt.encryptor.password");
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        return encryptor.decrypt(param.replace("ENC", "").replace("(","").replace(")", ""));
    }

}
