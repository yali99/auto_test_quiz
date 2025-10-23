package com.claims.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage{
    private WebDriver driver;


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void clickClaimsLink() {
        clickElement(By.xpath("//span[contains(@class, 'oxd-main-menu-item--name') and text()='Claim']"));
        takeScreenshot("click_claims_link");
    }



}
