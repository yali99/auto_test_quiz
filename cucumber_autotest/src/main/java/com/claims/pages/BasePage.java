package com.claims.pages;

import com.claims.utils.ConfigUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean waitForPageLoad(String url) {
        return wait.until(ExpectedConditions.urlToBe(url));

    }

    public boolean waitForPageLoadContains(String url) {
        return wait.until(ExpectedConditions.urlContains(url));
    }


    protected WebElement waitForElementToBeVisible(By locator) {
        //元素必须在页面上可见且显示，用于用户交互准备，较慢
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));

    }

    protected List<WebElement> waitForAllElementsToBeVisible(By locator) {
        //元素必须在页面上可见且显示，用于用户交互准备，较慢
        return wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(locator)));

    }

    protected WebElement waitForElementToBePresent(By locator) {
        //元素可能在DOM中但不可见，用于元素加载确认，较快
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement waitForElementToBePresentWithTimeout(By locator) {
        //元素可能在DOM中但不可见，用于元素加载确认，较快
        try{
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
        catch (TimeoutException e){
            System.err.println("Timeout waiting for element to be present: " + locator);
            return null;
        }
    }

    protected List<WebElement> waitForAllElementsToBePresent(By locator) {
        //元素可能在DOM中但不可见，用于元素加载确认，较快
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }


    protected WebElement waitForElementToBeClickable(WebElement element) {
       return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clickElement(By locator) {
        waitForElementToBeClickable(waitForElementToBeVisible(locator)).click();

    }

    protected void enterText(WebElement element, String text) {
        waitForElementToBeClickable(element);
        element.clear();
        element.sendKeys(text);
    }

    protected String getElementText(By locator) {
        return waitForElementToBeVisible(locator).getText();

    }

    public String takeScreenshot(String screenshotName) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = now.format(formatter);

            String fileName = screenshotName + "_" + timestamp + ".png";
            Path screenshotsDir = Paths.get("reports", "screenshots");

            if (!Files.exists(screenshotsDir)) {
                Files.createDirectories(screenshotsDir);
            }

            Path filePath = screenshotsDir.resolve(fileName);

            TakesScreenshot ts = (TakesScreenshot) driver;
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);
            Files.copy(sourceFile.toPath(), filePath);

            return filePath.toString();
        } catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }
}
