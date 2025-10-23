package com.test.tests;
import com.test.pages.ExceptionsPage;
import com.test.pages.HomePage;
import com.test.utils.DriverUtils;
import com.test.utils.WaitUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;

@Feature("Exception Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExceptionsTest {
    private  WebDriver driver;
    private  ExceptionsPage exceptionsPage;

    @BeforeEach
    public  void setUp() {
        // 初始化驱动
        driver = DriverUtils.getDriver("chrome");
        // 初始化等待器
        WaitUtils.initWait(driver);

        HomePage homePage = new HomePage(driver);
        homePage.open();
        exceptionsPage = homePage.clickExceptionsPageLink();
    }

    @Test
    @Order(1)
    @Story("Test case 1: NoSuchElementException")
    @Description("Verify that wait 5 second for row2 input to be displayed.")
    public void testNoSuchElementException() {
        exceptionsPage.clickAddBtn();
        WebElement secondInput = exceptionsPage.getRow2InputField();
        assertTrue(secondInput.isDisplayed(),"Row2 input isn't displayed");
    }
    @Test
    @Order(2)
    @Story("Test case 2:  ElementNotInteractableException")
    @Description("Verify that button can only be clicked when the button is visible.")
    public void testElementNotInteractableException() {
        exceptionsPage.clickAddBtn();
        WebElement secondInput = exceptionsPage.getRow2InputField();
        assertTrue(secondInput.isDisplayed(),"Row2 input is not displayed");
        exceptionsPage.enterText(secondInput,"apple");
        exceptionsPage.clickSaveBtnByIndex(2);
        String confirmationMsg = exceptionsPage.getConfirmationMsg();
        assertTrue(confirmationMsg.contains("Row 2 was saved"),"Row2 input text save Failed");

    }

    @Test
    @Story("Test case 3: InvalidElementStateException")
    @Description("Verify that input can only be entered when the input is not disabled.")
    public void testInvalidElementStateException() {
        exceptionsPage.clickEditBtn();
        WebElement firstInput = exceptionsPage.getRow1InputField();
        firstInput.clear();
        exceptionsPage.enterText(firstInput,"hamburger");
        exceptionsPage.clickSaveBtnByIndex(1);
        String confirmationMsg = exceptionsPage.getConfirmationMsg();
        assertTrue(confirmationMsg.contains("Row 1 was saved"),"Row1 input text save Failed");
    }

    @Test
    @Story("Test case 4: StaleElementReferenceException")
    @Description("Verify that StaleElementReferenceException is thrown when trying to interact with an element that has been removed from the DOM")
    public void testStaleElementReferenceException() {

        WebElement instructions = exceptionsPage.getInstructions();
        assertTrue(instructions.isDisplayed()," Instruction text element is not displayed");
        exceptionsPage.clickAddBtn();
        assertThrowsExactly(NoSuchElementException.class, () -> exceptionsPage.getInstructions(),"Instruction text element is still existing after clicking add button");
    }

    @Test
    @Story("Test case 5: TimeoutException")
    @Description("Verify that TimeoutException is thrown when waiting for an element to become visible or enabled")
    public void testTimeoutException() {
        exceptionsPage.clickAddBtn();
        WaitUtils.ChangeWaitTimeout(3); //修改超时时间为3秒
        try {
            exceptionsPage.getRow2InputField();
            // 如果未超时，说明测试失败（因为预期应超时）
            fail("There is no TimeoutException thrown, test failed");
        } catch (TimeoutException e) {
            // 捕获到异常，验证异常信息（可选），并确认测试通过
            System.out.println("成功捕获TimeoutException：" + e.getMessage());
            // 断言异常类型正确，测试通过
            assertTrue(true, "TimeoutException is thrown as expected");
        }


    }


    @AfterEach
    public  void tearDown() {
        // 退出驱动
        DriverUtils.quitDriver();
    }
}
