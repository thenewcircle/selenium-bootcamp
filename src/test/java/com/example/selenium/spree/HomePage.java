package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage {

    public static HomePage open(RemoteWebDriver webDriver) {
        webDriver.get("http://spree.newcircle.com");
        return new HomePage(webDriver);
    }

    private final RemoteWebDriver webDriver;

    public HomePage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
        validateTitle();
        validateUrl();
    }

    public void validateUrl() {
        Assert.assertEquals("http://spree.newcircle.com/", webDriver.getCurrentUrl());
    }

    public void validateTitle() {
        Assert.assertEquals("Spree Demo Site", webDriver.getTitle());
    }
}
