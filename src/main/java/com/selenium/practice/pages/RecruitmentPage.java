package com.selenium.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecruitmentPage extends BasePage {

    // LOCADORES
    private By recruitmentHeading = By.cssSelector("h6.oxd-topbar-header-breadcrumb-module");
    private By candidatesSubtitle = By.cssSelector("h5.oxd-table-filter-title");
    private By vacancyDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    private By searchButton = By.cssSelector("button.orangehrm-left-space");
    private By recordsFound = By.xpath("//span[contains(text(), 'Records Found')]");


    // CONSTRUCTOR
    public RecruitmentPage(WebDriver driver) {
        super(driver);
    }


    // METODOS DE VALIDACION
    public boolean isRecruitmentPageDisplayed() {
        try {
            waitForElementVisible(recruitmentHeading);
            return isElementDisplayed(recruitmentHeading);
        } catch (Exception e) {
            return false;
        }
    }

    public String getRecruitmentPageTitle() {
        return getElementText(recruitmentHeading);
    }

    public String getCandidatesSubtitle() {
        return getElementText(candidatesSubtitle);
    }

    public String getRecordsFoundText() {
        return getElementText(recordsFound);
    }

    public boolean isCandidatesSubtitleDisplayed() {
        return isElementDisplayed(candidatesSubtitle);
    }


    // METODOS DE ACCION
    public void clickVacancyDropdown() {
        click(vacancyDropdown);
    }

    public void selectVacancy(String vacancy) {
        clickVacancyDropdown();
        By vacancyOption = By.xpath("//span[text()='" + vacancy + "']");
        waitForElementVisible(vacancyOption);
        click(vacancyOption);
    }

    public void clickSearchButton() {
        click(searchButton);
    }


    // METODOS DE NAVEGACION
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}