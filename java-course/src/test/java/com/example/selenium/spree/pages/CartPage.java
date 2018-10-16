package com.example.selenium.spree.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage extends BasePage {
    public static final String URL = "https://spreecommerce-demo.herokuapp.com/cart";

    private static final String TITLE = "Shopping Cart - Spree Demo Site";

    public CartPage(RemoteWebDriver webDriver) {
        super(webDriver, TITLE, URL);
    }
}