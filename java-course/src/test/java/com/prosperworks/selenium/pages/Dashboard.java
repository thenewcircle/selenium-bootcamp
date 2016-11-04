package com.prosperworks.selenium.pages;

import com.prosperworks.selenium.pages.ProsperWorksPage;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jacobp on 11/3/2016.
 */
public class Dashboard extends ProsperWorksPage{

    public Dashboard(RemoteWebDriver webDriver) {
        super(webDriver);

        new WebDriverWait(webDriver, PAGE_READY_DELAY).until(
                ExpectedConditions.textToBe(By.cssSelector("a.Tab-dashboard > h4"), "TODAY")
        );
    }
}
