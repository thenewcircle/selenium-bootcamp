package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage {

    public static CartPage open(RemoteWebDriver webDriver) {
        webDriver.get("http://spree.newcircle.com/cart");
        return new CartPage(webDriver);
    }

    private final RemoteWebDriver webDriver;

    public CartPage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;

        validateUrl();
        validateTitle();
    }

    private void validateTitle() {
        Assert.assertEquals("Shopping Cart - Spree Demo Site", webDriver.getTitle());
    }

    private void validateUrl() {
        Assert.assertEquals("http://spree.newcircle.com/cart", webDriver.getCurrentUrl());
    }
}
