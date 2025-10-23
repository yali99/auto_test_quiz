package com.claims.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;



public class AssignClaimPage extends BasePage{
    public AssignClaimPage(WebDriver driver) {
        super(driver);
    }

    public void createClaim(String employeeName, String event, String currency) {

        enterText(driver.findElement(By.cssSelector("input[placeholder='Type for hints...']")), employeeName);
        takeScreenshot("enter_employee_name");

        WebElement employeeOption = waitForElementToBeVisible(By.xpath("//div[@role='option' and @class='oxd-autocomplete-option' and .//span[text()='yytest  wang']]"));

        if(employeeOption == null){
            System.out.println("未找到员工姓名");
        }else{
            employeeOption.click();
        }


// 只能是html select标签可以用 Select ， 其他标签用 Select 无效
//        Select eventDropdown = new Select(eventSelect);
//        eventDropdown.selectByVisibleText(event);
//        takeScreenshot("select_event");

        // event 和currency 都是下拉框，且class 一样，所以用 index 获取
        List<WebElement> webElementList = driver.findElements(By.className("oxd-select-wrapper"));
        //点击event 下拉框
        webElementList.get(0).click();
        takeScreenshot("click_event_dropdown");
//        clickElement(By.xpath("//div[contains(@class, 'oxd-select-dropdown')]//div[contains(@class, 'oxd-select-option')].//span[text()='"+event+"']"));
        clickElement(By.xpath("//div[@role='option' and @class='oxd-select-option' and .//span[text()='Travel Allowance']]"));
        takeScreenshot("select_event");

        //点击currency 下拉框
        webElementList.get(1).click();
        takeScreenshot("click_currency_dropdown");
//        clickElement(By.xpath("//div[contains(@class, 'oxd-select-dropdown')]//div[contains(@class, 'oxd-select-option')]//span[text()='"+currency+"']"));
        clickElement(By.xpath("//div[contains(@class, 'oxd-select-option') and .//span[text()='"+currency+"']]"));
        takeScreenshot("select_currency");

        clickElement(By.xpath("//button[@type='submit']"));
        takeScreenshot("click_create");
    }

    public boolean isSuccessMessageDisplayed() {

        WebElement successElement = waitForElementToBePresentWithTimeout(By.xpath("//div[contains(@class, 'oxd-toast-content--success') and .//p[text()='Success'] and .//p[text()='Successfully Saved']]"));
        WebElement successElement2 = waitForElementToBePresentWithTimeout(By.xpath("//div[contains(@class, 'oxd-toast-content--success') and .//p[text()='成功'] and .//p[text()='成功保存']]"));

        return successElement != null || successElement2 != null? true : false;
    }

}
