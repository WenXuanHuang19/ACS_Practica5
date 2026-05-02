package com.selenium.practice.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;

public class DriverManager {
    private static WebDriver driver;
    private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com";

    public static WebDriver initDriver(String browserType) {
        if (browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
            driver = new ChromeDriver(options);
        } else if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void navigateToApp() {
        driver.navigate().to(BASE_URL + "/web/index.php/auth/login");
    }
}
