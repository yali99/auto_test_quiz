package com.example.appium.tests;

import com.example.appium.base.BaseTest;
import com.example.appium.data.TestData;
import com.example.appium.pages.FirstPage;
import com.example.appium.pages.SecondPage;
import com.example.appium.utils.ReportManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static com.example.appium.utils.ReportManager.logStepWithScreenshot;
import static org.junit.jupiter.api.Assertions.*;

public class PageNavigationTest extends BaseTest {

    @Test
    @DisplayName("从第一页导航到第二页并返回")
    public void testPageNavigation() {
        // 记录测试开始
        logStepWithScreenshot("测试开始 - 第一页初始状态");

        // 假设从第一页开始
        FirstPage firstPage = new FirstPage();


        // 断言第一页元素
        assertTrue(firstPage.isNextButtonDisplayed(), "NEXT按钮应该显示");
        assertEquals(TestData.NEXT_BUTTON_TEXT, firstPage.getNextButtonText(),
                "NEXT按钮文本应该匹配");

        // 提取第一页文本内容
        String firstPageTitle = firstPage.getPageTitle();
        String firstPageContent = firstPage.getContentText();


        // 点击NEXT按钮导航到第二页
        logStepWithScreenshot("准备点击 NEXT 按钮");
        SecondPage secondPage = firstPage.clickNextButton();

        // 断言第二页元素
        assertTrue(secondPage.isPreviousButtonDisplayed(), "PREVIOUS按钮应该显示");
        assertEquals(TestData.PREVIOUS_BUTTON_TEXT, secondPage.getPreviousButtonText(),
                "PREVIOUS按钮文本应该匹配");

        // 提取第二页的导航文本
        String secondPageTitle = secondPage.getPageTitle();

//        logPageContent("Second Page", secondPageTitle,"");

        // 断言页面标题不同
        assertNotEquals(firstPageTitle, secondPageTitle, "两个页面的标题应该不同");

        // 点击第页面的邮件图标
        logStepWithScreenshot("点击第二页的邮件图标");
        secondPage.clickEmailImageBtn();

        // 验证Toast消息显示
        logStepWithScreenshot("验证Toast显示");
        assertTrue(secondPage.isToastDisplayed(), "Toast消息应该显示");
        assertEquals(TestData.EMAIL_TOAST_TEXT, secondPage.getToastText(),
                "Toast消息文本应该匹配");

        // 点击PREVIOUS按钮返回第一页
        logStepWithScreenshot("准备点击 PREVIOUS 按钮");
        secondPage.clickPreviousButton();

        // 验证成功返回第一页
        logStepWithScreenshot("验证成功返回第一页");
        assertTrue(firstPage.isNextButtonDisplayed(), "返回第一页后NEXT按钮应该显示");

        // 记录测试完成
        logStepWithScreenshot("测试完成");
    }

}