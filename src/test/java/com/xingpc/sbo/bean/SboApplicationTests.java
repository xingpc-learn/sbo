package com.xingpc.sbo.bean;

import com.xingpc.sbo.SboApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SboApplicationTests {

    @Autowired
    DataSource dataSource;

    /*
    * springboot 默认级别info
    * */
    Logger logger = LoggerFactory.getLogger(SboApplication.class);

    @Test
    public void contextLoads() {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }

    @Test
    public void contextLoads_jdbc() throws SQLException {
        logger.info(""+dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

}
