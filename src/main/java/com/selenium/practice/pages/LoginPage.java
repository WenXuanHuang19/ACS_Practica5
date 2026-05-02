package com.selenium.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // LOCADORES
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By loginTitle = By.cssSelector("h5.orangehrm-login-title");
    private By forgotPasswordLink = By.cssSelector("p.orangehrm-login-forgot-header");
    private By errorMessage = By.cssSelector("p.oxd-alert-content-text");  // NUEVO


    // CONSTRUCTOR
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    // METODOS DE NAVEGACION
    public void navigateToLogin() {
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        waitForElementVisible(usernameField);
    }


    // METODOS DE ACCION
    public void enterUsername(String username) {
        clearAndSendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        clearAndSendKeys(passwordField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void clickForgotPassword() {
        click(forgotPasswordLink);
    }

    public void login(String username, String password) {
        waitForElementVisible(usernameField);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }


    // METODOS DE VALIDACION
    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(loginTitle);
    }

    public boolean isUsernameFieldVisible() {
        return isElementDisplayed(usernameField);
    }

    public boolean isForgotPasswordVisible() {
        return isElementDisplayed(forgotPasswordLink);
    }

    public boolean isErrorMessageDisplayed() {  // NUEVO
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {  // NUEVO
        try {
            return getElementText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}