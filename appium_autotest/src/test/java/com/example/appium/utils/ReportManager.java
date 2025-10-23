package com.example.appium.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.example.appium.base.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {
    private static final Logger logger = LogManager.getLogger(ReportManager.class);
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> step = new ThreadLocal<>();

    public static void initializeReport() {
        if (extent == null) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/test-output/TestReport_" + timeStamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Appium Automation Report");
            sparkReporter.config().setReportName("Mobile Test Results");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setEncoding("utf-8");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Platform", ConfigReader.getProperty("platform.name"));
            extent.setSystemInfo("Device", ConfigReader.getProperty("device.name"));
            extent.setSystemInfo("App", ConfigReader.getProperty("app.path"));

            logger.info("Extent Report initialized at: " + reportPath);
        }
    }

    public static void startTest(String testName) {
        initializeReport();
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        logInfo("Started test: " + testName);
    }

    public static void startStep(String stepName) {
        if (test.get() != null) {
            ExtentTest stepTest = test.get().createNode(stepName);
            step.set(stepTest);
            logInfo("Step: " + stepName);
        }
    }

    public static void endStep() {
        step.remove();
    }

    public static void endTest() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void logInfo(String message) {
        if (step.get() != null) {
            step.get().log(Status.INFO, message);
        } else if (test.get() != null) {
            test.get().log(Status.INFO, message);
        }
        logger.info(message);
    }

    public static void logPass(String message) {
        if (step.get() != null) {
            step.get().log(Status.PASS, message);
        } else if (test.get() != null) {
            test.get().log(Status.PASS, message);
        }
        logger.info("PASS: " + message);
    }

    public static void logFail(String message) {
        if (step.get() != null) {
            step.get().log(Status.FAIL, message);
        } else if (test.get() != null) {
            test.get().log(Status.FAIL, message);
        }
        logger.error("FAIL: " + message);
    }

    public static void logWarning(String message) {
        if (step.get() != null) {
            step.get().log(Status.WARNING, message);
        } else if (test.get() != null) {
            test.get().log(Status.WARNING, message);
        }
        logger.error("WARNING: " + message);
    }


    public static void addScreenshot(String base64Image, String title) {
        if (step.get() != null) {
            step.get().addScreenCaptureFromBase64String(base64Image, title);
        } else if (test.get() != null) {
            test.get().addScreenCaptureFromBase64String(base64Image, title);
        }
    }

    // 新增：带截图的步骤记录
    public static void logStepWithScreenshot(String stepName) {
        startStep(stepName);
        String base64Screenshot = DriverFactory.getDriver().getScreenshotAs(OutputType.BASE64);
        addScreenshot(base64Screenshot, stepName);
        endStep();
    }
}