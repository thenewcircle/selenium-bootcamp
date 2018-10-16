package com.example.selenium.spree.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class BasePage {
    protected RemoteWebDriver webDriver;
    private String title;
    private String url;

    public BasePage(RemoteWebDriver webDriver, String title, String url) {
        this.webDriver = webDriver;
        this.title = title;
        this.url = url;
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, this.title);
    }

    public void validateUrl() {
        String url = webDriver.getCurrentUrl();
        Assert.assertEquals(url, this.url);
    }
}
