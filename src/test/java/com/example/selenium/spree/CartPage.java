package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class CartPage {

    private RemoteWebDriver webDriver;

    public CartPage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Shopping Cart - Spree Demo Site");
    }
}
