package com.test.pages;

import com.test.utils.ScreenshotUtils;
import com.test.utils.WaitUtils;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    // 元素定位
    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(className = "btn")
    private WebElement submitBtn;

    @FindBy(className = "post-title")
    private WebElement successMsg;

    @FindBy(id = "error")
    private WebElement errorMsg;

    @FindBy(linkText = "Home")
    private WebElement homeLink;

    // 构造方法
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // 输入用户名
    @Step("type username：{0}")
    public void enterUsername(String username) {
        WaitUtils.waitForVisibility(usernameInput).sendKeys(username);
        ScreenshotUtils.takeScreenshot(driver);
    }

    // 输入密码
    @Step("type password：{0}")
    public void enterPassword(String password) {
        WaitUtils.waitForVisibility(passwordInput).sendKeys(password);
        ScreenshotUtils.takeScreenshot(driver);
    }

    // 点击登录按钮
    @Step("click Submit Button")
    public void clickSubmit() {
        WaitUtils.waitForClickable(submitBtn).click();
        ScreenshotUtils.takeScreenshot(driver);
    }

    // 执行登录操作
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
    }

    // 获取登录成功提示
    @Step("get Success Message")
    public String getSuccessMessage() {
        successMsg = WaitUtils.waitForVisibility(successMsg);
        ScreenshotUtils.takeScreenshot(driver);
        return successMsg.getText();
    }

    // 获取错误提示
    @Step("get Error Message")
    public String getErrorMessage() {
        errorMsg = driver.findElement(By.id("error"));
//        errorMsg.scrollIntoView();
        // 可选：让元素居中显示（避免被导航栏遮挡）
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", errorMsg);
        ScreenshotUtils.takeScreenshot(driver);
        return WaitUtils.waitForVisibility(errorMsg).getText();
    }

    // 返回首页
    public HomePage clickHomeLink() {
        WaitUtils.waitForClickable(homeLink).click();
        return new HomePage(driver);
    }
}
