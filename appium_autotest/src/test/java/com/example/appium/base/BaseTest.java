package com.example.appium.base;

import com.example.appium.listeners.TestListener;
import com.example.appium.utils.ReportManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ExtendWith(TestListener.class)
public class BaseTest {
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("=== Starting test: {} ===", testInfo.getDisplayName());

        // 主要调用位置：在每个测试方法执行前初始化驱动
        DriverFactory.initializeDriver();

        ReportManager.startTest(testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        logger.info("=== Finished test: {} ===", testInfo.getDisplayName());

        // 在每个测试方法执行后退出驱动
        DriverFactory.quitDriver();

        ReportManager.endTest();
    }


}