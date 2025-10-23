package com.claims.steps;

import com.claims.utils.DriverUtils;
import io.cucumber.java.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Hooks {

    private WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("测试开始执行...");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("所有测试执行完成，确保报告生成...");

        // 给 Grasshopper 适配器一些时间完成报告生成
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("报告应该已在 test-output/SparkReport/ 目录下生成");
    }

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("=== Starting Scenario: " + scenario.getName() + " ===");
        driver = DriverUtils.getDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        // 1. 首先等待页面基本加载完成
        waitForPageReadyState(driver);


        // 3. 没有指定元素时，等待页面内容稳定
        waitForPageContentStable(driver, 10);


        // 4. 额外的短暂等待，确保渲染完成
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 每个步骤后都截图（确保有截图）
        takeScreenshot(scenario, "Step_" + getTimestamp());
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            // 如果场景失败，额外截图并记录详细日志
            if (scenario.isFailed()) {
                // 1. 首先等待页面基本加载完成
                waitForPageReadyState(driver);


                // 3. 没有指定元素时，等待页面内容稳定
                    waitForPageContentStable(driver, 10);


                // 4. 额外的短暂等待，确保渲染完成
                Thread.sleep(500);

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


    /**
     * 等待页面readyState
     */
    private static void waitForPageReadyState(WebDriver driver) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                    webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState").equals("complete")
            );
        } catch (Exception e) {
            System.out.println("页面readyState等待超时，继续执行");
        }
    }


    /**
     * 等待页面内容稳定（通过检查DOM变化）
     */
    private static void waitForPageContentStable(WebDriver driver, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        wait.until(d -> {
            try {
                // 获取当前页面的一些特征值
                String initialState = getPageStateSignature(driver);
                Thread.sleep(1000); // 等待1秒
                String finalState = getPageStateSignature(driver);

                // 如果状态相同，说明页面稳定
                return initialState.equals(finalState);
            } catch (Exception e) {
                return false;
            }
        });
    }

    /**
     * 获取页面状态特征签名
     */
    private static String getPageStateSignature(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 组合多个页面特征
            String readyState = (String) js.executeScript("return document.readyState");
            String bodyText = (String) js.executeScript("return document.body.innerText");
            String bodyChildren = (String) js.executeScript("return document.body.children.length");
            String scrollHeight = (String) js.executeScript("return document.body.scrollHeight");

            return readyState + "|" +
                    (bodyText != null ? bodyText.hashCode() : "null") + "|" +
                    bodyChildren + "|" +
                    scrollHeight;
        } catch (Exception e) {
            return "error_state";
        }
    }
}