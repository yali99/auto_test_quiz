package com.claims.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpensePage extends BasePage{

    public ExpensePage(WebDriver driver) {
        super(driver);
    }


    public void submitExpense(String expenseType, String date, String amount) {

        clickElement(By.className("oxd-select-text-input"));

        WebElement expenseTypeOption = waitForElementToBeVisible(By.xpath("//div[@role='option' and .//span[text()='" + expenseType + "']]"));

        System.out.println("expenseType: " + expenseTypeOption.getText());
        expenseTypeOption.click();


        // 获取 Date input
        WebElement dateInput = driver.findElement(By.xpath("//label[text()='Date']/following::input[1]"));
        dateInput.sendKeys(date);
        // 获取 Amount input
        WebElement amountInput = driver.findElement(By.xpath("//label[text()='Amount']/following::input[1]"));
        amountInput.sendKeys(amount);

        takeScreenshot("enter_expense_data");

        // 通过按钮文本内容定位
        WebElement saveButton = driver.findElement(By.xpath("//button[text()=' Save ']"));
        // 通过 type="submit" 定位
//        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();


    }


    public boolean isSuccessMessageDisplayed() {
        WebElement successElement = waitForElementToBePresentWithTimeout(By.xpath("//div[contains(@class, 'oxd-toast-content--success') and .//p[text()='Success'] and .//p[text()='Successfully Saved']]"));
        WebElement successElement2 = waitForElementToBePresentWithTimeout(By.xpath("//div[contains(@class, 'oxd-toast-content--success') and .//p[text()='成功'] and .//p[text()='成功保存']]"));

        return successElement != null || successElement2 != null? true : false;
    }

    public List<String> getExpenseDetails(String expenseTypeText, String dateText, String amountText) {

//        List<WebElement> divs = waitForAllElementsToBeVisible(By.xpath("//div[classname='oxd-table-body'][0]//div[contains(@class,'oxd-table-row--with-border')]"));
//        WebElement parentDiv = divs.get(divs.size()-1);
//        WebElement expenseTypeElement = parentDiv.findElement(By.xpath("//div[contains(text(),'"+expenseTypeText+"')]"));
        WebElement rowElement = waitForElementToBeVisible(By.xpath("//div[@class='oxd-table-body' and @role='rowgroup']//div[@role='row' and contains(@class, 'oxd-table-row') and .//div[@role='cell'][2]//div[text()='Accommodation'] and .//div[@role='cell'][3]//div[text()='2025-01-01'] and .//div[@role='cell'][5]//div[text()='150.00']]"));
//        System.out.println("rowElement: " + rowElement.getText());
        WebElement expenseTypeElement = rowElement.findElement(By.xpath("//div[@role='cell'][2]//div[text()='"+expenseTypeText+"']"));
//        System.out.println("expenseTypeElement: " + expenseTypeElement.getText());
        WebElement dateElement = rowElement.findElement(By.xpath("//div[@role='cell'][3]//div[text()='"+dateText+"']"));
//        System.out.println("dateElement: " + dateElement.getText());
        WebElement amountElement = rowElement.findElement(By.xpath("//div[@role='cell'][5]//div[text()='"+amountText+"']"));
//        System.out.println("amountElement: " + amountElement.getText());
        return Arrays.asList(expenseTypeElement.getText(), dateElement.getText(), amountElement.getText());

    }


    public void clickBackButton() {
        clickElement(By.xpath("//button[text()=' Back ']"));
    }

}
