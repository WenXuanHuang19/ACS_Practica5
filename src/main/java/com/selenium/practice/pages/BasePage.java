package com.selenium.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int WAIT_TIME = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForUrlContains(String urlPortion) {
        wait.until(ExpectedConditions.urlContains(urlPortion));
    }

    protected void waitForTitleContains(String titlePortion) {
        wait.until(ExpectedConditions.titleContains(titlePortion));
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            return waitForElementVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected String getElementText(By locator) {
        return waitForElement(locator).getText();
    }

    protected void sendKeys(By locator, String text) {
        waitForElementVisible(locator).sendKeys(text);
    }

    protected void click(By locator) {
        waitForElementClickable(locator).click();
    }

    protected void selectDropdownByValue(By locator, String value) {
        WebElement dropdown = waitForElement(locator);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    protected void selectDropdownByText(By locator, String text) {
        WebElement dropdown = waitForElement(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected void clearAndSendKeys(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
}
