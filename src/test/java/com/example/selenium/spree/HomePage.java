package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class HomePage {

    private RemoteWebDriver webDriver;

    public HomePage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Demo Site");
    }
}
