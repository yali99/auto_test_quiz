package com.test.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverUtils {
    private static WebDriver driver;

    // 初始化驱动（支持 Chrome/Edge）
    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("不支持的浏览器：" + browser);
            }
            driver.manage().window().maximize(); //最大化窗口
        }
        return driver;
    }

    // 关闭驱动
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // 重置驱动，避免下次使用残留
        }
    }
}
