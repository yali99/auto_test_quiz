package com.test.pages;

import com.test.config.Config;
import com.test.utils.ScreenshotUtils;
import com.test.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.qameta.allure.Step;

public class HomePage {
    private WebDriver driver;

    // 页面元素定位（使用 @FindBy 注解）
    @FindBy(linkText = "Test Login Page")
    private WebElement loginPageLink;

    @FindBy(linkText = "Test Exceptions")
    private WebElement exceptionsPageLink;

    // 构造方法：初始化页面对象

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // 初始化元素
    }

    // 打开首页
    @Step("open Home Page")
    public void open() {
        driver.get(Config.BASE_URL);
        ScreenshotUtils.takeScreenshot(driver);
    }

    // 点击 "Test Login Page" 进入登录页
    @Step("click Test Login Page Link")
    public LoginPage clickLoginPageLink() {
        WaitUtils.waitForClickable(loginPageLink).click();
        ScreenshotUtils.takeScreenshot(driver);
        return new LoginPage(this.driver); // 跳转后返回新页面的对象
    }

    // 点击 "Test Exceptions" 进入异常测试页
    @Step("click Test Exceptions Page Link")
    public ExceptionsPage clickExceptionsPageLink() {
        WaitUtils.waitForClickable(exceptionsPageLink).click();
        ScreenshotUtils.takeScreenshot(driver);
        return new ExceptionsPage(this.driver);
    }
}
