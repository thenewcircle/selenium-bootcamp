package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class ProductPage {
    private RemoteWebDriver webDriver;

    public ProductPage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Tote - Spree Demo Site");
    }
}
