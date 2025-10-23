package com.claims.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClaimsPage extends BasePage{


    public ClaimsPage(WebDriver driver) {
        super(driver);
    }


    public void clickAssignClaim() {
        clickElement(By.xpath("//a[contains(text(),'Assign Claim')]"));
        takeScreenshot("click_assign_claim");

    }
    public boolean isExpenseRecordDisplayed(String referenceId) {
        WebElement expenseRecord =waitForElementToBeVisible(By.xpath("//div[text()='"+referenceId+"']"));
        return expenseRecord.isDisplayed();
    }

}
