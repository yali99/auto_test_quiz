package com.example.appium.listeners;

import com.example.appium.base.DriverFactory;
import com.example.appium.utils.ReportManager;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;

import java.util.Optional;

public class TestListener implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        String testName = context.getDisplayName();
        ReportManager.logPass("Test passed: " + testName);
        captureScreenshot(testName + "_PASSED");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        ReportManager.logFail("Test failed: " + testName + " - " + cause.getMessage());
        captureScreenshot(testName + "_FAILED");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        ReportManager.logWarning("Test aborted: " + testName);
        captureScreenshot(testName + "_ABORTED");
    }

    private void captureScreenshot(String screenshotName) {
        try {
            if (DriverFactory.getDriver() != null) {
                String base64Screenshot = DriverFactory.getDriver().getScreenshotAs(OutputType.BASE64);
                ReportManager.addScreenshot(base64Screenshot, screenshotName);
            }
        } catch (Exception e) {
            ReportManager.logFail("Failed to capture screenshot: " + e.getMessage());
        }
    }
}