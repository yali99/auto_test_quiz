package com.test.pages;

import com.test.utils.ScreenshotUtils;
import com.test.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ExceptionsPage {
    private WebDriver driver;

    @FindBy(id = "add_btn")
    private WebElement addBtn;

    @FindBy(id = "edit_btn")
    private WebElement editBtn;

    @FindBy(id = "row2")
    private WebElement row2Div;
//
//    @FindBy(name = "Save")
//    private WebElement saveBtn;

    @FindBy(id = "confirmation")
    private WebElement confirmation;

    @FindBy(id = "instructions")
    private WebElement instructions;



    // 构造函数
    public ExceptionsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //点击添加按钮
    @Step("click Add Button")
    public void clickAddBtn() {
        WaitUtils.waitForClickable(addBtn).click();
        ScreenshotUtils.takeScreenshot(driver);
    }

    //点击编辑按钮
    @Step("click Edit Button")
    public void clickEditBtn() {
        WaitUtils.waitForClickable(editBtn).click();
        ScreenshotUtils.takeScreenshot(driver);
    }

    //获取第一行输入框
    @Step("get Row1 Input Field")
    public WebElement getRow1InputField(){
        List<WebElement> inputFields = driver.findElements(By.className("input-field"));
        ScreenshotUtils.takeScreenshot(driver);
        return inputFields != null && inputFields.size() > 0 ? inputFields.get(0) : null;
    }
    //获取第二行输入框
    @Step("get Row2 Input Field")
    public WebElement getRow2InputField(){
        row2Div = WaitUtils.waitForVisibility(row2Div);
        ScreenshotUtils.takeScreenshot(driver);
        return row2Div.findElement(By.className("input-field"));
    }

    //输入文本
    @Step("enter Text")
    public void enterText(WebElement inputField, String text){
        if(inputField !=null) {
            inputField.clear();
            inputField.sendKeys(text);
            ScreenshotUtils.takeScreenshot(driver);
        }else{
            System.out.println("inputField is null");
        }
    }
    @Step("clear Text")
    public void clearText(WebElement inputField, String text){
        if(inputField !=null) {
            inputField.clear();
            inputField.sendKeys(text);
            ScreenshotUtils.takeScreenshot(driver);
        }else{
            System.out.println("inputField is null");
        }
    }

    //获取指令文本
    @Step("get Instructions")
    public WebElement getInstructions(){
        instructions = driver.findElement(By.id("instructions"));
        ScreenshotUtils.takeScreenshot(driver);
        return instructions;
    }

    //点击保存按钮
    @Step("click Save Button")
    public void clickSaveBtnByIndex(int index){
        List<WebElement> saveBtns = driver.findElements(By.name("Save"));
        if (saveBtns == null || saveBtns.size() == 0) {
            System.out.println("页面中 name='Save' 的按钮不存在");
            return;
        }
        if(index ==1){
            saveBtns.get(0).click();
        }else if(index == 2 && saveBtns.size()>1){
            saveBtns.get(1).click();
        }
        ScreenshotUtils.takeScreenshot(driver);

    }

    @Step("get Confirmation Msg")
    public String getConfirmationMsg(){
        confirmation = WaitUtils.waitForVisibility(confirmation);
        // 强制通过 JavaScript 滚动到元素
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", confirmation);

        ScreenshotUtils.takeScreenshot(driver);
        return confirmation.getText();

    }



}
