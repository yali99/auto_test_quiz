package com.claims.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    private static final String DEFAULT_CONFIG_PATH = "src/test/resources/test.properties";
    private static  Properties properties;

    static {
        loadProperties();
    }
    private static void loadProperties() {
        properties = new Properties();
        try{
            InputStream inputStream = new FileInputStream(DEFAULT_CONFIG_PATH);
            properties.load(inputStream);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            // 配置文件加载失败时抛出异常，避免后续测试因配置缺失出错
           throw new RuntimeException("Failed to load config file: " + DEFAULT_CONFIG_PATH, e);
        }
    }

    /**
     * 根据键获取配置值（如果键不存在，返回null）
     *
     * @param key 配置项的键
     * @return 配置项的值
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * 根据键获取配置值（如果键不存在，返回默认值）
     * @param key 配置项的键
     * @param defaultValue 默认值
     * @return 配置项的值或默认值
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * 手动重新加载配置文件（用于动态修改配置后刷新）
     */
    public static void reloadProperties() {
        loadProperties();
    }

}
