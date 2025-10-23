package com.claims.steps;

import com.claims.utils.DriverUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    private WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("=== Starting Scenario: " + scenario.getName() + " ===");
        driver = DriverUtils.getDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        // 每个步骤后都截图（确保有截图）
        takeScreenshot(scenario, "Step_" + getTimestamp());
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            // 如果场景失败，额外截图并记录详细日志
            if (scenario.isFailed()) {
                String screenshotName = "FAILED_" + scenario.getName() + "_" + getTimestamp();

                // 失败时再次截图
                takeScreenshot(scenario, screenshotName);

                // 获取当前URL和页面标题
                String currentUrl = driver.getCurrentUrl();
                String pageTitle = driver.getTitle();

                // 记录详细的失败日志
                String failureLog = "❌ SCENARIO FAILED ❌\n" +
                        "Time: " + getTimestamp() + "\n" +
                        "Scenario: " + scenario.getName() + "\n" +
                        "Page URL: " + currentUrl + "\n" +
                        "Page Title: " + pageTitle + "\n" +
                        "Error details will be shown in stack trace";

                scenario.attach(failureLog, "text/plain", "Failure Details");
                System.err.println("=== Scenario FAILED: " + scenario.getName() + " ===");
            } else {
                // 成功时也记录日志
                String successLog = "✅ SCENARIO PASSED ✅\n" +
                        "Time: " + getTimestamp() + "\n" +
                        "Scenario: " + scenario.getName();
                scenario.attach(successLog, "text/plain", "Success Details");
                System.out.println("=== Scenario PASSED: " + scenario.getName() + " ===");
            }
        } catch (Exception e) {
            System.err.println("Error in tearDown: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                DriverUtils.quitDriver();
            }
        }
    }

    private void takeScreenshot(Scenario scenario, String screenshotName) {
        try {
            if (driver != null) {
                // 等待页面加载完成
                try {
                    new WebDriverWait(driver, java.time.Duration.ofSeconds(2))
                            .until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                                    .executeScript("return document.readyState").equals("complete"));
                } catch (Exception e) {
                    // 忽略超时异常，继续截图
                }

                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", screenshotName);
                System.out.println("📸 Screenshot taken: " + screenshotName);
            } else {
                System.err.println("⚠️ Driver is null, cannot take screenshot");
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to take screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String getTimestamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
    }
}