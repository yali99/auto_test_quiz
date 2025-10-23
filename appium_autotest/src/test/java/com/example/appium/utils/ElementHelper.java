package com.example.appium.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ElementHelper {
    private static WaitUtils waitUtils;

    public static void setWaitUtils(WaitUtils utils) {
        waitUtils = utils;
    }

    public static void click(WebElement element) {
        waitUtils.waitForElementToBeClickable(element).click();
    }

    public static void sendKeys(WebElement element, String text) {
        WebElement visibleElement = waitUtils.waitForElementToBeVisible(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    public static String getText(WebElement element) {
        return waitUtils.waitForElementToBeVisible(element).getText();
    }

    public static boolean isDisplayed(WebElement element) {
        return waitUtils.waitForElementToBeVisible(element).isDisplayed();

    }

    // 新增方法：获取元素属性
    public static String getAttribute(WebElement element, String attribute) {
        return waitUtils.waitForElementToBeVisible(element).getAttribute(attribute);
    }

    // 新增方法：检查元素是否启用
    public static boolean isEnabled(WebElement element) {
        return waitUtils.waitForElementToBeVisible(element).isEnabled();
    }

    // 新增方法：获取元素坐标和尺寸
    public static org.openqa.selenium.Point getLocation(WebElement element) {
        return waitUtils.waitForElementToBeVisible(element).getLocation();
    }

    public static org.openqa.selenium.Dimension getSize(WebElement element) {
        return waitUtils.waitForElementToBeVisible(element).getSize();
    }
}