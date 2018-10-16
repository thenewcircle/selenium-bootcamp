package com.example.selenium.spree.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class ProductPage  extends BasePage {
    public static final String URL = "https://spreecommerce-demo.herokuapp.com/products/spree-tote";

    public static final String TITLE = "Spree Tote - Spree Demo Site";

    public ProductPage(RemoteWebDriver webDriver) {
        super(webDriver, TITLE, URL);
    }
}
