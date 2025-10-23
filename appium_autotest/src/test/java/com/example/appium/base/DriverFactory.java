package com.example.appium.base;

import com.example.appium.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void initializeDriver() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability(MobileCapabilityType.PLATFORM_NAME,
                    ConfigReader.getProperty("platform.name"));
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,
                    ConfigReader.getProperty("platform.version"));
            caps.setCapability(MobileCapabilityType.DEVICE_NAME,
                    ConfigReader.getProperty("device.name"));
            caps.setCapability(MobileCapabilityType.APP,
                    ConfigReader.getProperty("app.path"));
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,
                    ConfigReader.getProperty("automation.name"));

            caps.setCapability("appPackage", ConfigReader.getProperty("app.package"));
            caps.setCapability("appActivity", ConfigReader.getProperty("app.activity"));

            URL appiumServerUrl = new URL(ConfigReader.getProperty("appium.server.url"));

            String platform = ConfigReader.getProperty("platform.name");
            if ("Android".equalsIgnoreCase(platform)) {
                driver.set(new AndroidDriver(appiumServerUrl, caps));
            } else if ("iOS".equalsIgnoreCase(platform)) {
                driver.set(new IOSDriver(appiumServerUrl, caps));
            }

            getDriver().manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicit.wait"))));

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}