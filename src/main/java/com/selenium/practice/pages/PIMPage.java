package com.selenium.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage extends BasePage {

    // LOCADORES
    private By pimHeading = By.cssSelector("h6.oxd-topbar-header-breadcrumb-module");
    private By employeeInfoSubtitle = By.cssSelector("h5.oxd-table-filter-title");
    private By employmentStatusDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    private By searchButton = By.cssSelector("button.orangehrm-left-space");
    private By recordsFound = By.xpath("//span[contains(text(), 'Records Found')]");

    // CONSTRUCTOR
    public PIMPage(WebDriver driver) {
        super(driver);
    }


    // METODOS DE VALIDACION
    public boolean isPIMPageDisplayed() {
        try {
            waitForElementVisible(pimHeading);
            return isElementDisplayed(pimHeading);
        } catch (Exception e) {
            return false;
        }
    }

    public String getPIMPageTitle() {
        return getElementText(pimHeading);
    }

    public String getEmployeeInfoSubtitle() {
        return getElementText(employeeInfoSubtitle);
    }

    public String getRecordsFoundText() {
        return getElementText(recordsFound);
    }

    public boolean isEmployeeInfoDisplayed() {
        return isElementDisplayed(employeeInfoSubtitle);
    }


    // METODOS DE ACCION
    public void clickEmploymentStatusDropdown() {
        click(employmentStatusDropdown);
    }

    public void selectEmploymentStatus(String status) {
        clickEmploymentStatusDropdown();
        By statusOption = By.xpath("//span[text()='" + status + "']");
        waitForElementVisible(statusOption);
        click(statusOption);
    }

    public void clickSearchButton() {
        click(searchButton);
    }


    // METODOS DE NAVEGACION
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}