package com.test.tests;
import com.test.config.Config;
import com.test.pages.HomePage;
import com.test.pages.LoginPage;
import com.test.utils.DriverUtils;
import com.test.utils.ScreenshotUtils;
import com.test.utils.WaitUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Login Page Test")
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setup(){
        driver = DriverUtils.getDriver("chrome");
        WaitUtils.initWait(driver);
        HomePage homePage = new HomePage(driver);
        homePage.open();
        loginPage = homePage.clickLoginPageLink();
    }

    @Test
    @Story("Positive LogIn test")
    @Description("use valid username and password to login")
    public void testValidLogin(){
        loginPage.login(Config.VALID_USERNAME, Config.VALID_PASSWORD);
        assertEquals("Logged In Successfully", loginPage.getSuccessMessage(),"有效登录失败");
    }

    @Test
    @Story("Negative username test")
    @Description("use invalid username to login")
    public void testInvalidLogin(){
        loginPage.login(Config.INVALID_USERNAME, Config.VALID_PASSWORD);
        assertEquals("Your username is invalid!", loginPage.getErrorMessage(),"无效登录成功");
    }
    @Test
    @Story("Negative password test")
    @Description("use invalid password to login")
    public void testInvalidPasswordLogin(){
        loginPage.login(Config.VALID_USERNAME, Config.INVALID_PASSWORD);
        assertEquals("Your password is invalid!", loginPage.getErrorMessage(),"无效登录成功");
    }
    @AfterEach
    public void tearDown(){
        DriverUtils.quitDriver();
    }

}
