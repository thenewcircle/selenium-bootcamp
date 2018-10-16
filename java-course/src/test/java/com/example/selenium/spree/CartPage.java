package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class CartPage {
    public static final String URL = "https://spreecommerce-demo.herokuapp.com/cart";
    public static final String TITLE = "Shopping Cart - Spree Demo Site";

    RemoteWebDriver webDriver;

    public CartPage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, TITLE);
    }

    public void validateUrl() {
        String url = webDriver.getCurrentUrl();
        Assert.assertEquals(url, URL);
    }
}