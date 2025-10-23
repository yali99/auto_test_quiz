package com.test.utils;

import com.test.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private static WebDriverWait wait;

    // 初始化等待器
    public static void initWait(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(Config.WAIT_TIMEOUT));

    }

    // 等待元素可见
    public static WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // 等待元素可点击
    public static WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void ChangeWaitTimeout(int timeout) {
        wait.withTimeout(Duration.ofSeconds(timeout));
    }


}
