package com.prosperworks.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by jacobp on 11/3/2016.
 */
public class HamburgerMenu extends ProsperWorksPage {
    public HamburgerMenu(RemoteWebDriver webDriver) {
        super(webDriver);

        new WebDriverWait(webDriver, PAGE_READY_DELAY).until(
                elementToBeClickable(By.id("dashboard"))
        );
    }

    public Dashboard clickDashboard() {
        webDriver.findElementById("dashboard").click();
        confirmClose();
        return new Dashboard(webDriver);
    }

    public CompaniesPage clickCompanies() {
        webDriver.findElementById("organization").click();
        confirmClose();
        return new CompaniesPage(webDriver);
    }

    public void confirmClose() {
        new WebDriverWait(webDriver, PAGE_READY_DELAY).until(
                invisibilityOfElementLocated(By.id("dashboard"))
        );
    }
}
