package com.example.appium.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PageUtils {
    private static final Logger logger = LogManager.getLogger(PageUtils.class);

    /**
     * 记录页面内容到日志和报告
     */
    public static void logPageContent(String pageName, String title, String content) {
        String logMessage = String.format(
                "=== %s 内容 ===\n标题: %s\n内容: %s\n======================",
                pageName, title, content
        );

        logger.info(logMessage);
        ReportManager.logInfo(logMessage);
    }

    /**
     * 提取并记录页面所有文本资源
     */
    public static void logAllPageResources(String pageTitle, String buttonText,
                                           String contentText, String... additionalTexts) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== 页面资源文本 ===\n");
        sb.append("页面标题: ").append(pageTitle).append("\n");
        sb.append("按钮文本: ").append(buttonText).append("\n");
        sb.append("内容文本: ").append(contentText).append("\n");

        for (int i = 0; i < additionalTexts.length; i++) {
            sb.append("附加文本").append(i + 1).append(": ").append(additionalTexts[i]).append("\n");
        }
        sb.append("======================");

        logger.info(sb.toString());
        ReportManager.logInfo(sb.toString());
    }
}