package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BasePage {
    RemoteWebDriver webDriver;
    String URL;
    String title;

    BasePage(RemoteWebDriver webDriver, String URL, String title,
             boolean navigateTo) {
        this.webDriver = webDriver;
        this.URL = URL;
        this.title = title;

        if(navigateTo) {
            navigate();
        } else {
            assertOnPage();
        }

        PageFactory.initElements(this.webDriver, this);
    }

    protected void navigate() {
        webDriver.navigate().to(URL);
        assertOnPage();
    }

    protected void assertOnPage() {
        Assert.assertEquals(webDriver.getTitle(), title);
    }
}
