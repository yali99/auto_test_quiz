package com.example.appium.pages;

import com.example.appium.utils.ElementHelper;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.example.appium.utils.ReportManager.logStepWithScreenshot;

public class SecondPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Second Page']") // 根据实际ID调整
    private WebElement pageTitle;

    @AndroidFindBy(id = "cn.ianzhang.android:id/button_second") // 根据实际ID调整
    private WebElement previousButton;

    @AndroidFindBy(id = "cn.ianzhang.android:id/fab") // 根据实际ID调整
    private WebElement emailImageBtn;


    public String getPageTitle() {
        String title = ElementHelper.getText(pageTitle);
        logStepWithScreenshot("获取第二页标题: " + title);
        return title;
    }

    public void clickPreviousButton() {
        ElementHelper.click(previousButton);
    }

    public boolean isPreviousButtonDisplayed() {
        boolean displayed = ElementHelper.isDisplayed(previousButton);
        return displayed;
    }

    public String getPreviousButtonText() {
        String buttonText = ElementHelper.getText(previousButton);
        return buttonText;
    }

    public boolean isEmailImageBtnDisplayed() {
        boolean displayed = ElementHelper.isDisplayed(emailImageBtn);
        return displayed;
    }

    public void clickEmailImageBtn() {
        ElementHelper.click(emailImageBtn);
    }

    public WebElement getToastElement() {
        WebElement toast = waitUtils.waitForElementToBeVisible(driver.findElement(By.id("cn.ianzhang.android:id/snackbar_text")));
        return toast;

    }

    public boolean isToastDisplayed() {
        boolean displayed = ElementHelper.isDisplayed(driver.findElement(By.id("cn.ianzhang.android:id/snackbar_text")));
        return displayed;
    }

    public String getToastText() {
        String toastText = driver.findElement(By.id("cn.ianzhang.android:id/snackbar_text")).getText();
        return toastText;
    }
}