package com.test.config;

public class Config {
    // 测试网站地址
    public static final String BASE_URL = "http://practicetestautomation.com/practice/";
    // 登录页地址（可选，用于直接访问）
    public static final String LOGIN_PAGE_URL = "http://practicetestautomation.com/practice-test-login/";
    // 有效登录信息
    public static final String VALID_USERNAME = "student";
    public static final String VALID_PASSWORD = "Password123";
    // 无效登录信息
    public static final String INVALID_USERNAME = "incorrectUser";
    public static final String INVALID_PASSWORD = "incorrectPassword";
    // 等待时间（秒）
    public static final int WAIT_TIMEOUT = 5;
}
