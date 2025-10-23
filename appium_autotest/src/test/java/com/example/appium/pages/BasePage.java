package com.example.appium.pages;

import com.example.appium.base.DriverFactory;
import com.example.appium.utils.ElementHelper;
import com.example.appium.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class BasePage {
    protected AppiumDriver driver;
    protected WaitUtils waitUtils;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.waitUtils = new WaitUtils(driver);
        ElementHelper.setWaitUtils(this.waitUtils);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

//    public static void logWithScreenshot(String message) {
//        logInfo(message);
//        ScreenshotUtils.takeScreenshot(message);
//    }

//    protected void logInfo(String message) {
//        logger.info(message);
//        com.example.appium.utils.ReportManager.logInfo(message);
//    }




}