package com.selenium.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    // LOCADORES
    private By dashboardHeading = By.cssSelector("h6.oxd-topbar-header-breadcrumb-module");
    private By adminMenu = By.xpath("//span[text()='Admin']");
    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By recruitmentMenu = By.xpath("//span[text()='Recruitment']");
    private By userDropdown = By.cssSelector("p.oxd-userdropdown-name");
    private By logoutLink = By.xpath("//a[@href='/web/index.php/auth/logout']");


    // CONSTRUCTOR
    public DashboardPage(WebDriver driver) {
        super(driver);
    }


    // METODOS DE VALIDACION
    public boolean isDashboardDisplayed() {
        try {
            waitForElementVisible(dashboardHeading);
            return isElementDisplayed(dashboardHeading);
        } catch (Exception e) {
            return false;
        }
    }

    public String getDashboardTitle() {
        return getElementText(dashboardHeading);
    }

    public String getUserName() {
        return getElementText(userDropdown);
    }

    public boolean isAdminMenuVisible() {
        return isElementDisplayed(adminMenu);
    }

    public boolean isPIMMenuVisible() {
        return isElementDisplayed(pimMenu);
    }

    public boolean isRecruitmentMenuVisible() {
        return isElementDisplayed(recruitmentMenu);
    }


    // METODOS DE ACCION
    public void clickAdminMenu() {
        click(adminMenu);
    }

    public void clickPIMMenu() {
        click(pimMenu);
    }

    public void clickRecruitmentMenu() {
        click(recruitmentMenu);
    }

    public void clickUserDropdown() {
        click(userDropdown);
    }

    public void clickLogout() {  // NUEVO
        clickUserDropdown();
        waitForElementVisible(logoutLink);
        click(logoutLink);
    }


    // METODOS DE NAVEGACION
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}