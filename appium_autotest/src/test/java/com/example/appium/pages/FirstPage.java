package com.example.appium.pages;

import com.example.appium.utils.ElementHelper;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;


public class FirstPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='First Page']") // 根据实际ID调整
    private WebElement pageTitle;

    @AndroidFindBy(id = "cn.ianzhang.android:id/button_first") // 根据实际ID调整
    private WebElement nextButton;

    @AndroidFindBy(id = "cn.ianzhang.android:id/textview_first") // 根据实际ID调整
    private WebElement contentText;

    public String getPageTitle() {
        String title = ElementHelper.getText(pageTitle);
        return title;
    }

    public String getContentText() {
        String content = ElementHelper.getText(contentText);
        return content;
    }

    public SecondPage clickNextButton() {
        ElementHelper.click(nextButton);
        return new SecondPage();
    }

    public boolean isNextButtonDisplayed() {
        boolean displayed = ElementHelper.isDisplayed(nextButton);
        return displayed;
    }

    public String getNextButtonText() {
        String buttonText = ElementHelper.getText(nextButton);
        return buttonText;
    }
}