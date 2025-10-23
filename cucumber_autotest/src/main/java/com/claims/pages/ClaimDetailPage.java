package com.claims.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ClaimDetailPage extends BasePage{

    private String getReferenceId;

    public String getReferenceId() {
        return getReferenceId;
    }



    public ClaimDetailPage(WebDriver driver) {
        super(driver);
    }



    public String getEmployeeName() {
        WebElement employeeName = waitForElementToBeVisible(
//                By.xpath("//div[contains(@class, 'oxd-form-row')]//input[@class='oxd-input oxd-input--active' and @disabled]"));
//                By.xpath("//form[@class='oxd-form' and @data-v-d5bfe35b]//label[text()='Employee']/ancestor::div[contains(@class, 'oxd-input-group')]//input[@class='oxd-input oxd-input--active' and @disabled]"));
                By.xpath("//label[text()='Employee']/ancestor::div[contains(@class, 'oxd-input-group')]//input[@disabled]"));

        System.out.println("employeeName: " + employeeName.getAttribute("value"));

//        WebElement disabledInput = wait.until(driver -> {
//            // 先确保元素存在
//            WebElement el = driver.findElement(
//                    By.xpath("//label[text()='Reference Id']/ancestor::div[contains(@class, 'oxd-input-group')]//input[@disabled]")
//            );
//            // 额外判断元素是否可见（避免隐藏元素）
//            return el.isDisplayed() ? el : null;
//        });

//        // 初始化JS执行器
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        System.out.println("employeeName: " + (String) js.executeScript("return arguments[0].value;", employeeName));


        return employeeName!=null ?employeeName.getAttribute("value"):null;
    }

    public String getEvent() {
        WebElement event = waitForElementToBeVisible(
                By.xpath("//label[text()='Event']/ancestor::div[contains(@class, 'oxd-input-group')]//input[@disabled]"));

        System.out.println("event: " + event.getAttribute("value"));
        return event!=null ?event.getAttribute("value"):null;
    }

    public String getCurrency() {
        WebElement currency = waitForElementToBeVisible(
                By.xpath("//label[text()='Currency']/ancestor::div[contains(@class, 'oxd-input-group')]//input[@disabled]"));

        System.out.println("currency: " + currency.getAttribute("value"));
        return currency!=null ?currency.getAttribute("value"):null;
    }

    public void saveGetReferenceId() {
        WebElement referenceId = waitForElementToBeVisible(
                By.xpath("//label[text()='Reference Id']/ancestor::div[contains(@class, 'oxd-input-group')]//input[@disabled]"));

        System.out.println("Reference Id: " + referenceId.getAttribute("value"));

        this.getReferenceId = referenceId != null ? referenceId.getAttribute("value") : null;

    }

    public void clickAddExpense() {
        clickElement(By.xpath("(//button[contains(@class, 'oxd-button') and .//i[contains(@class, 'bi-plus')] and contains(., 'Add')])[1]"));

    }
}
