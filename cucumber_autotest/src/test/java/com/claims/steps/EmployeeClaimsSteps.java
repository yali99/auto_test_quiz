package com.claims.steps;

import com.claims.pages.*;
import com.claims.utils.ConfigUtils;
import com.claims.utils.DriverUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class EmployeeClaimsSteps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ClaimsPage claimsPage;
    private AssignClaimPage assignClaimPage;
    private ClaimDetailPage claimDetailPage;
    private ExpensePage expensePage;
    private Properties config;
    private String employeeName;
    private String event;
    private String currency;

    private String expenseType;
    private String date;
    private String amount;

    private WebDriver driver;

    public EmployeeClaimsSteps() {
        System.out.println("EmployeeClaimsSteps 构造函数");
        this.driver =  DriverUtils.getDriver();
        System.out.println("EmployeeClaimsSteps 构造函数 执行完成");

    }

    @Given("I am logged into the claims management system")
    public void i_am_logged_into_the_claims_management_system() throws IOException {
        // Load configuration
//        driver = DriverUtils.getDriver();
        System.out.println("开始执行I am logged into the claims management system");

        String dashboardUrl = ConfigUtils.getProperty("dashboard.url");
        DriverUtils.openUrl(dashboardUrl);


        // 如果当前URL是登录页，则登录
        if (driver.getCurrentUrl().equals(ConfigUtils.getProperty("login.url"))) {
            System.out.println("当前页面是登录页");
            loginPage = new LoginPage(driver);
            loginPage.login(); //如果没有登录，则登录
        }

        if(loginPage.waitForPageLoad((dashboardUrl))){
            dashboardPage = new DashboardPage(driver);
        }
        else{
            Assert.fail("fail to login");
        }


    }

    @When("I navigate to the Claims page from the left sidebar")
    public void i_navigate_to_the_claims_page_from_the_left_sidebar() {

        dashboardPage.clickClaimsLink();
        Assert.assertTrue(dashboardPage.waitForPageLoadContains(ConfigUtils.getProperty("claims.url")));

    }

    @And("I click on Assign Claim")
    public void i_click_on_assign_claim() {
        claimsPage = new ClaimsPage(driver);
        claimsPage.clickAssignClaim();
        Assert.assertTrue(claimsPage.waitForPageLoadContains(ConfigUtils.getProperty("assign.claim.url")));
    }

    @And("I create a new Assign Claim with the following details:")
    public void i_create_a_new_assign_claim_with_the_following_details(DataTable dataTable) {
        // dataTable 来自 feature 文件中的表格数据

            Map<String, String> claimData = dataTable.asMap(String.class, String.class);

            employeeName = claimData.get("Employee Name");
            event = claimData.get("Event");
            currency = claimData.get("Currency");

            // 验证必要字段
            if (!claimData.containsKey("Employee Name")) {
                throw new IllegalArgumentException("缺少必要字段: Employee Name");
            }

            assignClaimPage = new AssignClaimPage(driver);
            assignClaimPage.createClaim(employeeName, event, currency);

    }

    @Then("I should see a success message for claim creation")
    public void i_should_see_a_success_message_for_claim_creation() {
        Assert.assertTrue("there is no success message",assignClaimPage.isSuccessMessageDisplayed());
    }

    @Then("I should be redirected to the Assign Claim details page")
    public void i_should_be_redirected_to_the_assign_claim_details_page() {

        Assert.assertTrue(assignClaimPage.waitForPageLoadContains(ConfigUtils.getProperty("assign.claim.detail.url")));
    }

    @Then("the claim details should match the entered information")
    public void the_claim_details_should_match_the_entered_information() {
        claimDetailPage = new ClaimDetailPage(driver);

        Assert.assertEquals(employeeName, claimDetailPage.getEmployeeName());
        Assert.assertEquals(event,claimDetailPage.getEvent());
        Assert.assertEquals(currency,claimDetailPage.getCurrency());
        claimDetailPage.saveGetReferenceId();

    }
    @When("I click the Add Expense button")
    public void i_click_the_add_expense_button() {

        claimDetailPage.clickAddExpense();

    }

    @And("I add an expense with the following details:")
    public void i_add_an_expense_with_the_following_details(DataTable dataTable) {
        expensePage = new ExpensePage(driver);
        Map<String, String> expenseData = dataTable.asMap(String.class, String.class);
        expenseType = expenseData.get("Expense Type");
        date = expenseData.get("Date");
        amount = expenseData.get("Amount");

        expensePage.submitExpense(
                expenseType,
                date,
                amount
        );
    }

    @Then("I should see a success message for expense submission")
    public void i_should_see_a_success_message_for_expense_submission() {

        Assert.assertTrue("there is no success message",expensePage.isSuccessMessageDisplayed());

    }

    @Then("the expense details should match the entered information")
    public void the_expense_details_should_match_the_entered_information() {

        List<String> expenseDetails = expensePage.getExpenseDetails(expenseType, date, amount);

        Assert.assertEquals("Expense type should match", expenseType, expenseDetails.get(0));
        Assert.assertEquals("Expense date should be " + date, date, expenseDetails.get(1));
        Assert.assertEquals("Expense amount should be " + amount,amount,expenseDetails.get(2));

    }

    @When("I click the Back button")
    public void i_click_the_back_button() {
        expensePage.clickBackButton();
    }

    @Then("I should see the expense record in the claims list")
    public void i_should_see_the_expense_record_in_the_claims_list() {
        boolean isRecordDisplayed = claimsPage.isExpenseRecordDisplayed(claimDetailPage.getReferenceId());
        Assert.assertTrue("Expense record with 'Reference Id="+claimDetailPage.getReferenceId()+"' should be displayed in the list", isRecordDisplayed);
        expensePage.takeScreenshot("expense_record_in_list");
    }

}
