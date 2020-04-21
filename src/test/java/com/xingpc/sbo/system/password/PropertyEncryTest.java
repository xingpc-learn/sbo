package com.xingpc.sbo.system.password;

import com.xingpc.sbo.utils.PropertyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: XingPc
 * @Description: 配置文件加密解密测试
 * @Date: 2020/3/26 18:03
 * @Version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PropertyEncryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyEncryTest.class);

    @Test
    public void testEncrypt() {
        String root = PropertyEncry.encrypt("root");
        LOGGER.info(root);
    }

    @Test
    public void testDecrypt() {
        String root = PropertyEncry.decrypt("ENC(vhZHBzMnEKVLSNJRf6eb5w==)");
        LOGGER.info(root);
    }

}
