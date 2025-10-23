package com.claims.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class DriverUtils {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if(driver == null) {
            String browser = ConfigUtils.getProperty("browser.type", "chrome");
            BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());
            switch (browserType) {
                case CHROME:
                    driver = initChromeDriver();
                    break;
                case EDGE:
                    driver = initEdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("不支持的浏览器：" + browser);
            }
            driver.manage().window().maximize(); //最大化窗口
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); //隐式等待
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50)); //页面加载超时
        }
        return driver;
    }

    private static WebDriver initChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        // 创建 ChromeDriver 实例
        return new ChromeDriver(options);
    }

    private static WebDriver initEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        // 创建 EdgeDriver 示例
        return new EdgeDriver(options);
    }
    public static void openUrl(String url) {
        getDriver().get(url);
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
