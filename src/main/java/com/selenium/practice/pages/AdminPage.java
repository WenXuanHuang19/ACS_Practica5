package com.selenium.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage extends BasePage {

    // LOCADORES
    private By adminHeading = By.cssSelector("h6.oxd-topbar-header-breadcrumb-module");
    private By userRoleDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    private By statusDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
    private By searchButton = By.cssSelector("button.orangehrm-left-space");


    // CONSTRUCTOR
    public AdminPage(WebDriver driver) {
        super(driver);
    }


    // METODOS DE VALIDACION
    public boolean isAdminPageDisplayed() {
        try {
            waitForElementVisible(adminHeading);
            return isElementDisplayed(adminHeading);
        } catch (Exception e) {
            return false;
        }
    }

    public String getAdminPageTitle() {
        return getElementText(adminHeading);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    // METODOS DE ACCION DROPDOWN
    public void clickUserRoleDropdown() {
        click(userRoleDropdown);
    }

    public void selectUserRole(String role) {
        clickUserRoleDropdown();
        By roleOption = By.xpath("//span[text()='" + role + "']");
        waitForElementVisible(roleOption);
        click(roleOption);
    }

    public void clickStatusDropdown() {
        click(statusDropdown);
    }

    public void selectStatus(String status) {
        clickStatusDropdown();
        By statusOption = By.xpath("//span[text()='" + status + "']");
        waitForElementVisible(statusOption);
        click(statusOption);
    }

    public void clickSearchButton() {
        click(searchButton);
    }
}