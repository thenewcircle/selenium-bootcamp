package com.prosperworks.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompaniesPage extends ProsperWorksPage {

    public CompaniesPage(RemoteWebDriver webDriver) {
        super(webDriver);

        new WebDriverWait(webDriver, PAGE_READY_DELAY).until(
                ExpectedConditions.textToBe(By.cssSelector(".WebAppHeader_globalNav .Dropdown_selected"), "Companies")
        );
    }
}
